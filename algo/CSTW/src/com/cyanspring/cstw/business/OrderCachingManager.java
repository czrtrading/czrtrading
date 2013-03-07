/*******************************************************************************
 * Copyright (c) 2011-2012 Cyan Spring Limited
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms specified by license file attached.
 * 
 * Software distributed under the License is released on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 ******************************************************************************/
package com.cyanspring.cstw.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyanspring.common.business.MultiInstrumentStrategyData;
import com.cyanspring.common.business.OrderField;
import com.cyanspring.common.business.ParentOrder;
import com.cyanspring.common.data.DataObject;
import com.cyanspring.common.event.AsyncEvent;
import com.cyanspring.common.event.IAsyncEventListener;
import com.cyanspring.common.event.IRemoteEventManager;
import com.cyanspring.common.event.order.ParentOrderUpdateEvent;
import com.cyanspring.common.event.order.StrategySnapshotEvent;
import com.cyanspring.common.event.strategy.MultiInstrumentStrategyUpdateEvent;
import com.cyanspring.common.event.strategy.StrategyLogEvent;
import com.cyanspring.cstw.event.GuiMultiInstrumentStrategyUpdateEvent;
import com.cyanspring.cstw.event.GuiParentOrderUpdateEvent;
import com.cyanspring.cstw.event.OrderCacheReadyEvent;

public class OrderCachingManager implements IAsyncEventListener {
	private static final Logger log = LoggerFactory.getLogger(OrderCachingManager.class);
	SingleInstrumentStrategyCache singleInstrumentStrategyCache = new SingleInstrumentStrategyCache();
	IRemoteEventManager eventManager;
	private ArrayList<String> servers = new ArrayList<String>();
	private Queue<ParentOrder> singleInstrumentStrategyQueue = new LinkedList<ParentOrder>();
	private MultiInstrumentStrategyCache multiInstrumentStrategyCache = new MultiInstrumentStrategyCache();
	private Queue<MultiInstrumentStrategyData> multiInstrumentStrategyQueue = new LinkedList<MultiInstrumentStrategyData>();
	private HashMap<String, LinkedList<StrategyLogEvent>> logs = new HashMap<String, LinkedList<StrategyLogEvent>>();
	private boolean ready;
	private static final int maxLog = 100;
	
	public OrderCachingManager(IRemoteEventManager eventManager) {
		this.eventManager = eventManager;
	}
	
	public synchronized void processStrategySnapshotEvent(StrategySnapshotEvent event) {
		// clearing strategy log for this server
		clearLogs(event.getSender());
		servers.add(event.getSender());
		
		// processing parent orders snapshot for this server
		singleInstrumentStrategyCache.clearOrders(event.getSender());
		for(ParentOrder order: event.getOrders()) {
//			order.put(OrderField.SERVER.value(), event.getSender());
			singleInstrumentStrategyCache.updateOrder(order);
		}
		processQueuedParentOrderUpdate();
		
		// processing MultiInstrumentStrategy snapshot for this server
		multiInstrumentStrategyCache.clear(event.getSender());
		for(MultiInstrumentStrategyData data: event.getStrategyData()) {
//			data.put(OrderField.SERVER.value(), event.getSender());
			multiInstrumentStrategyCache.update(data);
		}
		processQueuedMultiInstrumentStrategyUpdate();
		
		// setting ready
		setReady(true);
		eventManager.sendEvent(new OrderCacheReadyEvent(null));
	}
	
	private void clearLogs(String sender) {
		List<Map<String, Object>> orders = singleInstrumentStrategyCache.getOrders();
		for(Map<String, Object> map: orders) {
			String name = (String)map.get(OrderField.SERVER_ID.value());
			if(name.equals(sender)) {
				String key = (String) map.get(OrderField.ID.value());
				logs.remove(key);
				log.debug("Removed strategy log: " + key);
			}
		}
		List<Map<String, Object>> list = multiInstrumentStrategyCache.getStrategyList();
		for(Map<String, Object> map: list) {
			String name = (String)map.get(OrderField.SERVER_ID.value());
			if(name.equals(sender)) {
				String key = (String) map.get(OrderField.ID.value());
				logs.remove(key);
				log.debug("Removed strategy log: " + key);
			}
		}
	}

	private void processQueuedParentOrderUpdate() {
		LinkedList<ParentOrder> newQueue = new LinkedList<ParentOrder>();
		while (singleInstrumentStrategyQueue.size()>0) {
			ParentOrder order = singleInstrumentStrategyQueue.remove();
			String server = order.get(String.class, OrderField.SERVER_ID.value());
			if (servers.contains(server)) {
				singleInstrumentStrategyCache.updateOrder(order);
			} else {
				newQueue.add(order);
			}
		}
		singleInstrumentStrategyQueue = newQueue;
	}
	
	private void processQueuedMultiInstrumentStrategyUpdate() {
		LinkedList<MultiInstrumentStrategyData> newQueue = new LinkedList<MultiInstrumentStrategyData>();
		while (multiInstrumentStrategyQueue.size()>0) {
			MultiInstrumentStrategyData data = multiInstrumentStrategyQueue.remove();
			String server = data.get(String.class, OrderField.SERVER_ID.value());
			if (servers.contains(server)) {
				multiInstrumentStrategyCache.update(data);
			} else {
				newQueue.add(data);
			}
		}
		multiInstrumentStrategyQueue = newQueue;
	}

	private void processParentOrderUpdateEvent(ParentOrderUpdateEvent event) {
		ParentOrder parentOrder = ((ParentOrderUpdateEvent)event).getOrder();
//		parentOrder.put(OrderField.SERVER.value(), event.getSender());
		log.debug("Update parent order recieved: " + parentOrder);
		String server = parentOrder.get(String.class, OrderField.SERVER_ID.value());
		if (server == null || server.equals("") || servers.contains(server)) {
			singleInstrumentStrategyCache.updateOrder(parentOrder);
			eventManager.sendEvent(new GuiParentOrderUpdateEvent(parentOrder));
		} else {
			singleInstrumentStrategyQueue.add(parentOrder);
		}
	}
	
	private void processStrategyLogEvent(StrategyLogEvent event) {
		LinkedList<StrategyLogEvent> list = logs.get(event.getKey());
		if(null == list) {
			list = new LinkedList<StrategyLogEvent>();
			logs.put(event.getKey(), list);
		}
		
		if(list.size()>= maxLog) {
			list.remove();
		}
		list.add(event);
	}
	
	public List<StrategyLogEvent> getLogEvents(String id) {
		LinkedList<StrategyLogEvent> list = logs.get(id);
		if(null == list) {
			list = new LinkedList<StrategyLogEvent>();
			logs.put(id, list);
		}
		return new ArrayList<StrategyLogEvent>(list);
	}

	public synchronized List<Map<String, Object>> getParentOrders() {
		return singleInstrumentStrategyCache.getOrders();
	}
	
	public synchronized List<Map<String, Object>> getMultiInstrumentStrategies() {
		return multiInstrumentStrategyCache.getStrategyList();
	}
	
	public synchronized List<Map<String, Object>> getInstruments(String id) {
		return multiInstrumentStrategyCache.getInstrumentList(id);
	}
	
	public synchronized Map<String, Object> getMultiInstrumentStrategy(String id) {
		return multiInstrumentStrategyCache.getStrategy(id);
	}
	
	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public void init() {
		eventManager.subscribe(StrategySnapshotEvent.class, this);
		eventManager.subscribe(ParentOrderUpdateEvent.class, this);
		eventManager.subscribe(StrategyLogEvent.class, this);
		eventManager.subscribe(MultiInstrumentStrategyUpdateEvent.class, this);
	}

	public void uninit() {
		eventManager.unsubscribe(StrategySnapshotEvent.class, this);
		eventManager.unsubscribe(ParentOrderUpdateEvent.class, this);
		eventManager.unsubscribe(StrategyLogEvent.class, this);
		eventManager.unsubscribe(MultiInstrumentStrategyUpdateEvent.class, this);
	}

	public ArrayList<String> getServers() {
		return servers;
	}
	
	public Map<String, Object> getStrategyData(String id) {
		DataObject result = singleInstrumentStrategyCache.getParentOrder(id);
		if(null == result)
			return multiInstrumentStrategyCache.getStrategy(id);
		else
			return result.getFields();
	}

	private void processMultiInstrumentStrategyUpdateEvent(
			MultiInstrumentStrategyUpdateEvent event) {
		MultiInstrumentStrategyData data = event.getStrategyData();
		//data.put(OrderField.SERVER.value(), event.getSender());
		String server = event.getSender();
		if (server == null || server.equals("") || servers.contains(server)) {
			multiInstrumentStrategyCache.update(data);
			eventManager.sendEvent(new GuiMultiInstrumentStrategyUpdateEvent(event.getStrategyData().getId()));
		} else {
			multiInstrumentStrategyQueue.add(data);
		}
	}
	
	@Override
	public void onEvent(AsyncEvent event) {
		if (event instanceof StrategySnapshotEvent) {
			processStrategySnapshotEvent((StrategySnapshotEvent)event);
		} else if (event instanceof StrategyLogEvent) {
			processStrategyLogEvent((StrategyLogEvent)event);
		} else if (event instanceof ParentOrderUpdateEvent) {
			processParentOrderUpdateEvent((ParentOrderUpdateEvent)event);
		} else if (event instanceof ParentOrderUpdateEvent) {
			processParentOrderUpdateEvent((ParentOrderUpdateEvent)event);
		} else if (event instanceof MultiInstrumentStrategyUpdateEvent) {
			processMultiInstrumentStrategyUpdateEvent((MultiInstrumentStrategyUpdateEvent)event);
		}
	}

	
}

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
package com.cyanspring.common.event.strategy;

import com.cyanspring.common.event.RemoteAsyncEvent;

public class CancelMultiInstrumentStrategyEvent extends RemoteAsyncEvent {
	private String strategyId;
	private String txId;
	
	public CancelMultiInstrumentStrategyEvent(String key, String receiver,
			String strategyId, String txId) {
		super(key, receiver);
		this.strategyId = strategyId;
		this.txId = txId;
	}
	public String getStrategyId() {
		return strategyId;
	}
	public String getTxId() {
		return txId;
	}

}

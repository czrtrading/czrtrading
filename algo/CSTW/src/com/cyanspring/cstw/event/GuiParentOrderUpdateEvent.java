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
package com.cyanspring.cstw.event;

import com.cyanspring.common.business.ParentOrder;
import com.cyanspring.common.event.AsyncEvent;

public class GuiParentOrderUpdateEvent extends AsyncEvent {
	private ParentOrder order;

	public GuiParentOrderUpdateEvent(ParentOrder order) {
		super();
		this.order = order;
	}

	public ParentOrder getOrder() {
		return order;
	}
	
}

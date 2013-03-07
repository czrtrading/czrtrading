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

public abstract class MultiInstrumentStrategyReplyEvent extends RemoteAsyncEvent {
	private String txId;
	private boolean success;
	private String message;
	
	public MultiInstrumentStrategyReplyEvent(String key, String receiver,
			String txId, boolean success, String message) {
		super(key, receiver);
		this.txId = txId;
		this.success = success;
		this.message = message;
	}

	public String getTxId() {
		return txId;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}
	
}
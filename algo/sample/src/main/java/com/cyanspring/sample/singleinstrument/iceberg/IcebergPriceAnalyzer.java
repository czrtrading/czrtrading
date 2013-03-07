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
package com.cyanspring.sample.singleinstrument.iceberg;

import com.cyanspring.common.business.ParentOrder;
import com.cyanspring.common.marketdata.Quote;
import com.cyanspring.common.strategy.PriceAllocation;
import com.cyanspring.common.strategy.PriceInstruction;
import com.cyanspring.common.type.ExchangeOrderType;
import com.cyanspring.strategy.singleinstrument.AbstractPriceAnalyzer;
import com.cyanspring.strategy.singleinstrument.QuantityInstruction;
import com.cyanspring.strategy.singleinstrument.SingleInstrumentStrategy;

public class IcebergPriceAnalyzer extends AbstractPriceAnalyzer {

	@Override
	protected PriceInstruction calculate(QuantityInstruction qtyInstruction,
			SingleInstrumentStrategy strategy) {
		ParentOrder order = strategy.getParentOrder();
		Quote quote = strategy.getQuote();
		double price = order.getSide().isBuy()?quote.getBid():quote.getAsk();
		PriceInstruction pi = new PriceInstruction();
		pi.add(new PriceAllocation(order.getSymbol(), order.getSide(), price, qtyInstruction.getPassiveQty(), 
				ExchangeOrderType.LIMIT, strategy.getId()));
		return pi;
	}

}

package com.cyanspring.custom.strategy;

import com.cyanspring.common.strategy.PriceInstruction;
import com.cyanspring.strategy.singleinstrument.AbstractPriceAnalyzer;
import com.cyanspring.strategy.singleinstrument.QuantityInstruction;
import com.cyanspring.strategy.singleinstrument.SingleInstrumentStrategy;

public class CustomStrategy1PriceAnalyzer extends AbstractPriceAnalyzer {

	@Override
	protected PriceInstruction calculate(QuantityInstruction qtyInstruction,
			SingleInstrumentStrategy strategy) {
		// TODO implement your strategy logic on pricing here
		return null;
	}

}

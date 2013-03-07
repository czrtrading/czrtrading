package com.cyanspring.sample.custom;

import com.cyanspring.common.strategy.PriceInstruction;
import com.cyanspring.strategy.singleinstrument.AbstractPriceAnalyzer;
import com.cyanspring.strategy.singleinstrument.QuantityInstruction;
import com.cyanspring.strategy.singleinstrument.SingleInstrumentStrategy;

public class DummyPriceAnalyzer extends AbstractPriceAnalyzer {

	@Override
	protected PriceInstruction calculate(QuantityInstruction qtyInstruction,
			SingleInstrumentStrategy strategy) {
		// TODO Auto-generated method stub
		return null;
	}

}

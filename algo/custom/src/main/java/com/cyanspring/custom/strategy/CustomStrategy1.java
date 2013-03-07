package com.cyanspring.custom.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyanspring.strategy.singleinstrument.SingleInstrumentStrategy;

public class CustomStrategy1 extends SingleInstrumentStrategy {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory
			.getLogger(CustomStrategy1.class);
	
	public String getDescription() {
		return "This is a custom strategy";
	}
}

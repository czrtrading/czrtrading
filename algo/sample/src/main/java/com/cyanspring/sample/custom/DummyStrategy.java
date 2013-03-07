package com.cyanspring.sample.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyanspring.strategy.singleinstrument.SingleInstrumentStrategy;

public class DummyStrategy extends SingleInstrumentStrategy {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory
			.getLogger(DummyStrategy.class);
}

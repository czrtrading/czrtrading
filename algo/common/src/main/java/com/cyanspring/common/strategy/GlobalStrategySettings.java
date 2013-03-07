package com.cyanspring.common.strategy;

import java.util.List;
import com.cyanspring.common.business.FieldDef;

public class GlobalStrategySettings {
	private boolean checkAdjQuote = true;
	private List<String> singleInstrumentCommonDisplayFields;
	private List<String> multiInstrumentCommonDisplayFields;
	private List<String> multiInstrumentCommonInstrumentDisplayFields;
	private List<FieldDef> singleInstrumentCommonFieldDefs;
	private List<FieldDef> multiInstrumentCommonFieldDefs;
	private List<FieldDef> multiInstrumentCommonInstrumentFieldDefs;
	
	public boolean isCheckAdjQuote() {
		return checkAdjQuote;
	}

	public void setCheckAdjQuote(boolean checkAdjQuote) {
		this.checkAdjQuote = checkAdjQuote;
	}

	public List<String> getSingleInstrumentCommonDisplayFields() {
		return singleInstrumentCommonDisplayFields;
	}

	public void setSingleInstrumentCommonDisplayFields(
			List<String> singleInstrumentCommonDisplayFields) {
		this.singleInstrumentCommonDisplayFields = singleInstrumentCommonDisplayFields;
	}

	public List<String> getMultiInstrumentCommonDisplayFields() {
		return multiInstrumentCommonDisplayFields;
	}

	public void setMultiInstrumentCommonDisplayFields(
			List<String> multiInstrumentCommonDisplayFields) {
		this.multiInstrumentCommonDisplayFields = multiInstrumentCommonDisplayFields;
	}

	public List<FieldDef> getSingleInstrumentCommonFieldDefs() {
		return singleInstrumentCommonFieldDefs;
	}

	public void setSingleInstrumentCommonFieldDefs(
			List<FieldDef> singleInstrumentCommonFieldDefs) {
		this.singleInstrumentCommonFieldDefs = singleInstrumentCommonFieldDefs;
	}

	public List<FieldDef> getMultiInstrumentCommonFieldDefs() {
		return multiInstrumentCommonFieldDefs;
	}

	public void setMultiInstrumentCommonFieldDefs(
			List<FieldDef> multiInstrumentCommonFieldDefs) {
		this.multiInstrumentCommonFieldDefs = multiInstrumentCommonFieldDefs;
	}

	public List<String> getMultiInstrumentCommonInstrumentDisplayFields() {
		return multiInstrumentCommonInstrumentDisplayFields;
	}

	public void setMultiInstrumentCommonInstrumentDisplayFields(
			List<String> multiInstrumentCommonInstrumentDisplayFields) {
		this.multiInstrumentCommonInstrumentDisplayFields = multiInstrumentCommonInstrumentDisplayFields;
	}

	public List<FieldDef> getMultiInstrumentCommonInstrumentFieldDefs() {
		return multiInstrumentCommonInstrumentFieldDefs;
	}

	public void setMultiInstrumentCommonInstrumentFieldDefs(
			List<FieldDef> multiInstrumentCommonInstrumentFieldDefs) {
		this.multiInstrumentCommonInstrumentFieldDefs = multiInstrumentCommonInstrumentFieldDefs;
	}

	
}

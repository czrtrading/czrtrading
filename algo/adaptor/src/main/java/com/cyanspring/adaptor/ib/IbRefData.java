package com.cyanspring.adaptor.ib;

import com.cyanspring.common.staticdata.RefData;
import com.ib.client.Contract;

public class IbRefData extends RefData {
	Contract contract;

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
}

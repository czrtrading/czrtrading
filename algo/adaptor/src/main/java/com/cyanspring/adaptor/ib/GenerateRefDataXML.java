package com.cyanspring.adaptor.ib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.cyanspring.common.staticdata.RefData;
import com.ib.client.Contract;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GenerateRefDataXML extends RefData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XStream xstream = new XStream(new DomDriver());
		ArrayList<IbRefData> list;

		list = new ArrayList<IbRefData>();
		
		IbRefData refData;
		refData = new IbRefData();
		refData.setSymbol("C");
		Contract contract = new Contract();
		contract.m_symbol = "C";
		contract.m_secType = "STK";
		contract.m_currency = "USD";
		contract.m_exchange = "SMART";
		refData.contract = contract;
		list.add(refData);

		File file = new File("refdata/refData.xml");
		try {
			file.createNewFile();
			FileOutputStream os = new FileOutputStream(file);
			xstream.toXML(list, os);
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

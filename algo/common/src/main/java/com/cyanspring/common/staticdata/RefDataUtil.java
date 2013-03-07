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
package com.cyanspring.common.staticdata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class RefDataUtil {
	private static ArrayList<Double> getVolProfile() {
		ArrayList<Double> volProfile;
		volProfile = new ArrayList<Double>();
		volProfile.add(10.0);
		volProfile.add(18.0);
		volProfile.add(25.0);
		volProfile.add(27.0);
		volProfile.add(31.0);
		volProfile.add(36.0);
		volProfile.add(41.0);
		volProfile.add(45.0);
		volProfile.add(51.0);
		volProfile.add(57.0);

		volProfile.add(61.0);
		volProfile.add(63.0);
		volProfile.add(66.0);
		volProfile.add(69.0);
		volProfile.add(72.0);
		volProfile.add(76.0);
		volProfile.add(78.0);
		volProfile.add(83.0);
		volProfile.add(87.0);
		volProfile.add(100.0);

		return volProfile;
	}
	
	public static void main(String args[]) {
		XStream xstream = new XStream(new DomDriver());
		ArrayList<RefData> list;

		list = new ArrayList<RefData>();
		RefData refData;
		
		refData = new RefData();
		refData.symbol = "0005.HK";
		refData.avgBidAskSize = 200000;
		refData.avgTradeSize = 2000;
		refData.avgBidAskClearTime = 180;
		refData.avgPriceMovement = 2;
		refData.avgOpenVol = 500000;
		refData.avgCloseVol = 0;
		refData.lotSize = 400;
		refData.volProfile = getVolProfile();
		list.add(refData);
		
		refData = new RefData();
		refData.symbol = "0016.HK";
		refData.avgBidAskSize = 10000;
		refData.avgTradeSize = 1000;
		refData.avgBidAskClearTime = 300;
		refData.avgPriceMovement = 3;
		refData.avgOpenVol = 100000;
		refData.avgCloseVol = 0;
		refData.lotSize = 1000;
		refData.volProfile = getVolProfile();
		list.add(refData);

		refData = new RefData();
		refData.symbol = "1398.HK";
		refData.avgBidAskSize = 2000000;
		refData.avgTradeSize = 20000;
		refData.avgBidAskClearTime = 240;
		refData.avgPriceMovement = 0.3;
		refData.avgOpenVol = 5000000;
		refData.avgCloseVol = 0;
		refData.lotSize = 1000;
		refData.volProfile = getVolProfile();
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

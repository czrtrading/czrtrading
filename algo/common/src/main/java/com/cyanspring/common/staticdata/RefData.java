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

import java.util.ArrayList;

public class RefData {
	String symbol;
	int lotSize;
	String exchange;
	String currency;
	double avgTradeSize;
	double avgBidAskSize;
	double avgBidAskClearTime;
	double avgPriceMovement;
	double avgOpenVol;
	double avgCloseVol;
	double open; // yesterday's high/low/open/close
	double close;
	double high;
	double low;
	ArrayList<Double> volProfile;
	public double roundToLots(double qty) {
		if (qty > 0)
			return ((long)(qty/lotSize)) * lotSize;
		else
			return 0;
	}
	
	// getters
	public String getSymbol() {
		return symbol;
	}
	public int getLotSize() {
		return lotSize;
	}
	public String getExchange() {
		return exchange;
	}
	public String getCurrency() {
		return currency;
	}
	public double getAvgTradeSize() {
		return avgTradeSize;
	}
	public double getAvgBidAskSize() {
		return avgBidAskSize;
	}
	public double getAvgBidAskClearTime() {
		return avgBidAskClearTime;
	}
	public double getAvgPriceMovement() {
		return avgPriceMovement;
	}
	public double getAvgOpenVol() {
		return avgOpenVol;
	}
	public double getAvgCloseVol() {
		return avgCloseVol;
	}
	public ArrayList<Double> getVolProfile() {
		return volProfile;
	}
	
	// setters
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setLotSize(int lotSize) {
		this.lotSize = lotSize;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public void setAvgTradeSize(double avgTradeSize) {
		this.avgTradeSize = avgTradeSize;
	}
	public void setAvgBidAskSize(double avgBidAskSize) {
		this.avgBidAskSize = avgBidAskSize;
	}
	public void setAvgBidAskClearTime(double avgBidAskClearTime) {
		this.avgBidAskClearTime = avgBidAskClearTime;
	}
	public void setAvgPriceMovement(double avgPriceMovement) {
		this.avgPriceMovement = avgPriceMovement;
	}
	public void setAvgOpenVol(double avgOpenVol) {
		this.avgOpenVol = avgOpenVol;
	}
	public void setAvgCloseVol(double avgCloseVol) {
		this.avgCloseVol = avgCloseVol;
	}
	public void setVolProfile(ArrayList<Double> volProfile) {
		this.volProfile = volProfile;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}
	
	
}

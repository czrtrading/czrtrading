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
package com.cyanspring.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.cyanspring.common.Clock;

public class TimeUtil {
	public final static long millisInDay = 60 * 60 * 24 * 1000; 

	public static long getTimePass(Date time) {
		Date now = Clock.getInstance().now();
		return now.getTime() - time.getTime();
	}
	
	public static Date parseTime(String format, String time) throws ParseException {
		Calendar today, adjust;
		today = Calendar.getInstance();
		today.setTime(new Date());
		adjust = Calendar.getInstance();
		adjust.setTime(new SimpleDateFormat(format).parse(time));
		adjust.set(Calendar.YEAR, today.get(Calendar.YEAR));
		adjust.set(Calendar.MONTH, today.get(Calendar.MONTH));
		adjust.set(Calendar.DATE, today.get(Calendar.DATE));
		return adjust.getTime();
		
	}

	public static Date getOnlyDate(Date date) {
		long currentTime = date.getTime(); 
		long dateOnly = (currentTime / millisInDay) * millisInDay; 
		return new Date(dateOnly); 
	}
	
	public static boolean sameDate(long date1, long date2) {
		return date1/millisInDay == date2/millisInDay; 
	}
}

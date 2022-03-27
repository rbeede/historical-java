package com.rodneybeede.tools.historicalbackup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utilities {
	/**
	 * @param date If null uses new Date()
	 * @return Formatted date in UTC time zone
	 */
	public static String getFormattedDatestamp(final Date date) {
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		if(null == date) {
			return dateFormat.format(new Date()) + "_UTC";
		} else {
			return dateFormat.format(date) + "_UTC";
		}
	}
	
	
	
}

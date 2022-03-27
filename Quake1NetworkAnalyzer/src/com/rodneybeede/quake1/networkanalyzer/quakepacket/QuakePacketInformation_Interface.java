package com.rodneybeede.quake1.networkanalyzer.quakepacket;

import java.nio.charset.Charset;

public interface QuakePacketInformation_Interface {
	public static final String NEWLINE = System.getProperty("line.separator");
	public static Charset UTF8 = Charset.forName("UTF8");
	
	/**
	 * Implementations <b>MUST</b> call the super (parent) class's as well.  Example:<br />
	 * return super.getDisplayInformation() + QuakePacketInformation_Interface.NEWLINE + thisClassInformation;
	 * 
	 * @return One line string that describes the packet information with no newline at the end.  May be null which will display "null"
	 */
	public String getDisplayInformation();
}

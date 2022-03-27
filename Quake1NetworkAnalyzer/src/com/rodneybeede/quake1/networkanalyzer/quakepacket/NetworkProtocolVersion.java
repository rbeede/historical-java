package com.rodneybeede.quake1.networkanalyzer.quakepacket;


public enum NetworkProtocolVersion {
	qtest1((byte) 0x01),
	unknown((byte) 0x02),
	/**
	 * 0.91, 0.92, 1.00, 1.01
	 */
	release_0dot91_0dot92_1dot00_1dot01((byte) 0x03),
	;
	
	private final byte version;
	
	private NetworkProtocolVersion(final byte version) {
		this.version = version;
	}
	
	/**
	 * @param version value to lookup for known enum
	 * @return null if version doesn't match.  enum value otherwise
	 */
	public static NetworkProtocolVersion getBy(final byte version) {
		for(final NetworkProtocolVersion networkProtocolVersion : NetworkProtocolVersion.values()) {
			if(networkProtocolVersion.version == version)  return networkProtocolVersion;
		}
		
		return null;
	}
}	

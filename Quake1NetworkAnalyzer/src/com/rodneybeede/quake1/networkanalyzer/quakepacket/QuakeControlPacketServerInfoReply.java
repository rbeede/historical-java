package com.rodneybeede.quake1.networkanalyzer.quakepacket;


/**
 * String encoding is assumed ANSI ASCII
 * 
 * @author rbeede
 *
 */
public class QuakeControlPacketServerInfoReply extends QuakeControlPacket {
	private final String serverAddress;
	private final String hostName;
	private final String levelName;
	
	private final byte currentPlayers;
	private final byte maxPlayers;
	
	private final NetworkProtocolVersion networkProtocolVersion;

	public QuakeControlPacketServerInfoReply(final byte[] bytes, final int length) {
		super(bytes, length);
		
		if(!QuakeControlPacket.OPERATION_CODE.CCREP_SERVER_INFO.equals(this.getOperationCode())) {
			this.setInvalidReason("Provided packet is not of type " + QuakeControlPacketServerInfoReply.class.getName() + " but is instead " + this.getOperationCode());
			this.serverAddress = null;
			this.hostName = null;
			this.levelName = null;
			this.currentPlayers = Byte.MIN_VALUE;
			this.maxPlayers = Byte.MIN_VALUE;
			this.networkProtocolVersion = null;
			return;
		}
		

		if(this.getLength() < 15) {
			this.setInvalidReason("packet length is too short with value of " + this.getLength());
			this.serverAddress = null;
			this.hostName = null;
			this.levelName = null;
			this.currentPlayers = Byte.MIN_VALUE;
			this.maxPlayers = Byte.MIN_VALUE;
			this.networkProtocolVersion = null;
			return;
		}

		
		// We have some dynamic length strings to parse
		int idx = 5;  // starting at byte 6
		StringBuilder sb = new StringBuilder();
		
		// Starting at byte 6 to next null-terminating byte (0x00) is server_address ("LOCAL", IP:PORT, IPX, or Serial)
		for(/* we keep idx outside for later loops starting place */; 0x00 != bytes[idx] && idx < length; idx++) {
			sb.append((char) bytes[idx]);
		}
		idx++;  // Skip past end 0x00 null character
		
		this.serverAddress = sb.toString();
		
		sb = new StringBuilder();  // reset for next string
		
		
		// Next is host_name until next null-terminating byte
		for(/* we keep idx outside for later loops starting place */; 0x00 != bytes[idx] && idx < length; idx++) {
			sb.append((char) bytes[idx]);
		}
		idx++;  // Skip past end 0x00 null character

		this.hostName = sb.toString();
		
		sb = new StringBuilder();  // reset for next string

		
		// Next is level_name until next null-terminating byte
		for(/* we keep idx outside for later loops starting place */; 0x00 != bytes[idx] && idx < length; idx++) {
			sb.append((char) bytes[idx]);
		}
		idx++;  // Skip past end 0x00 null character

		this.levelName = sb.toString();
		
		sb = new StringBuilder();  // reset for next string
		
		
		// Single byte of current_players
		this.currentPlayers = bytes[idx];
		idx++;
		
		// Single byte of max_players
		this.maxPlayers = bytes[idx];
		idx++;
		
		// Single and last byte if network protocol version
		this.networkProtocolVersion = NetworkProtocolVersion.getBy(bytes[idx]);
		if(null == this.networkProtocolVersion) {
			this.setInvalidReason("undefined networkProtocolVersion " + bytes[idx]);
			return;
		}
		idx++;  // should now be same as length
		
		// Sanity check
		if(length != idx) {
			this.setInvalidReason("index for reading dynamic strings didn't end at last byte of packet!");
		}
	}
	
	public String getServerAddress() {
		return serverAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public String getLevelName() {
		return levelName;
	}

	public byte getCurrentPlayers() {
		return currentPlayers;
	}

	public byte getMaxPlayers() {
		return maxPlayers;
	}

	public NetworkProtocolVersion getNetworkProtocolVersion() {
		return networkProtocolVersion;
	}
	
	
	@Override
	public String getDisplayInformation() {
		return super.getDisplayInformation() + QuakePacketInformation_Interface.NEWLINE
				+ "server_address=" + this.getServerAddress()
				+ QuakePacketInformation_Interface.NEWLINE
				+ "host_name=" + this.getHostName()
				+ QuakePacketInformation_Interface.NEWLINE
				+ "level_name=" + this.getLevelName()
				+ QuakePacketInformation_Interface.NEWLINE
				+ "current_players=" + this.getCurrentPlayers()
				+ QuakePacketInformation_Interface.NEWLINE
				+ "max_players=" + this.getMaxPlayers()
				+ QuakePacketInformation_Interface.NEWLINE
				+ "net_protocol_version=" + this.getNetworkProtocolVersion();
	}	
}

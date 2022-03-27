package com.rodneybeede.quake1.networkanalyzer.quakepacket;


public class QuakeControlPacketConnectionRequest extends QuakeControlPacket {
	private final NetworkProtocolVersion networkProtocolVersion;

	public QuakeControlPacketConnectionRequest(final byte[] bytes, final int length) {
		super(bytes, length);
		
		if(!QuakeControlPacket.OPERATION_CODE.CCREQ_CONNECT.equals(this.getOperationCode())) {
			this.setInvalidReason("Provided packet is not of type " + QuakeControlPacketConnectionRequest.class.getName() + " but is instead " + this.getOperationCode());
			this.networkProtocolVersion = null;
			return;
		}
		

		if(this.getLength() < 12) {
			this.setInvalidReason("packet length is too short with value of " + this.getLength());
			this.networkProtocolVersion = null;
			return;
		}

		
		// bytes 6 - 11 are the null-terminated string "QUAKE"
		
		// byte 12 is the network protocol version
		this.networkProtocolVersion = NetworkProtocolVersion.getBy(bytes[11]);
		if(null == this.networkProtocolVersion) {
			this.setInvalidReason("undefined networkProtocolVersion " + bytes[11]);
			return;
		}
	}
	
	
	public NetworkProtocolVersion getNetworkProtocolVersion() {
		return networkProtocolVersion;
	}
	
	
	@Override
	public String getDisplayInformation() {
		return super.getDisplayInformation() + QuakePacketInformation_Interface.NEWLINE + "net_protocol_version=" + this.getNetworkProtocolVersion();
	}	
}

package com.rodneybeede.quake1.networkanalyzer.quakepacket;


public class QuakeControlPacketConnectionRequestReplyAccept extends QuakeControlPacket {
	/**
	 * Server allocated port for client to use
	 */
	private final int udpPort;  // int instead of short because Quake short is unsigned
	private final int unknownPurpose;

	public QuakeControlPacketConnectionRequestReplyAccept(final byte[] bytes, final int length) {
		super(bytes, length);
		
		if(!QuakeControlPacket.OPERATION_CODE.CCREP_ACCEPT.equals(this.getOperationCode())) {
			this.setInvalidReason("Provided packet is not of type " + QuakeControlPacketConnectionRequestReplyAccept.class.getName() + " but is instead " + this.getOperationCode());
			this.udpPort = Integer.MIN_VALUE;
			this.unknownPurpose = Integer.MIN_VALUE;
			return;
		}
		

		if(this.getLength() < 9) {
			this.setInvalidReason("packet length is too short with value of " + this.getLength());
			this.udpPort = Integer.MIN_VALUE;
			this.unknownPurpose = Integer.MIN_VALUE;
			return;
		}

		
		// bytes 6 - 7 are the UDP port in *LITTLE ENDIAN (LSB FIRST)* order
		this.udpPort = bytesToInteger(bytes[6], bytes[5]);  // switch the order as the method expects MSB
		
		// bytes 8 - 9 have no known purpose
		//FIXME review Quake source code to see what this is for, might just be padding?
		this.unknownPurpose = bytesToInteger(bytes[7], bytes[8]);
	}
	
	
	public int getUDPPort() {
		return udpPort;
	}
	
	
	@Override
	public String getDisplayInformation() {
		return super.getDisplayInformation() + QuakePacketInformation_Interface.NEWLINE + "UDP port=" + this.getUDPPort() + " unknownAsShort=" + this.unknownPurpose;
	}	
}

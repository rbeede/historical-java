package com.rodneybeede.quake1.networkanalyzer.quakepacket;



public class QuakeControlPacketConnectionRequestReplyReject extends QuakeControlPacket {
	private final String reason;
	
	public QuakeControlPacketConnectionRequestReplyReject(final byte[] bytes, final int length) {
		super(bytes, length);
		
		if(!QuakeControlPacket.OPERATION_CODE.CCREP_REJECT.equals(this.getOperationCode())) {
			this.setInvalidReason("Provided packet is not of type " + QuakeControlPacketConnectionRequestReplyReject.class.getName() + " but is instead " + this.getOperationCode());
			this.reason = null;
			return;
		}
		

		if(this.getLength() < 6) {
			this.setInvalidReason("packet length is too short with value of " + this.getLength());
			this.reason = null;
			return;
		}

		
		// bytes 6 - length of packet is reason why Quake server rejected client.  Last byte will be null character
		// Encoding is just ANSI ASCII
		this.reason = new String(bytes, 5, length - 5, QuakePacketInformation_Interface.UTF8);
	}
	
	
	public String getReason() {
		return reason;
	}
	
	
	@Override
	public String getDisplayInformation() {
		return super.getDisplayInformation() + QuakePacketInformation_Interface.NEWLINE + "reason=" + this.getReason();
	}	
}

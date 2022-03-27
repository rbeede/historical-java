package com.rodneybeede.quake1.networkanalyzer.quakepacket;


public class QuakeControlPacket extends QuakePacket {
	public enum OPERATION_CODE {
		CCREQ_CONNECT((byte) 0x01),
		CCREP_ACCEPT((byte) 0x81),
		CCREP_REJECT((byte) 0x82),
		CCREQ_SERVER_INFO((byte) 0x02),
		CCREP_SERVER_INFO((byte) 0x83),
		CCREQ_PLAYER_INFO((byte) 0x03),
		CCREP_PLAYER_INFO((byte) 0x84),
		CCREQ_RULE_INFO((byte) 0x04),
		CCREP_RULE_INFO((byte) 0x85),
		;
		
		private final byte operationCode;
		
		private OPERATION_CODE(final byte operationCode) {
			this.operationCode = operationCode;
		}
		
		/**
		 * @param operationCode value to lookup for known Quake packet types
		 * @return null if code doesn't match any known types; packet type otherwise
		 */
		public static OPERATION_CODE getBy(final byte operationCode) {
			for(final OPERATION_CODE operationCodeEnum : OPERATION_CODE.values()) {
				if(operationCodeEnum.operationCode == operationCode)  return operationCodeEnum;
			}
			
			return null;
		}
	}

	
	private final OPERATION_CODE operationCode;
	

	public QuakeControlPacket(final byte[] bytes, final int length) {
		super(bytes, length);
		
		if(!QuakePacket.TYPE.CONTROL.equals(this.getType())) {
			this.setInvalidReason("Provided packet is not of type " + QuakeControlPacket.class.getName() + " but is instead " + this.getType());
			this.operationCode = null;
			return;
		}
		
		if(this.getLength() < 5) {
			this.setInvalidReason("packet length is too short with value of " + this.getLength());
			this.operationCode = null;
			return;
		}
		

		this.operationCode = OPERATION_CODE.getBy(bytes[4]);  // fifth byte in packet
		if(null == this.operationCode) {
			this.setInvalidReason("unknown operation code " + bytes[4]);
			return;
		}
	}
	
	
	public OPERATION_CODE getOperationCode() {
		return operationCode;
	}
	
	
	@Override
	public String getDisplayInformation() {
		return super.getDisplayInformation() + QuakePacketInformation_Interface.NEWLINE + "operationCode=" + this.getOperationCode();
	}	
}

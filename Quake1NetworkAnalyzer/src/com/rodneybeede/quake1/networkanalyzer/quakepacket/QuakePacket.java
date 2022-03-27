package com.rodneybeede.quake1.networkanalyzer.quakepacket;


/**
 * Unofficial Quake Network Protocol Specs v1.01b
 * http://www.gamers.org/dEngine/quake/QDP/qnp.html
 * 
 * Packet bytes are big-endian (MSB)
 * 
 * Sub-classes may use {@link #setInvalidReason(String)} if they find problems while parsing
 * 
 * @author rbeede
 *
 */
public class QuakePacket implements QuakePacketInformation_Interface {
	public enum TYPE {
		CONTROL((short) 0x8000),
		RELIABLE_MESSAGE_BLOCK((short) 0x0001),
		RELIABLE_END_MESSAGE_BLOCK((short) 0x0009),
		RELIABLE_ACKNOWLEDGEMENT((short) 0x0002),
		UNRELIABLE((short) 0x0010)
		;
		
		private final short code;
		
		private TYPE(final short code) {
			this.code = code;
		}
		
		/**
		 * @param code value to lookup for known Quake packet types
		 * @return null if code doesn't match any known types; packet type otherwise
		 */
		public static TYPE getBy(final short code) {
			for(final TYPE type : TYPE.values()) {
				if(type.code == code)  return type;
			}
			
			return null;
		}
	}
	
	private final TYPE type;
	private final int length;  // Java int because Java short (16 bit) is signed and Quake short seem to assume unsigned
	
	private String invalidReason = null;
	
	
	/**
	 * bytes.length may be larger than actual Quake packet length.  Use length parameter for packet length as received'
	 * from socket.  Also the Quake packet header defines the length of the Quake packet (header + data)
	 * 
	 * If the packet is invalid then the reason will be set and accessible via {@link #getInvalidReason()}
	 * 
	 * @param bytes buffer that contains the packet data
	 * @param length real length of the actual Quake packet in the buffer bytes
	 */
	public QuakePacket(final byte[] bytes, final int length) {
		if(null == bytes || length < 4) {
			this.setInvalidReason("bytes were null or length was too small (" + length + ")");
			this.type = null;
			this.length = Integer.MIN_VALUE;
			return;
		}
		
		
		// First two bytes are the packet type
		this.type = TYPE.getBy(bytesToShort(bytes[0], bytes[1]));
		if(null == this.type) {
			this.setInvalidReason("unknown packet type code " + bytesToShort(bytes[0], bytes[1]));
			this.length = Integer.MIN_VALUE;
			return;
		}
		
		
		// Next 2 bytes are the Quake packet length
		this.length = bytesToInteger(bytes[2], bytes[3]);
		
		if(this.length != length) {
			this.setInvalidReason("Network packet read length of " + length + " didn't match Quake packet length field value of " + this.length);
			return;
		}
	}
	
	
	/**
	 * @return null if packet is valid.  String with invalid reason otherwise.
	 */
	public String getInvalidReason() {
		return invalidReason;
	}
	
	public boolean isValid() {
		return null == this.invalidReason;
	}
	
	/**
	 * Useful by sub-classes when parsing a packet.  Supports concurrency.
	 * 
	 * If not already null and called multiple times later calls will be ignored (first reason wins)
	 * 
	 * @param reason null value indicates valid.  Anything else indicates invalid.
	 */
	protected synchronized void setInvalidReason(final String reason) {
		if(null == this.invalidReason) {
			this.invalidReason = reason;
		}
	}
	
	
	public TYPE getType() {
		return type;
	}
	
	
	public int getLength() {
		return length;
	}
	
	
	/**
	 * Friendly formatted properties of this packet
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return getDisplayInformation();
	}
	
	
	/**
	 * Preferred usage is {@link #toString()}
	 * @see com.rodneybeede.quake1.networkanalyzer.quakepacket.QuakePacketInformation_Interface#getDisplayInformation()
	 */
	@Override
	public String getDisplayInformation() {
		return (null == this.invalidReason ? "" : this.invalidReason + " ")
				+ "type=" + this.type.toString()
				+ " len=" + this.length
				;
	}
	
	
	/**
	 * Assumes big-endian (MSB) order
	 * 
	 * WARNING that the short is signed so if you want unsigned consider an int
	 * 
	 * @param byte1
	 * @param byte2
	 * @return
	 */
	protected static short bytesToShort(final byte byte1, final byte byte2) {
		short conversion = 0;
		conversion |= byte1 & 0xFF;
		conversion <<= 8;
		conversion |= byte2 & 0xFF;
		return conversion;
	}
	
	
	/**
	 * Assumes big-endian (MSB) order.  For little-endian (LSB) flip your passed arguments
	 * 
	 * Useful also if you want to represent an unsigned short (16 bits) which needs a Java int (32 bits) to avoid negative looking values
	 * 
	 * @param byte1
	 * @param byte2
	 * @return
	 */
	protected static int bytesToInteger(final byte byte1, final byte byte2) {
		int conversion = 0;
		conversion |= byte1 & 0xFF;
		conversion <<= 8;
		conversion |= byte2 & 0xFF;
		return conversion;
	}

}

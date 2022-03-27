package com.rodneybeede.quake1.networkanalyzer;

import java.net.SocketAddress;

import com.rodneybeede.quake1.networkanalyzer.quakepacket.QuakeControlPacket;
import com.rodneybeede.quake1.networkanalyzer.quakepacket.QuakeControlPacketConnectionRequest;
import com.rodneybeede.quake1.networkanalyzer.quakepacket.QuakeControlPacketConnectionRequestReplyAccept;
import com.rodneybeede.quake1.networkanalyzer.quakepacket.QuakeControlPacketConnectionRequestReplyReject;
import com.rodneybeede.quake1.networkanalyzer.quakepacket.QuakeControlPacketServerInfoReply;
import com.rodneybeede.quake1.networkanalyzer.quakepacket.QuakeControlPacketServerInfoRequest;
import com.rodneybeede.quake1.networkanalyzer.quakepacket.QuakePacket;

public class QuakePacketViewerUtility {
	/**
	 * It is common for the buffer to be larger than the actual content inside of it for convenience in how the network
	 * packets are read.  In addition the Quake packet header itself has a length field.
	 * 
	 * @param bytes Raw buffer bytes
	 * @param length Actual length of real content in the buffer 
	 * @param fromClient true = packet from Quake client; false = packet from Quake server
	 */
	public static void display(final byte[] bytes, final int length, final boolean fromClient, final SocketAddress originatorAddress) {
		final QuakePacket quakePacket = bytesToDetailedQuakePacket(bytes, length);

		System.out.println("Data from Quake " + (fromClient ? "client" : "server") + " at address " + originatorAddress.toString());
		System.out.println(quakePacket.getClass().getSimpleName());
		System.out.println(quakePacket.toString());
		System.out.println();
	}
	
	
	/**
	 * This reads all the types and sub-types for the Quake Packet data and returns a concrete sub-type instance
	 * 
	 * Note that if the packet bytes generate an invalid QuakePacket you may not get a definitive sub-type but a more general type instead.  You can check with {@link QuakePacket#isValid()}
	 * 
	 * @param bytes
	 * @param length
	 * @return
	 */
	private static QuakePacket bytesToDetailedQuakePacket(final byte[] bytes, final int length) {
		final QuakePacket quakePacket = new QuakePacket(bytes, length);
		
		if(!quakePacket.isValid()) {
			return quakePacket;
		}
		
		switch(quakePacket.getType()) {
			case CONTROL:
				// Call another method to do qualifier on QuakeControlPacket
				
				return qcpToDetailed(new QuakeControlPacket(bytes, length), bytes, length);
				
				
			//TODO implement the rest of the types
				
			default:
				// Program code bug where we didn't add the new type
				assert(true==false);
				throw new RuntimeException("Unhandled code for enum type " + quakePacket.getType());
		}
	}
	
	
	private static QuakeControlPacket qcpToDetailed(final QuakeControlPacket qcp, final byte[] bytes, final int length) {
		if(!qcp.isValid()) {
			return qcp;
		}

		switch(qcp.getOperationCode()) {
			case CCREQ_CONNECT:
				return new QuakeControlPacketConnectionRequest(bytes, length);
			case CCREP_ACCEPT:
				return new QuakeControlPacketConnectionRequestReplyAccept(bytes, length);
			case CCREP_REJECT:
				return new QuakeControlPacketConnectionRequestReplyReject(bytes, length);
			case CCREQ_SERVER_INFO:
				return new QuakeControlPacketServerInfoRequest(bytes, length);
			case CCREP_SERVER_INFO:
				return new QuakeControlPacketServerInfoReply(bytes, length);
				
			//TODO implement the rest of the types
			default:
				// Program code bug where we didn't add the new type
				assert(true==false);
				throw new RuntimeException("Unhandled code for enum type " + qcp.getOperationCode());				
		}
	}
}

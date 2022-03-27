package com.rodneybeede.software.socketsniffer;


/**
 * Your must make sure your implementation supports concurrency
 * 
 * @author rbeede
 *
 */
public interface SnifferListener {
	/**
	 * Useful for outputting sniffed data between client and server.
	 * 
	 * Called whenever any amount of bytes (usually a buffer block) are seen from the client or server.
	 * 
	 * It is possible that communication could be occurring in both directions at the same time.
	 * 
	 * Note that data may be only a fragment of an communication message.  It depends on the protocol of the client and server.
	 * 
	 * @param data Raw bytes that were seen in transmission
	 * @param fromClient true indicates client sending to server.  false indicates server sending to client.
	 */
	public void onNetworkTraffic(final byte[] data, final boolean fromClient);
	
	
	/**
	 * When either the client -> relay is closed or destination (server) -> relay is closed
	 */
//	public void onNetworkClose();
	//TODO consider implementing this and onNetworkConnect
}

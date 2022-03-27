package com.rodneybeede.software.socketsniffer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;


public class TCPNetworkRelay implements Runnable {
	public static final int CONNECT_TIMEOUT_SECONDS = 30;
	
	
	private final Socket sourceSocket;
	private final Socket destinationSocket;
	private final SnifferListener snifferListener;
	
	public TCPNetworkRelay(final Socket sourceSocket, final InetSocketAddress destinationSocketAddress, final SnifferListener snifferListener) throws IOException {
		super();
		
		this.sourceSocket = sourceSocket;
		this.destinationSocket = new Socket();
		this.destinationSocket.connect(destinationSocketAddress, CONNECT_TIMEOUT_SECONDS * 1000);
		this.snifferListener = snifferListener;
	}

	
	@Override
	public void run() {
		// We spawn yet two more threads to do the actual relay work
		// When they both return the connection is no more
			
		// Read from sourceSocket (client to sniffer -> server)
		final Thread srcToDestRelay;
		try {
			srcToDestRelay = new Thread(new StreamRelay(this.sourceSocket.getInputStream(), this.destinationSocket.getOutputStream(), this.snifferListener, true));
		} catch(final IOException excep) {
			//TODO report somehow the disconnect before relay even started?
			return;
		}
		
			
		// Read from destinationSocket (server to sniffer -> client)
		final Thread destToSrcRelay;
		try {
			destToSrcRelay = new Thread(new StreamRelay(this.destinationSocket.getInputStream(), this.sourceSocket.getOutputStream(), this.snifferListener, false));
		} catch(final IOException excep) {
			//TODO report somehow the disconnect before relay even started?
			return;
		}

		
		srcToDestRelay.start();
		destToSrcRelay.start();
		
		// Wait on both to finish (commonly marked by an IO error)
		try {
			srcToDestRelay.join();
			destToSrcRelay.join();
		} catch(final InterruptedException e) {
			// Forced shutdown okay
			return;
		}
	}
	
	
	private class StreamRelay implements Runnable {
		private final InputStream in;
		private final OutputStream out;
		private final boolean isClientSource;
		
		public StreamRelay(final InputStream in, final OutputStream out, final SnifferListener snifferListener, final boolean isClientSource) {
			this.in = in;
			this.out = out;
			this.isClientSource = isClientSource;
		}

		@Override
		public void run() {
			final byte[] buffer = new byte[GlobalConstants.BUFFER_SIZE];
			
			int count = -1;
			
			try {
				for(count = in.read(buffer); -1 != count; count = in.read(buffer)) {
					// Echo it back to the other end
					out.write(buffer, 0, count);
					// Notify listener too
					if(null != snifferListener) {
						//FIXME possible performance improvement would be no copied buffer.  Possibly using SocketChannel and ByteBuffer instead
						snifferListener.onNetworkTraffic(Arrays.copyOf(buffer, count), this.isClientSource);
					}
				}
			} catch(final IOException e) {
				//TODO decide something better todo or just admit disconnect?
				return;
			}
		}
	}
}

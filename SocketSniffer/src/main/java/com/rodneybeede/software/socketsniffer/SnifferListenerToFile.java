/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodneybeede.software.socketsniffer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author rbeede
 */
public class SnifferListenerToFile implements SnifferListener {
	private final FileOutputStream fos;

	public SnifferListenerToFile(final File outputFile) throws FileNotFoundException {
		this.fos = new FileOutputStream(outputFile.getAbsolutePath());
	}
	
	@Override
	public void onNetworkTraffic(final byte[] data, final boolean fromClient) {		
		final byte[] indicator = new String("\r\nINDICATOR - from " + (fromClient ? "CLIENT" : "SERVER") + "\r\n").getBytes(GlobalConstants.UTF8);
		
		
		try {
			fos.write(indicator);
			fos.write(data);
		} catch (final IOException ex) {
			System.err.println(ex.getMessage());
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		
		this.fos.close();
	}
}

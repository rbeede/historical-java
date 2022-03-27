package com.rodneybeede.tools.historicalbackup;

import java.io.File;
import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Main {
	private static final Logger log = Logger.getLogger(Main.class);
	
	private static volatile boolean shutdownFlag = false;
	

	public static void main(final String[] args) throws IOException, ConfigurationException {
		// Parse command line arguments
		if(null == args || args.length != 1) {
			System.err.println("Insuffient args.  Usage:  java " + Main.class.getName() + "  /path/to/config.xml");
			System.err.println();
			System.err.println("Sample config.xml:");
			
			final XMLConfiguration config = new XMLConfiguration();
			config.setProperty(Configuration.OPTION_KEY_LOG_FILE, "/path/to/your.log (beginning truncated after certain size)");
			config.setProperty(Configuration.OPTION_KEY_BACKUP_SOURCE_DIRECTORIES, new File[] {new File("/path/to/src1"), new File("/path/to/src2")});
			config.setProperty(Configuration.OPTION_KEY_BACKUP_DESTINATION, new File("/path/to/dest/dir"));
			
			config.save(System.err);
			
			System.exit(255);
		}
		

		// Parse config file
		final File configFile = (new File(args[0])).getCanonicalFile();
		final Configuration config = Configuration.getInstance();
		config.load(configFile);
		
		
		setupLogging(config.getLogFile());

		log.debug("Log file stored in " + config.getLogFile().getPath());
		log.info("Destination base directory is " + config.getBackupDestination());
		for(final File file : config.getBackupSources()) {
			log.info("Backup source directory:  " + file.getPath());
		}

		
		// Setup and start our worker threads (all low priority)
		//TODO thread 1	watch source directory for mods
		//TODO	thread 2	review all source directories every 4 hours
		//TODO	thread 3	actual backup worker with global queue
		//TODO	thread	4	deleted files finder
		
		
		// This thread simply waits until a shutdown signal is given
		try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (final InterruptedException e) {
			// Should have shutdown flag set
			
		}
	}
	
	
	private static void setupLogging(final File logFile) throws IOException {
		final Layout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss,SSS Z}\t%-5p\tThread=%t\t%c\t%m%n");
		
		// Setup the logger to also log to the console
		final ConsoleAppender consoleAppender = new ConsoleAppender(layout);
		consoleAppender.setEncoding("UTF-8");
		consoleAppender.setThreshold(Level.INFO);  // Cuts back on so many messages on the console, but the file still has everything else
		Logger.getRootLogger().addAppender(consoleAppender);
		
		
		// Setup the logger to log into the specified log file
		final FileAppender fileAppender = new FileAppender(layout, logFile.getPath());

		fileAppender.setEncoding("UTF-8");
		Logger.getRootLogger().addAppender(fileAppender);
		Logger.getRootLogger().setLevel(Level.ALL);  // Appenders may have their own thresholds different from root
	}
	
	
	public static void start() {
		// Pause for 30 seconds to allow booting system to finish initialization before being busy with backup
		// Do the pause before calling main
		try {
			Thread.sleep(30 * 1000);
		} catch (final InterruptedException e) {
			// Must assume request to abort start
			System.exit(Integer.MIN_VALUE);
		}
		
		// TODO call main
		
	
	}
	
	
	public static void shutdown() {
		// TODO interrupt thread sleep somehow
		Main.shutdownFlag = true;
		Thread.currentThread().interrupt();  // FIXME need sure fire thread selection
	}
	
	
}

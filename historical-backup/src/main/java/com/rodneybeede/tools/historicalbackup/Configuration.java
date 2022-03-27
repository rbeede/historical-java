package com.rodneybeede.tools.historicalbackup;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.xml.bind.JAXBContext;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public enum Configuration {
	INSTANCE;  // Singleton
	
	public static Configuration getInstance() {
		return Configuration.INSTANCE;
	}
	
	public static final String OPTION_KEY_LOG_FILE = "LOG_FILE";
	public static final String OPTION_KEY_BACKUP_SOURCE_DIRECTORIES = "BACKUP_SOURCE_DIRECTORIES";
	public static final String OPTION_KEY_BACKUP_DESTINATION = "BACKUP_DESTINATION";
	
	
	private File logFile;
	
	public File getLogFile() {
		return logFile;
	}
	public synchronized void setLogFile(final File logFile) {
		this.logFile = logFile;
	}
	
	
	private File backupDestination;
	public File getBackupDestination() {
		return backupDestination;
	}
	public synchronized void setBackupDestination(final File backupDestination) {
		this.backupDestination = backupDestination;
	}
	
	
	private Collection<File> backupSources;  // unmodifiableCollection
	/**
	 * @return {@link java.util.Collections#unmodifiableCollection(Collection)} of the backup sources
	 */
	public Collection<File> getBackupSources() {
		return backupSources;
	}
	/**
	 * @param backupSources Array of backup sources.  A copy is made so changes to the passed array are not reflected.
	 */
	public synchronized void setBackupSources(final File[] backupSources) {
		final File[] copyOfArray;
		if(null == backupSources) {
			copyOfArray = new File[0];
		} else {
			copyOfArray = java.util.Arrays.copyOf(backupSources, backupSources.length);
		}
		
		this.backupSources = java.util.Collections.unmodifiableCollection(java.util.Arrays.asList(copyOfArray));
	}
	
	
	public void load(final File configFile) throws ConfigurationException, IOException {
		System.out.println("Loading configuration file at " + configFile.getPath());  // may not have logger yet
		
		final XMLConfiguration config = new XMLConfiguration();
		config.load(configFile);
		
		this.setLogFile(((File) config.getProperty(Configuration.OPTION_KEY_LOG_FILE)).getCanonicalFile());
		this.setBackupDestination(((File) config.getProperty(Configuration.OPTION_KEY_BACKUP_DESTINATION)).getCanonicalFile());
		
		File[] backupSources = (File[]) config.getProperty(Configuration.OPTION_KEY_BACKUP_SOURCE_DIRECTORIES);
		for(int i = 0; i < backupSources.length; i++) {
			backupSources[i] = backupSources[i].getCanonicalFile();
		}
FIXME 	Use Java 1.6+ built-in  javax.xml.bind.JAXBContext 
Ditch the Apache one as it doesn't handle object conversion for you automagically

		this.setBackupSources(backupSources);
	}
}

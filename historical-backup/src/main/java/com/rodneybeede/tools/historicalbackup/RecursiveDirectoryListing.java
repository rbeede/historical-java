package com.rodneybeede.tools.historicalbackup;

import java.io.File;
import java.io.FileFilter;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;

public class RecursiveDirectoryListing implements Callable<Set<File>> {
	private final File root;
	private final boolean includeDirectories;
	private final FileFilter fileFilter;
	
	/**
	 * @param root Directory to recurse
	 * @param includeDirectories If true all directories including root are included in the results.  If false only non-directories are included.
	 * @param fileFilter Filters out directories/files/entries that should be skipped/included.  May be null.
	 */
	public RecursiveDirectoryListing(final File root, final boolean includeDirectories, final FileFilter fileFilter) {
		this.root = root;
		this.includeDirectories = includeDirectories;
		this.fileFilter = fileFilter;
	}
	
	@Override
	public Set<File> call() throws Exception {
		final Set<File> files = new TreeSet<File>();
		
		recurse(this.root, files);
		
		return files;
	}
	
	
	private void recurse(final File directory, final Set<File> listing) {
		if(null == directory || null == listing)  return;
		
		if(this.includeDirectories) {
			// Check and catch this here since recursive calls pass in directories that need to be added to the listing 
			listing.add(directory);
		}
		
		final File[] dirEntries = directory.listFiles(this.fileFilter);
		
		if(null == dirEntries)  return;  //wasn't a directory

		for(final File dirEntry : dirEntries) {
			// Only add non-directory entries, if this.includeDirectories is set the directory entry will be added in the recursion call
			if(!dirEntry.isDirectory()) {
				listing.add(dirEntry);
			} else {
				recurse(dirEntry, listing);
			}
		}
	}

}

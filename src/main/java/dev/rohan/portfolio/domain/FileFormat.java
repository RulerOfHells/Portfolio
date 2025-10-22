package dev.rohan.portfolio.domain;

import java.io.File;

public class FileFormat {
	private final File file;
	private String mimeType;
	
	public FileFormat(File file, String mimeType) {
		this.file = file;
		this.mimeType = mimeType;
	}
	
	public File getFile() {
		return file;
	}
	
	public String getMimeType() {
		return mimeType;
	}
}

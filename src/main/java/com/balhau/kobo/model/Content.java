package com.balhau.kobo.model;


/**
 * This should have all common properties to the several kind of contents
 * For example we got books and pocket articles, these have common fields so
 * they should inherit from this abstract class
 * @author balhau
 *
 */
public abstract class Content{
	
	protected String contentID;
	protected int contentType;
	protected String mimeType;
	protected String dateLastRead;
	protected String dateAdded;
	protected int fileSize;
	
	public String getContentID() {
		return contentID;
	}
	public void setContentID(String contentID) {
		this.contentID = contentID;
	}
	public int getContentType() {
		return contentType;
	}
	public void setContentType(int contentType) {
		this.contentType = contentType;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getDateLastRead() {
		return dateLastRead;
	}
	public void setDateLastRead(String dateLastRead) {
		this.dateLastRead = dateLastRead;
	}
	public String getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	
	
	
	
} 

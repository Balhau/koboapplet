package com.balhau.kobo.model;


/**
 * Content class to hold information about a book stored in kobo reader
 * @author <a href="mailto:balhau@balhau.net">Balhau</a>
 * <p>17 de Fev de 2014</p>
 */
public class KoboBook {
	private String bookTitle;
	private String bookID;
	private int contentType;
	private String mimeType;
	private int percentageReaded;
	private String dateLastRead;
	
	public KoboBook(){
		
	}

	public KoboBook(String bookTitle, String bookID,int contentType,String mimeType,int percentageReaded) {
		this.bookTitle = bookTitle;
		this.bookID = bookID;
		this.contentType=contentType;
		this.mimeType=mimeType;
		this.percentageReaded=percentageReaded;
		this.setDateLastRead(null);
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public int getPercentageReaded() {
		return percentageReaded;
	}

	public void setPercentageReaded(int percentageReaded) {
		this.percentageReaded = percentageReaded;
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
	
	
}

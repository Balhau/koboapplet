package com.balhau.kobo.model;


/**
 * Content class to hold information about a book stored in kobo reader
 * @author <a href="mailto:balhau@balhau.net">Balhau</a>
 * <p>17 de Fev de 2014</p>
 */
public class Book extends Content implements BaseModel{
	
	private String bookTitle;
	private int percentageReaded;
	
	public Book(){
		
	}

	public Book(String bookTitle, String bookID,int contentType,String mimeType,int percentageReaded) {
		this.bookTitle = bookTitle;
		this.contentID = bookID;
		this.contentType=contentType;
		this.mimeType=mimeType;
		this.percentageReaded=percentageReaded;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public int getPercentageReaded() {
		return percentageReaded;
	}

	public void setPercentageReaded(int percentageReaded) {
		this.percentageReaded = percentageReaded;
	}
	
	public String toString(){
		String out="BOOK[";
		out+=contentID+", "+bookTitle+", ";
		out+=contentType+", "+dateLastRead+", "+percentageReaded;
		out+="]";
		return out;
	}
	
	
}

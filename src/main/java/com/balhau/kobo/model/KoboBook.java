package com.balhau.kobo.model;

/**
 * Content class to hold information about a book stored in kobo reader
 * @author <a href="mailto:balhau@balhau.net">Balhau</a>
 * <p>17 de Fev de 2014</p>
 */
public class KoboBook {
	private String bookTitle;
	private String bookID;
	private int percentageReaded;
	
	public KoboBook(){
		
	}

	public KoboBook(String bookTitle, String bookID,int percentageReaded) {
		this.bookTitle = bookTitle;
		this.bookID = bookID;
		this.percentageReaded=percentageReaded;
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
	
	
}

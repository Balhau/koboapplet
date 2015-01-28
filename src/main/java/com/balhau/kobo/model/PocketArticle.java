package com.balhau.kobo.model;

/**
 * This represents the information relative to a PocketArticle that is presented in the
 * Kobo database.
 * @author balhau
 *
 */
public class PocketArticle extends Content implements BaseModel{
	
	private String title;
	private String attribution;
	private String description;
	private int pocketStatus;
	private int wordCount;
	private String imageURL;
	private String contentURL;
	private int readStatus;
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAttribution() {
		return attribution;
	}
	public void setAttribution(String attribution) {
		this.attribution = attribution;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPocketStatus() {
		return pocketStatus;
	}
	public void setPocketStatus(int pocketStatus) {
		this.pocketStatus = pocketStatus;
	}
	public int getWordCount() {
		return wordCount;
	}
	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public int getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(int readStatus) {
		this.readStatus = readStatus;
	}
	public String getContentURL() {
		return contentURL;
	}
	public void setContentURL(String contentURL) {
		this.contentURL = contentURL;
	}
	
	
	
}

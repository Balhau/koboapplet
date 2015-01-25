package com.balhau.kobo.model;

import java.util.Date;

/**
 * This is a wrapper arround a achivement table on kobo storage data. This will encapsulate the data that we consider meaningfull around the concept Achievement
 * @author <a href="mailto:balhau@balhau.net">Balhau</a>
 * <p>15 de Fev de 2014</p>
 */
public class KoboAchievement implements BaseModel{
	private String completeDescription;
	private String eventLogDescription;
	private String incompleteDescription;
	private String dateCreated;
	private String imageId;
	private String name;
	private int percentComplete;
	private String userID;
	
	
	
	public KoboAchievement(String completeDescription,
			String eventLogDescription, String incompleteDescription,
			String dateCreated, String imageId, String name, int percentComplete,
			String userID) {
		this.completeDescription = completeDescription;
		this.eventLogDescription = eventLogDescription;
		this.incompleteDescription = incompleteDescription;
		this.dateCreated = dateCreated;
		this.imageId = imageId;
		this.name = name;
		this.percentComplete = percentComplete;
		this.userID = userID;
	}
	
	public String getCompleteDescription() {
		return completeDescription;
	}
	public void setCompleteDescription(String completeDescription) {
		this.completeDescription = completeDescription;
	}
	public String getEventLogDescription() {
		return eventLogDescription;
	}
	public void setEventLogDescription(String eventLogDescription) {
		this.eventLogDescription = eventLogDescription;
	}
	public String getIncompleteDescription() {
		return incompleteDescription;
	}
	public void setIncompleteDescription(String incompleteDescription) {
		this.incompleteDescription = incompleteDescription;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPercentComplete() {
		return percentComplete;
	}
	public void setPercentComplete(int percentComplete) {
		this.percentComplete = percentComplete;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String toString(){
		String out="ACHIEVEMENT[";
		out+=name+", "+completeDescription+", "+percentComplete;
		out+="]";
		return out;
	}
	
	
}

package com.balhau.kobo.model;

import java.util.Date;

public class Bookmark implements BaseModel{
	private String id;
	private String volumeId;
	private String contentId;
	private String startContainerPath;
	private int startContainerChildIndex;
	private int startOffset;
	private String endContainerPath;
	private int endContainerChildIndex;
	private int endOffset;
	private String text;
	private String annotation;
	private String extraAnnotation;
	private Date dateCreated;
	private double chapterProgress;
	private boolean hidden;
	private String version;
	private Date dateModified;
	private String creator;
	private String uuid;
	private String userId;
	private Date syncTime;
	private boolean published;
	
	public Bookmark(){
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVolumeId() {
		return volumeId;
	}

	public void setVolumeId(String volumeId) {
		this.volumeId = volumeId;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getStartContainerPath() {
		return startContainerPath;
	}

	public void setStartContainerPath(String startContainerPath) {
		this.startContainerPath = startContainerPath;
	}

	public int getStartContainerChildIndex() {
		return startContainerChildIndex;
	}

	public void setStartContainerChildIndex(int startContainerChildIndex) {
		this.startContainerChildIndex = startContainerChildIndex;
	}

	public int getStartOffset() {
		return startOffset;
	}

	public void setStartOffset(int startOffset) {
		this.startOffset = startOffset;
	}

	public String getEndContainerPath() {
		return endContainerPath;
	}

	public void setEndContainerPath(String endContainerPath) {
		this.endContainerPath = endContainerPath;
	}

	public int getEndContainerChildIndex() {
		return endContainerChildIndex;
	}

	public void setEndContainerChildIndex(int endContainerChildIndex) {
		this.endContainerChildIndex = endContainerChildIndex;
	}

	public int getEndOffset() {
		return endOffset;
	}

	public void setEndOffset(int endOffset) {
		this.endOffset = endOffset;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public String getExtraAnnotation() {
		return extraAnnotation;
	}

	public void setExtraAnnotation(String extraAnnotation) {
		this.extraAnnotation = extraAnnotation;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public double getChapterProgress() {
		return chapterProgress;
	}

	public void setChapterProgress(double chapterProgress) {
		this.chapterProgress = chapterProgress;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getSyncTime() {
		return syncTime;
	}

	public void setSyncTime(Date syncTime) {
		this.syncTime = syncTime;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}
}

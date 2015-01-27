/**
 This is a frontend layer to facilitate working with the KoboAPI exported through an Java Applet
*/
var Kobo=function(appletObject){
	this.applet=appletObject;
}

/**
* Domain objects
*/
Kobo.Book=function(title,id,contentType,mimeType,percentageRead,dateLastRead){
	this.title=title;
	this.bookId=id;
	this.contentType=contentType;
	this.mimeType=mimeType;
	this.percentageRead=percentageRead;
	this.dateLastRead=dateLastRead;
}

/*
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
private String dateCreated;
private double chapterProgress;
private boolean hidden;
private String version;
private String dateModified;
private String creator;
private String uuid;
private String userId;
private String syncTime;
private boolean published;
*/

Kobo.Bookmark=function(id,volumeId,contentId,startContainerPath,
		startContainerChildIndex,startOffSet,endContainerPath,
		endContainerChildIndex,endOffset,text,annotation,
		extraAnnotation,dateCreated,chapterProgress,hidden,
		version,dateModified,creator,uuid,userId,syncTime,published){

		this.id=id;this.volumeId=volumeId;this.contentId=contentId;
		this.startContainerPath=startContainerPath;
		this.startOffset=startOffSet;
		this.startContainerChildIndex=startContainerChildIndex;
		this.endContainerPath=endContainerPath;
		this.endContainerChildIndex=endContainerChildIndex;this.endOffset=endOffset;
		this.text=text;this.annotation=annotation;this.extraAnnotation=extraAnnotation;
		this.dateCreated=dateCreated;this.chapterProgress=chapterProgress;this.hidden=hidden;
		this.version=version;this.dateModified=dateModified;this.creator=creator;
		this.uuid=uuid;this.userId=userId;this.syncTime=syncTime;this.published=published;
}

Kobo.prototype.getDatabaseVersion=function(){
	return this.applet.getDatabaseVersion();
}

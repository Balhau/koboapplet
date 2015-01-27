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
};

Kobo.Achievement=function(completeDescription,eventLogDescription,incompleteDescription,dateCreated,imageId,name,percentComplete,userID){
	this.completeDescription=completeDescription;this.eventLogDescription=eventLogDescription;
	this.incompleteDescription=incompleteDescription;this.dateCreated=dateCreated;
	this.imageId=imageId;this.name=name;this.percentComplete=percentComplete;
	this.userID=userID;
};


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
};

Kobo.prototype.getDatabaseVersion=function(){
	return this.applet.getDatabaseVersion();
}

Kobo.prototype.getDetectedDevices=function(){
	var out=[];
	var dDev=this.applet.getDetectedDevices();
	for(var i=0;i<dDev.size();i++){
		out.push(dDev.get(i));
	}
	return out;
};

Kobo.prototype.getAchievements=function(){
	var out=[];
	var oAch=this.applet.getAchievements();
	var a;
	for(var i=0;i<oAch.size();i++){
		a=oAch.get(i);
		out.push(
			new Kobo.Achievement(
				a.getCompleteDescription(),a.getEventLogDescription(),a.getIncompleteDescription(),
				a.getDateCreated(),a.getImageId(),a.getName(),a.getPercentComplete(),a.getUserID()
			)
		);
	}
	return out;
};

Kobo.prototype.getBookmarks=function(){
	var out=[];
	var oBMark=this.applet.getBookmarks();
	var o=null;
	for(var i=0;i<oBMark.size();i++){
		o=oBMark.get(i);
		out.push(
			new Kobo.Bookmark(
				o.getId(),o.getVolumeId(),o.getContentId(),o.getStartContainerPath(),
				o.getStartOffset(),o.getStartContainerChildIndex(),o.getEndContainerPath(),
				o.getEndContainerChildIndex(),o.getEndOffset(),o.getText(),o.getAnnotation(),
				o.getExtraAnnotation(),o.getDateCreated(),o.getChapterProgress(),o.isHidden(),
				o.getVersion(),o.getDateModified(),o.getCreator(),o.getUuid(),o.getUserId(),
				o.getSyncTime(),o.isPublished()
			)
		);
	}
	return out;
};

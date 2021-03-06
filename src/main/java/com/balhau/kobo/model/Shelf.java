package com.balhau.kobo.model;


/**
 * Shelf dao for kobo database
 * @author balhau
 *
 */
public class Shelf implements BaseModel{
	
	private String creationDate;
	private String id;
	private String internalName;
	private String lastModified;
	private String name;
	private String type;
	private boolean deleted;
	private boolean visible;
	private boolean synced;
	
	public Shelf(String creationDate,String id,String internalName,
			String lastModified,String name,String type,boolean deleted,
			boolean visible,boolean synced){
		this.creationDate=creationDate;this.id=id;
		this.internalName=internalName;this.lastModified=lastModified;
		this.name=name;this.type=type;this.deleted=deleted;
		this.visible=visible;this.synced=synced;
	}
	
	public Shelf(){}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInternalName() {
		return internalName;
	}

	public void setInternalName(String internalName) {
		this.internalName = internalName;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isSynced() {
		return synced;
	}

	public void setSynced(boolean synced) {
		this.synced = synced;
	}
	
	
	
}

package com.balhau.kobo.model;

import com.google.gson.Gson;

/**
 * Interface for all model objects
 * @author balhau
 *
 */
public interface BaseModel {
	/**
	 * Default method to xml encoding
	 * @return
	 * @throws ModelEncodingException
	 */
	default String toXml(){
		return "";
	}
	
	/**
	 * Default method for json encoding
	 * @return
	 * @throws ModelEncodingException
	 */
	default String toJson(){
		Gson gson=new Gson();
		return gson.toJson(this);
	}
}

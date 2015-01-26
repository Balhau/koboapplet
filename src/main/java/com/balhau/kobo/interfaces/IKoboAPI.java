package com.balhau.kobo.interfaces;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.balhau.kobo.exceptions.KoboSQLException;
import com.google.gson.Gson;


/**
 * This will represent all the exportable methods the KoboAPI should implement
 * @author <a href="mailto:balhau@balhau.net">Balhau</a>
 * <p>15 de Fev de 2014</p>
 */
public interface IKoboAPI {
	
	default String api(){
		Method[] methods =IKoboAPI.class.getDeclaredMethods();
		Map<String,List<String>> mNames=new HashMap<String,List<String>>();
		List<String> aux;
		for(Method m : methods){
			Class<?>[] types=m.getParameterTypes();
			aux=new ArrayList<String>();
			for(Class<?> t : types){
				aux.add(t.getName());
			}
			mNames.put(m.getName(), aux);
		}
		Gson g=new Gson();
		return g.toJson(mNames);
	}
	
	int getDatabaseVersion() throws KoboSQLException;
	String getDetectedDevices() throws KoboSQLException;
	String getCurrentReadings() throws KoboSQLException;
	String getCurrentReadingsIDs() throws KoboSQLException;
	String getBookByContentID(String contentID) throws KoboSQLException;
	String getAchievements() throws KoboSQLException;
	String getBookmarks() throws KoboSQLException;
}

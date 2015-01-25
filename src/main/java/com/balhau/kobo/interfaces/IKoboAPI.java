package com.balhau.kobo.interfaces;

import com.balhau.kobo.exceptions.KoboSQLException;


/**
 * This will represent all the exportable methods the KoboAPI should implement
 * @author <a href="mailto:balhau@balhau.net">Balhau</a>
 * <p>15 de Fev de 2014</p>
 */
public interface IKoboAPI {
	
	int getDatabaseVersion() throws KoboSQLException;
	String getDetectedDevices() throws KoboSQLException;
	String getCurrentReadings() throws KoboSQLException;
	String getCurrentReadingsIDs() throws KoboSQLException;
	String getBookByContentID(String contentID) throws KoboSQLException;
	String getAchievements() throws KoboSQLException;
	String getBookmarks() throws KoboSQLException;
}

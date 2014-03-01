package com.balhau.kobo.interfaces;

import java.util.List;

import com.balhau.kobo.exceptions.KoboSQLException;
import com.balhau.kobo.model.KoboAchievement;
import com.balhau.kobo.model.KoboBook;

/**
 * Interface that all KoboDatabase implementations should respect
 * @author <a href="mailto:balhau@balhau.net">Balhau</a>
 * <p>15 de Fev de 2014</p>
 */
public interface IKoboDatabase {
	public int getVersion() throws KoboSQLException;
	public List<KoboBook> getCurrentReadings() throws KoboSQLException;
	public List<String> getReadingBookContentID() throws KoboSQLException;
	public List<String> getReadingBookIDs() throws KoboSQLException;
	public KoboBook getBookByContentID(String contentID) throws KoboSQLException;
	public KoboBook getBookByName(String name) throws KoboSQLException;
	public List<KoboAchievement> getAchievements() throws KoboSQLException;
	public void exportToDatabase(IDBExporter dbexporter) throws KoboSQLException;
	
}

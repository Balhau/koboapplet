package com.balhau.kobo.interfaces;

import java.util.List;

import com.balhau.kobo.exceptions.KoboSQLException;
import com.balhau.kobo.model.Bookmark;
import com.balhau.kobo.model.Achievement;
import com.balhau.kobo.model.Book;
import com.balhau.kobo.model.PocketArticle;
import com.balhau.kobo.model.Rating;
import com.balhau.kobo.model.Shelf;

/**
 * Interface that all KoboDatabase implementations should respect
 * @author <a href="mailto:balhau@balhau.net">Balhau</a>
 * <p>15 de Fev de 2014</p>
 */
public interface IKoboDatabase {
	public int getVersion() throws KoboSQLException;
	public List<Book> getCurrentReadings() throws KoboSQLException;
	public List<String> getReadingBooksContentID() throws KoboSQLException;
	public List<String> getReadingBookIDs() throws KoboSQLException;
	public List<String> getBooksIds() throws KoboSQLException;
	public Book getBookByContentID(String contentID) throws KoboSQLException;
	public List<Book> getBooksByName(String name) throws KoboSQLException;
	public List<Achievement> getAchievements() throws KoboSQLException;
	public void exportToDatabase(IDBExporter dbexporter) throws KoboSQLException;
	/**
	 * Returns all the bookmarks in the Kobo database
	 * @return {@link List<Bookmark>} List of {@link Bookmark} elements
	 * @throws KoboSQLException 
	 */
	public List<Bookmark> getBookmarks() throws KoboSQLException;
	public List<Rating> getRatings() throws KoboSQLException;
	public List<Shelf> getShelfs() throws KoboSQLException;
	public List<PocketArticle> getPocketArticles() throws KoboSQLException;
}

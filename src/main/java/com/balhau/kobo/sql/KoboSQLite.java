package com.balhau.kobo.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.balhau.kobo.exceptions.KoboSQLException;
import com.balhau.kobo.interfaces.IDBExporter;
import com.balhau.kobo.interfaces.IKoboDatabase;
import com.balhau.kobo.model.Bookmark;
import com.balhau.kobo.model.Achievement;
import com.balhau.kobo.model.Book;
import com.balhau.kobo.model.PocketArticle;
import com.balhau.kobo.model.Rating;
import com.balhau.kobo.model.Shelf;

/**
 * Implementation of {@link IKoboDatabase}. This is a implementation to kobo sqlite database
 * @author <a href="mailto:balhau@balhau.net">Balhau</a>
 * <p>15 de Fev de 2014</p>
 */
public class KoboSQLite implements IKoboDatabase{
	
	private String databasePath;
	private final String SQLITE_JDBC="org.sqlite.JDBC";
	private final String SQLITE_CON_PREFIX="jdbc:sqlite:";
	private Connection conn;
	
	public KoboSQLite(String dbPath) throws ClassNotFoundException, KoboSQLException{
		Class.forName(SQLITE_JDBC);
		this.databasePath=dbPath;
		try{
		conn=getConnection();
		}catch (Exception e) {
			throw new KoboSQLException(e);
		}
	}
	
	
	private Connection getConnection() throws SQLException{
		 return DriverManager.getConnection(SQLITE_CON_PREFIX+databasePath);
	}

	public int getVersion() throws KoboSQLException{
		try{
		Statement st = conn.createStatement();
		ResultSet rs=st.executeQuery("select version from DbVersion;");
		rs.next();
		return rs.getInt(1);
		}
		catch (Exception e) {
			throw new KoboSQLException(e);
		}
	}
	
	public String toString(){
		String version="Undefined";
		try{
			version=getVersion()+"";
		}catch(Exception ex){}
		return "Kobo Database Version: "+version;
	}

	public List<Book> getCurrentReadings() throws KoboSQLException{
		try{
			ArrayList<Book> res=new ArrayList<Book>();
			List<String> rIDS=getReadingBookIDs();
			for(String cid : rIDS){
				res.add(getBookByContentID(cid));
			}
			return res;
		}catch (Exception e) {
			throw new KoboSQLException(e);
		}
	}


	public List<String> getReadingBookIDs() throws KoboSQLException {
		try{
			List<String> bookIDs=new ArrayList<String>();
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select contentID from  content where ___PercentRead <> 0;");
			while(rs.next()){
				bookIDs.add(rs.getString(1));
			}
			return bookIDs;
		}catch (Exception e) {
			throw new KoboSQLException(e);
		}
	}


	public List<String> getAllBookNames() throws KoboSQLException {
		try{
			List<String> bookNames=new ArrayList<String>();
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select distinct BookTitle from content");
			while(rs.next()){
				bookNames.add(rs.getString(1));
			}
			return bookNames;
		}catch (Exception e) {
			throw new KoboSQLException(e);
		}
	}


	public List<Achievement> getAchievements() throws KoboSQLException {
		try{
			Statement st=conn.createStatement();
			List<Achievement> achievements=new ArrayList<Achievement>();
			ResultSet rs=st.executeQuery("select CompleteDescription, EventLogDescription,IncompleteDescription,DateCreated,ImageId,Name,PercentComplete,UserId from Achievement;");
			while(rs.next()){
				achievements.add(new Achievement(
						rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
						rs.getString(6), rs.getInt(7), rs.getString(8)));
			}
			return achievements;
		}catch (Exception e) {
			throw new KoboSQLException(e);
		}
	}


	public void exportToDatabase(IDBExporter dbexporter)
			throws KoboSQLException {
	}


	private String getBookTitleByContentID(String contentID) throws KoboSQLException {
		try{
			PreparedStatement pst=conn.prepareStatement("select distinct(BookTitle) from content where contentID like ? and BookTitle<>''");
			pst.setString(1, "%"+contentID+"%");
			ResultSet rs=pst.executeQuery();
			if(rs.next())	return rs.getString(1);
			return null;
		}catch (Exception e) {
			throw new KoboSQLException(e);
		}
	}


	public List<String> getReadingBooksContentID() throws KoboSQLException {
		try{
			Statement st=conn.createStatement();
			List<String> readingBooks=new ArrayList<String>();
			ResultSet rs=st.executeQuery("select contentID from content where ___PercentRead <>");
			while(rs.next()){
				readingBooks.add(rs.getString(1));
			}
			return readingBooks;
		}catch (Exception e) {
			throw new KoboSQLException(e);
		}
	}

	private int percentReadByContentID(String contentID){
		try{
			PreparedStatement pst=conn.prepareStatement("select ___PercentRead from content where contentID like ? and ___PercentRead <> 0;");
			pst.setString(1, contentID);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) return rs.getInt(1);
		}catch (Exception e) {
			return 0;
		}
		return 0;
	}
	
	private void infoKoboBook(String contentID,Book book) throws KoboSQLException{
		try{
			PreparedStatement pst=conn.prepareStatement("select * from content where contentID like ? and BookTitle <>''");
			String lq="%"+contentID+"%";
			pst.setString(1, lq);
			ResultSet rs=pst.executeQuery();
			if(rs.next()){
				book.setContentType(rs.getInt(2));
				book.setMimeType(rs.getString(3));
				PreparedStatement pst2=conn.prepareStatement("select * from content where contentID like ? and ContentType ==6");
				pst2.setString(1, lq);
				ResultSet rs2=pst2.executeQuery();
				if(rs2.next()){
					String st=rs2.getString(15);
					book.setDateLastRead(st);
				}
			}
		}catch (Exception e) {
			throw new KoboSQLException(e);
		}
	}

	public Book getBookByContentID(String contentID) throws KoboSQLException {
		try{
			Book kb=new Book();
			kb.setPercentageReaded(percentReadByContentID(contentID));
			kb.setContentID(contentID);
			kb.setBookTitle(getBookTitleByContentID(contentID));
			infoKoboBook(contentID, kb);
			return kb;
		}catch (Exception e) {
			throw new KoboSQLException(e);
		}
	}


	public List<Book> getBooksByName(String name) throws KoboSQLException {
		try{
			PreparedStatement pst=conn.prepareStatement("select contentID,BookTitle from content where BookTitle like ? group by BookTitle;");
			pst.setString(1, "%"+name+"%");
			ResultSet rs=pst.executeQuery();
			List<Book> kb=new ArrayList<Book>();
			List<String> ids=new ArrayList<String>();
			while(rs.next()){
				ids.add(rs.getString(1));
			}
			for(String id: ids){
				kb.add(getBookByContentID(id));
			}
			return kb;
		}catch (Exception e) {
			throw new KoboSQLException(e);
		}
	}
	
	
	public List<Bookmark> getBookmarks() throws KoboSQLException{
		List<Bookmark> bookmarks = new ArrayList<Bookmark>();
		try{
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select * from Bookmark");
			Bookmark aux;
			while(rs.next()){
				aux=new Bookmark();
				aux.setId(rs.getString(1));
				aux.setVolumeId(rs.getString(2));
				aux.setContentId(rs.getString(3));
				aux.setStartContainerPath(rs.getString(4));
				aux.setStartContainerChildIndex(rs.getInt(5));
				aux.setStartOffset(rs.getInt(6));
				aux.setEndContainerPath(rs.getString(7));
				aux.setEndContainerChildIndex(rs.getInt(8));
				aux.setEndOffset(rs.getInt(9));
				aux.setText(rs.getString(10));
				aux.setAnnotation(rs.getString(11));
				aux.setExtraAnnotation(rs.getString(12));
				aux.setDateCreated(rs.getString(13));
				aux.setChapterProgress(rs.getDouble(14));
				aux.setHidden(rs.getBoolean(15));
				aux.setVersion(rs.getString(16));
				aux.setDateModified(rs.getString(17));
				aux.setCreator(rs.getString(18));
				aux.setUuid(rs.getString(19));
				aux.setUserId(rs.getString(20));
				aux.setSyncTime(rs.getString(21));
				aux.setPublished(rs.getBoolean(22));
				bookmarks.add(aux);
			}
			return bookmarks;
		}catch (Exception ex){
			throw new KoboSQLException(ex.getMessage());
		}
	}


	@Override
	public List<String> getBooksIds() throws KoboSQLException {
		List<String> bookIds = new ArrayList<String>();
		try{
			ResultSet res=query("select distinct contentId from content");
			String id;
			while(res.next()){
				id=res.getString(1);
				if(id.indexOf("#")==-1){
					bookIds.add(id);
				}
			}
			return bookIds;
		}catch(Exception ex){ throw new KoboSQLException(ex.getMessage());}
	}
	
	
	
	@Override
	public List<Rating> getRatings() throws KoboSQLException {
		List<Rating> ratings=new ArrayList<Rating>();
		try{
			ResultSet res=query("select * from ratings");
			Rating r;
			while(res.next()){
				r=new Rating();
				r.setContentId(res.getString(1));
				r.setRating(res.getInt(2));
				r.setReview(res.getString(3));
				r.setDateModified(res.getString(4));
				ratings.add(r);
			}
			return ratings;
		}catch(Exception ex){throw new KoboSQLException(ex.getMessage());}
	}
	
	@Override
	public List<Shelf> getShelfs() throws KoboSQLException {
		List<Shelf> shelfs=new ArrayList<Shelf>();
		try{
			ResultSet res = query("select * from Shelf");
			while(res.next()){
				shelfs.add(new Shelf(res.getString(1), 
						res.getString(2), res.getString(3), res.getString(4), res.getString(5), 
						res.getString(6), res.getBoolean(7), res.getBoolean(8), res.getBoolean(9)));
			}
		}catch(Exception ex){throw new KoboSQLException(ex.getMessage());}
		return shelfs;
	}
	

	@Override
	public List<PocketArticle> getPocketArticles() throws KoboSQLException {
		List<PocketArticle> articles=new ArrayList<PocketArticle>();
		PocketArticle a;
		try{
			ResultSet res=query("select * from content where PocketStatus <>  0");
			while(res.next()){
				a=new PocketArticle();
				a.setContentID(res.getString(1));a.setContentType(res.getInt(2));
				a.setMimeType(res.getString(2)); a.setTitle(res.getString(7));
				a.setAttribution(res.getString(8)); a.setDescription(res.getString(9));
				a.setDateLastRead(res.getString(15));a.setReadStatus(res.getInt(23));
				a.setFileSize(res.getInt(28));a.setContentURL(res.getString(33));
				a.setWordCount(res.getInt(53));a.setPocketStatus(res.getInt(58));
				a.setImageURL(res.getString(60));a.setDateAdded(res.getString(61));
				articles.add(a);
			}
		}catch(Exception ex){throw new KoboSQLException(ex.getMessage());}
		return articles;
	}


	private ResultSet query(String sqlQuery) throws Exception{
		Statement st=conn.createStatement();
		return st.executeQuery(sqlQuery);
	}	
	
}

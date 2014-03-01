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
import com.balhau.kobo.model.KoboAchievement;
import com.balhau.kobo.model.KoboBook;

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

	public List<KoboBook> getCurrentReadings() throws KoboSQLException{
		try{
			ArrayList<KoboBook> res=new ArrayList<KoboBook>();
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


	public List<KoboAchievement> getAchievements() throws KoboSQLException {
		try{
			Statement st=conn.createStatement();
			List<KoboAchievement> achievements=new ArrayList<KoboAchievement>();
			ResultSet rs=st.executeQuery("select CompleteDescription, EventLogDescription,IncompleteDescription,DateCreated,ImageId,Name,PercentComplete,UserId from Achievement;");
			while(rs.next()){
				achievements.add(new KoboAchievement(
						rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), 
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
	
	private void infoKoboBook(String contentID,KoboBook book) throws KoboSQLException{
		try{
			PreparedStatement pst=conn.prepareStatement("select * from content where contentID like ? and BookTitle <>''");
			pst.setString(1, "%"+contentID+"%");
			ResultSet rs=pst.executeQuery();
			if(rs.next()){
				book.setContentType(rs.getInt(2));
				book.setMimeType(rs.getString(3));
			}
		}catch (Exception e) {
			throw new KoboSQLException(e);
		}
	}

	public KoboBook getBookByContentID(String contentID) throws KoboSQLException {
		try{
			KoboBook kb=new KoboBook();
			kb.setPercentageReaded(percentReadByContentID(contentID));
			kb.setBookID(contentID);
			kb.setBookTitle(getBookTitleByContentID(contentID));
			infoKoboBook(contentID, kb);
			return kb;
		}catch (Exception e) {
			throw new KoboSQLException(e);
		}
	}


	public List<KoboBook> getBooksByName(String name) throws KoboSQLException {
		try{
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select contentID,BookTitle from content where BookTitle like '%"+name+"%' group by BookTitle;");
			List<KoboBook> kb=new ArrayList<KoboBook>();
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
	
}

package com.balhau.kobo.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.balhau.kobo.exceptions.KoboSQLException;
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
			Statement st=conn.createStatement();
			ArrayList<KoboBook> res=new ArrayList<KoboBook>();
			ResultSet rs=st.executeQuery("select distinct C.BookTitle,Filter.___PercentRead from content as C, (select ContentID ,BookTitle,___PercentRead from content   where ___PercentRead <> 0) as Filter where C.ContentID like Filter.ContentID||'%' limit 0,10");
			while(rs.next()){
				res.add(new KoboBook(rs.getString(1), "",rs.getInt(2)));
			}
			return res;
		}catch (Exception e) {
			throw new KoboSQLException(e);
		}
	}


	public List<KoboBook> getReadedBooks() throws KoboSQLException {
		// TODO Auto-generated method stub
		return null;
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


	public KoboBook fromBookTitle(String bookTitle) throws KoboSQLException {
		
		// TODO Auto-generated method stub
		return null;
	}
	
}

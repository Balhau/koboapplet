package com.balhau.kobo.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.balhau.kobo.interfaces.IKoboDatabase;
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
	
	public KoboSQLite(String dbPath) throws ClassNotFoundException, SQLException{
		Class.forName(SQLITE_JDBC);
		this.databasePath=dbPath;
		conn=getConnection();
	}
	
	
	private Connection getConnection() throws SQLException{
		 return DriverManager.getConnection(SQLITE_CON_PREFIX+databasePath);
	}

	public int getVersion() throws SQLException{
		Statement st = conn.createStatement();
		ResultSet rs=st.executeQuery("select version from DbVersion;");
		rs.next();
		return rs.getInt(1);
	}

	public List<KoboBook> getCurrentReadings() throws SQLException{
		Statement st=conn.createStatement();
		ArrayList<KoboBook> res=new ArrayList<KoboBook>();
		ResultSet rs=st.executeQuery("select distinct C.BookTitle,Filter.___PercentRead from content as C, (select ContentID ,BookTitle,___PercentRead from content   where ___PercentRead <> 0) as Filter where C.ContentID like Filter.ContentID||'%' limit 0,10");
		while(rs.next()){
			res.add(new KoboBook(rs.getString(1), "",rs.getInt(2)));
		}
		return res; 
	}


	public List<KoboBook> getReadedBooks() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}

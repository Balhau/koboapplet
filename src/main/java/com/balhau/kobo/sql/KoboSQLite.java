package com.balhau.kobo.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.balhau.kobo.interfaces.IKoboDatabase;
import com.balhau.kobo.utils.KoboConfig;

/**
 * Implementation of {@link IKoboDatabase}. This is a implementation to kobo sqlite database
 * @author <a href="mailto:balhau@balhau.net">Balhau</a>
 * <p>15 de Fev de 2014</p>
 */
public class KoboSQLite implements IKoboDatabase{
	private String databasePath;
	private String koboDevicePath;
	private final String SQLITE_JDBC="org.sqlite.JDBC";
	private final String SQLITE_CON_PREFIX="jdbc:sqlite:";
	
	public KoboSQLite(String dbPath) throws ClassNotFoundException{
		Class.forName(SQLITE_JDBC);
		this.databasePath=dbPath;
	}
	
	
	private Connection getConnection() throws SQLException{
		 return DriverManager.getConnection(SQLITE_CON_PREFIX+databasePath);
	}

	public int getVersion() throws SQLException{
		Connection cn=getConnection();
		Statement st = cn.createStatement();
		ResultSet rs=st.executeQuery("select version from DbVersion;");
		rs.next();
		return rs.getInt(1);
	}


	
	
}

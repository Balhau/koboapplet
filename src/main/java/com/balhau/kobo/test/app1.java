package com.balhau.kobo.test;


import java.util.List;

import com.balhau.kobo.device.DeviceUtils;
import com.balhau.kobo.interfaces.IKoboDatabase;
import com.balhau.kobo.sql.KoboSQLite;
import com.balhau.kobo.utils.KoboConfig;
import com.balhau.kobo.utils.KoboProp;

public class app1 {
	
	public static final String SQLITE_JDBC="org.sqlite.JDBC";
	
	public static void main(String[] args){
		try {
//			  System.out.println(KoboProp.CONFIG_PROPS.getProperty("kobo.default.dir"));
//		      Class.forName(SQLITE_JDBC);
			  List<String> dev=DeviceUtils.getDevicesInfo();
			  IKoboDatabase db=new KoboSQLite(dev.get(0)+"/"+KoboConfig.DEFAULT_DIR.getValue()+"/"+KoboConfig.SQLLITE_DATABASE.getValue());
			  System.out.println(dev.get(0)+"/"+KoboConfig.DEFAULT_DIR.getValue()+"/"+KoboConfig.SQLLITE_DATABASE.getValue());
			  System.out.println("Kobo Database Version:" +db.getVersion());
			  System.out.println(db);
			  System.out.println(DeviceUtils.getDevicesInfo());
//		      Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
		      System.out.println("Opened database successfully");
		      System.out.println(System.getProperty("kobo.default.dir"));
//		      stmt = c.createStatement();
//		      String sql = "CREATE TABLE COMPANY " +
//		                   "(ID INT PRIMARY KEY     NOT NULL," +
//		                   " NAME           TEXT    NOT NULL, " + 
//		                   " AGE            INT     NOT NULL, " + 
//		                   " ADDRESS        CHAR(50), " + 
//		                   " SALARY         REAL)"; 
//		      stmt.executeUpdate(sql);
//		      stmt.close();
//		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
//		    System.out.println("Table created successfully");
	}
}

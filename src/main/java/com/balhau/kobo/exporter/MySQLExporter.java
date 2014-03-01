package com.balhau.kobo.exporter;

import com.balhau.kobo.interfaces.IDBExporter;

/**
 * Exporter for MySQL databases. This will interface kobo SQLite data with MySQL.
 * @author <a href="mailto:balhau@balhau.net">Balhau</a>
 * <p>25 de Fev de 2014</p>
 */
public class MySQLExporter implements IDBExporter{

	private String dbName;
	private String user;
	private String pass;
	private String host;
	
	/**
	 * Exporter for MySQL database engine
	 * @param host {@link String} Hostname of MySQL server
	 * @param user {@link String} Username of MySQL
	 * @param pass {@link String} Password for MySQL
	 * @param dbName {@link String} Database for storing the data
	 */
	public MySQLExporter(String host,String user,String pass,String dbName){
		this.dbName=dbName;this.host=host;this.user=user;
		this.pass=pass;
	}

	public void importKoboData() {
		// TODO Auto-generated method stub
		
	}

	public String generateKoboSqlTables() {
		// TODO Auto-generated method stub
		return null;
	}

}

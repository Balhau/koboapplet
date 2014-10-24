package com.balhau.kobo.interfaces;

/**
 * Interface that all engine db should implement to export into
 * @author <a href="mailto:balhau@balhau.net">Balhau</a>
 * <p>25 de Fev de 2014</p>
 */
public interface IDBExporter {
	public void importKoboData();
	public String generateKoboSqlTables();
}

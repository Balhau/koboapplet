package com.balhau.kobo.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.balhau.kobo.model.KoboBook;

/**
 * Interface that all KoboDatabase implementations should respect
 * @author <a href="mailto:balhau@balhau.net">Balhau</a>
 * <p>15 de Fev de 2014</p>
 */
public interface IKoboDatabase {
	public int getVersion() throws SQLException;
	public List<KoboBook> getCurrentReadings() throws SQLException;
	public List<KoboBook> getReadedBooks() throws SQLException;
	
}

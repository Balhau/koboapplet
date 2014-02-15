package com.balhau.kobo.interfaces;

import java.sql.SQLException;

/**
 * Interface that all KoboDatabase implementations should respect
 * @author <a href="mailto:balhau@balhau.net">Balhau</a>
 * <p>15 de Fev de 2014</p>
 */
public interface IKoboDatabase {
	public int getVersion() throws SQLException;
}

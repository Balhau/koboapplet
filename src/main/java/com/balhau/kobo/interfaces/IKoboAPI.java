package com.balhau.kobo.interfaces;


/**
 * This will represent all the exportable methods the KoboAPI should implement
 * @author <a href="mailto:balhau@balhau.net">Balhau</a>
 * <p>15 de Fev de 2014</p>
 */
public interface IKoboAPI {
	
	int getDatabaseVersion();
	String getDetectedDevices();
	String getCurrentReadings();
}

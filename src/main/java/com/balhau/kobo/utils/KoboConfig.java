package com.balhau.kobo.utils;


/**s
 * Enumerated type with information about configuration properties. Has two auxiliary methods to help retrieve key/value info about configurations
 * @author <a href="mailto:balhau@balhau.net">Balhau</a>
 * <p>15 de Fev de 2014</p>
 */
public enum KoboConfig {
		DEFAULT_MOUNT_POINT("kobo.default.mountpoint"),
		DEFAULT_DIR("kobo.default.dir"),
		SQLLITE_DATABASE("kobo.default.database")
	;
	
	private String property;
	
	private KoboConfig(String property){
		this.property=property;
	}
	
	public String getKey(){
		return this.property;
	}
	
	public String getValue(){return KoboProp.CONFIG_PROPS.getProperty(this.property);}
}

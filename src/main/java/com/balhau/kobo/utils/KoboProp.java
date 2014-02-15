package com.balhau.kobo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Class that is responsible to load configuration data
 * @author <a href="mailto:balhau@balhau.net">Balhau</a>
 * <p>15 de Fev de 2014</p>
 */
public class KoboProp {
	
		

		public static final String CONFIG_PATH="config.properties";
		public static final Properties CONFIG_PROPS=loadConfigFiles();
		
		
		private static Properties loadConfigFiles(){
			InputStream input=null;
			try {
	    		input = KoboProp.class.getClassLoader().getResourceAsStream(CONFIG_PATH);
	    		if(input==null){
	    	            System.out.println("Sorry, unable to find " + CONFIG_PATH);
	    		    return null;
	    		}
	 
	    		//load a properties file from class path, inside static method
	    		Properties prop = new Properties();
	    		prop.load(input);
	    		return prop;
	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	        } finally{
	        	if(input!=null){
	        		try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        	}
	        }
			return null;
		}
}

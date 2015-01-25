package com.balhau.kobo.device;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.balhau.kobo.utils.KoboConfig;

/**
 * This is a utility class with some mechanisms to detect Kobo devices.
 * @author <a href="mailto:balhau@balhau.net">Balhau</a>
 * <p>15 de Fev de 2014</p>
 */
public class DeviceUtils {
	
	public static final String ROOTMOUNT="/media";
	public static final String KOBO_DEFAULT_FOLDER="";
	
	/**
	 * This static method retrieves a list of {@link String} with the absolute path of Kobo device default directory 
	 * @return {@link List<String>} List of strings with absolute paths of Kobo device default directory.
	 * @throws IOException This will be lounched if something is wrong with input/ouput file operations
	 */
	public static List<String> getDevicesInfo() throws IOException{
		File media=new File(KoboConfig.DEFAULT_MOUNT_POINT.getValue());
		File dev;
		List<String> devs=new ArrayList<String>();
		String devinfo;
		String[] mounted=media.list();
		for(int i=0;i<mounted.length;i++){
			devinfo=KoboConfig.DEFAULT_MOUNT_POINT.getValue()+"/"+mounted[i]+"/"+KoboConfig.DEFAULT_DIR.getValue();
			dev=new File(devinfo);
			if(dev.exists()){
				devs.add(ROOTMOUNT+"/"+mounted[i]);
			}
		}
		return devs;
	}
	
	public static String getSQLitePathFromKoboPath(String koboPath){
		return koboPath+"/"+KoboConfig.DEFAULT_DIR.getValue()+"/"+KoboConfig.SQLLITE_DATABASE.getValue();
	}
	
	/**
	 * Utility method o read text files 
	 * @param file {@link String} Absolute path of the file
	 * @return {@link String} String with the text presented on the file
	 * @throws IOException
	 */
	public static String readFile(String file) throws IOException{
		BufferedReader bf=new BufferedReader(new FileReader(new File(file)));
		String aux=null;
		StringBuilder sb=new StringBuilder();
		while((aux=bf.readLine())!=null){
			sb.append(aux);
		}
		bf.close();
		return sb.toString();
	}
}

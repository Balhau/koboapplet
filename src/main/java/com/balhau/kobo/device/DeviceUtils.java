package com.balhau.kobo.device;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.balhau.kobo.utils.KoboConfig;


public class DeviceUtils {
	
	public static final String ROOTMOUNT="/media";
	public static final String KOBO_DEFAULT_FOLDER="";
	
	public static List<String> getDevicesInfo() throws IOException{
		File media=new File(KoboConfig.DEFAULT_MOUNT_POINT.getValue());
		File dev;
		List<String> devs=new ArrayList<String>();
		String devinfo;
		System.out.println("Directory: "+media.isDirectory());
		String[] mounted=media.list();
		for(int i=0;i<mounted.length;i++){
			devinfo=KoboConfig.DEFAULT_MOUNT_POINT.getValue()+"/"+mounted[i]+"/"+KoboConfig.DEFAULT_DIR.getValue();
			dev=new File(devinfo);
			System.out.println("Exists "+devinfo+": "+dev.exists());
			if(dev.exists()){
				devs.add(ROOTMOUNT+"/"+mounted[i]);
			}
		}
		return devs;
	}
	
	public static String readFile(String file) throws IOException{
		BufferedReader bf=new BufferedReader(new FileReader(new File(file)));
		String aux=null;
		StringBuilder sb=new StringBuilder();
		while((aux=bf.readLine())!=null){
			sb.append(aux);
		}
		return sb.toString();
	}
}

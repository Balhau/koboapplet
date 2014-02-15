package com.balhau.kobo.applet;

import java.applet.Applet;
import java.awt.Graphics;
import java.util.List;

import com.balhau.kobo.device.DeviceUtils;
import com.balhau.kobo.interfaces.IKoboDatabase;
import com.balhau.kobo.sql.KoboSQLite;
import com.balhau.kobo.utils.KoboConfig;



 
public class KoboMain extends Applet{
	
	 private int koboVersion;
	 private IKoboDatabase koboDatabase;
	 private String errorMessage;
	
	 public void init(){
		 try{
			 List<String> ldv=DeviceUtils.getDevicesInfo();
			 String dbpath=ldv.get(0)+"/"+KoboConfig.SQLLITE_DATABASE.getValue();
			 System.out.println(DeviceUtils.getSQLitePathFromKoboPath(ldv.get(0)));
			 koboDatabase=new KoboSQLite(DeviceUtils.getSQLitePathFromKoboPath(ldv.get(0)));
		 }catch (Exception e) {
			 System.out.println(e.getMessage());
			 koboVersion=-1;
			 errorMessage=e.getMessage();
		}
	 }
	
	 public void paint(Graphics g) {
	        g.drawString("Test kobo!", 50, 25);
	 }
	 
	 public  String getKoboSystemInfo(){
		 return "Database Version: "+koboDatabase.getVersion();
	 }

} 

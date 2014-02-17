package com.balhau.kobo.applet;

import java.applet.Applet;
import java.awt.Graphics;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.balhau.kobo.device.DeviceUtils;
import com.balhau.kobo.interfaces.IKoboAPI;
import com.balhau.kobo.interfaces.IKoboDatabase;
import com.balhau.kobo.model.KoboBook;
import com.balhau.kobo.sql.KoboSQLite;
import com.balhau.kobo.utils.KoboConfig;
import com.google.gson.Gson;



 
public class KoboMain extends Applet implements IKoboAPI{
	
	 private IKoboDatabase koboDatabase;
	 private List<String> detectedDevices;
	 
	 public void init(){
		 try{
			 detectedDevices=DeviceUtils.getDevicesInfo();
			 List<String> ldv=DeviceUtils.getDevicesInfo();
			 String dbpath=ldv.get(0)+"/"+KoboConfig.SQLLITE_DATABASE.getValue();
			 System.out.println(DeviceUtils.getSQLitePathFromKoboPath(ldv.get(0)));
			 System.out.println("DEVICE: "+DeviceUtils.getSQLitePathFromKoboPath(ldv.get(0)));
			 koboDatabase=new KoboSQLite(DeviceUtils.getSQLitePathFromKoboPath(ldv.get(0)));
			 System.out.println(koboDatabase);
		 }catch (Exception e) {
			 System.out.println("Init Applet Error: "+e.getMessage());
		}
	 }
	
	 public void paint(Graphics g) {
	        g.drawString("Heil Joseph!", 50, 25);
	 }

	public int getDatabaseVersion() {
		try {
			return koboDatabase.getVersion();
		} catch (SQLException e) {
			System.out.println("Error connecting database");
		}
		return -1;
	}

	public String getDetectedDevices() {
		Gson gson = new Gson();
		return gson.toJson(detectedDevices); 
	}

	public String getCurrentReadings() {
		Gson gson=new Gson();
		try {
			return gson.toJson(koboDatabase.getCurrentReadings());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

} 

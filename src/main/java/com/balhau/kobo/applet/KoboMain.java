package com.balhau.kobo.applet;

import java.applet.Applet;
import java.awt.Graphics;
import java.util.List;

import com.balhau.kobo.device.DeviceUtils;
import com.balhau.kobo.exceptions.KoboSQLException;
import com.balhau.kobo.interfaces.IKoboAPI;
import com.balhau.kobo.interfaces.IKoboDatabase;
import com.balhau.kobo.model.Bookmark;
import com.balhau.kobo.model.KoboAchievement;
import com.balhau.kobo.model.KoboBook;
import com.balhau.kobo.sql.KoboSQLite;
import com.google.gson.Gson;



 
public class KoboMain extends Applet implements IKoboAPI{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IKoboDatabase koboDatabase;
	private List<String> detectedDevices;
	private String apiDesc;
	 
	 public void init(){
		 try{
			 detectedDevices=DeviceUtils.getDevicesInfo();
			 List<String> ldv=DeviceUtils.getDevicesInfo();
			 System.out.println(DeviceUtils.getSQLitePathFromKoboPath(ldv.get(0)));
			 System.out.println("DEVICE: "+DeviceUtils.getSQLitePathFromKoboPath(ldv.get(0)));
			 koboDatabase=new KoboSQLite(DeviceUtils.getSQLitePathFromKoboPath(ldv.get(0)));
			 System.out.println(koboDatabase);
		 }catch (Exception e) {
			 System.out.println("Init Applet Error: "+e.getMessage());
		}
	 }
	 
	 private String exportJSON(Object data){
		 Gson gson=new Gson();
		 return gson.toJson(data);
	 }
	
	 public void paint(Graphics g) {
	        g.drawString("Heil Joseph!", 50, 25);
	 }

	public int getDatabaseVersion() {
		try {
			return koboDatabase.getVersion();
		} catch (Exception e) {
			System.out.println("Error connecting database");
		}
		return -1;
	}

	public List<String> getDetectedDevices() {
		return detectedDevices; 
	}

	public List<KoboBook> getCurrentReadings() throws KoboSQLException{
		return koboDatabase.getCurrentReadings();
	}

	public List<String> getCurrentReadingsIDs() throws KoboSQLException {
		return koboDatabase.getReadingBookIDs();
	}

	public KoboBook getBookByContentID(String contentID) throws KoboSQLException{
		return koboDatabase.getBookByContentID(contentID);
	}

	public List<KoboAchievement> getAchievements() throws KoboSQLException {
		return koboDatabase.getAchievements();
	}
	
	public List<Bookmark> getBookmarks() throws KoboSQLException {
		return koboDatabase.getBookmarks();
	}

} 

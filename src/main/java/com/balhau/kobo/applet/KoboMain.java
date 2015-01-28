package com.balhau.kobo.applet;

import java.applet.Applet;
import java.awt.Graphics;
import java.util.List;

import com.balhau.kobo.device.DeviceUtils;
import com.balhau.kobo.exceptions.KoboSQLException;
import com.balhau.kobo.interfaces.IKoboAPI;
import com.balhau.kobo.interfaces.IKoboDatabase;
import com.balhau.kobo.model.Bookmark;
import com.balhau.kobo.model.Achievement;
import com.balhau.kobo.model.Book;
import com.balhau.kobo.model.PocketArticle;
import com.balhau.kobo.model.Rating;
import com.balhau.kobo.sql.KoboSQLite;



 
public class KoboMain extends Applet implements IKoboAPI{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IKoboDatabase koboDatabase;
	private List<String> detectedDevices;
	 
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

	public List<Book> getCurrentReadings() throws KoboSQLException{
		return koboDatabase.getCurrentReadings();
	}

	public List<String> getCurrentReadingsIDs() throws KoboSQLException {
		return koboDatabase.getReadingBookIDs();
	}

	public Book getBookByContentID(String contentID) throws KoboSQLException{
		return koboDatabase.getBookByContentID(contentID);
	}

	public List<Achievement> getAchievements() throws KoboSQLException {
		return koboDatabase.getAchievements();
	}
	
	public List<Bookmark> getBookmarks() throws KoboSQLException {
		return koboDatabase.getBookmarks();
	}

	@Override
	public List<Rating> getRatings() throws KoboSQLException {
		return koboDatabase.getRatings();
	}

	@Override
	public List<PocketArticle> getPocketArticles() throws KoboSQLException {
		return koboDatabase.getPocketArticles();
	}
	
} 

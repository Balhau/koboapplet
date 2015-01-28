package com.balhau.kobo.applet;

import java.util.List;

import com.balhau.kobo.device.DeviceUtils;
import com.balhau.kobo.interfaces.IKoboDatabase;
import com.balhau.kobo.model.Bookmark;
import com.balhau.kobo.model.KoboAchievement;
import com.balhau.kobo.model.Rating;
import com.balhau.kobo.model.Shelf;
import com.balhau.kobo.sql.KoboSQLite;

import static java.lang.System.out;
import static com.balhau.kobo.utils.functionals.Functional.*;

public class ShellMain {
	
	private static IKoboDatabase koboDatabase;
	/**
	 * Demo for the API
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		List<String> ldv=DeviceUtils.getDevicesInfo();
		out.println(DeviceUtils.getSQLitePathFromKoboPath(ldv.get(0)));
		out.println("DEVICE: "+DeviceUtils.getSQLitePathFromKoboPath(ldv.get(0)));
		koboDatabase=new KoboSQLite(DeviceUtils.getSQLitePathFromKoboPath(ldv.get(0)));
		final IKoboDatabase aux= koboDatabase;
		out.println(koboDatabase);
		List<String> ids=koboDatabase.getReadingBookIDs();
		out.println("Reading IDS: "+ids.size());
		
		callOn(ids, (String id) -> { 
			out.println(id);
		});
		
		List<KoboAchievement> ach=aux.getAchievements();
		
		callOn(ach,(KoboAchievement a) ->{
			out.println(a.toJson());
		});
		
		List<Bookmark> bmarks=aux.getBookmarks();
		
		callOn(bmarks,(Bookmark b) -> {
			out.println(b.toJson());
		});
		
		List<String> bids=aux.getBooksIds();
		
		callOn(bids,(String bookId) -> {
			out.println(bookId);
		});
		
		List<Rating> ratings=aux.getRatings();
		
		callOn(ratings,(Rating r) -> {
			out.println(r.toJson());
		});
		
		List<Shelf> shelfs=aux.getShelfs();
		callOn(shelfs, (Shelf s) -> {
			out.println(s.toJson());
		});
		
		out.println("Achievments: "+ach.size());
		out.println("Bookmarks: "+bmarks.size());
		out.println("Book Ids: "+bids.size());
		out.println("Ratings: "+ratings.size());
		
		KoboMain km=new KoboMain();
		
		out.println(km.api());
		
	}
}

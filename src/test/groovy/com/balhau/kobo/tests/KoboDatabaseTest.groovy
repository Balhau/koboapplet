package com.balhau.kobo.tests

import java.sql.ResultSet;
import java.util.List;

import org.junit.Assert;

import spock.lang.*

import com.balhau.kobo.device.DeviceUtils;
import com.balhau.kobo.interfaces.IKoboDatabase
import com.balhau.kobo.model.Bookmark;
import com.balhau.kobo.model.KoboAchievement;
import com.balhau.kobo.model.KoboBook;
import com.balhau.kobo.model.Rating;
import com.balhau.kobo.sql.KoboSQLite

class KoboDatabaseTest extends Specification {
	
	private IKoboDatabase kdb
	private String concurencyBook="file:///mnt/sd/Subramaniam, Venkat/Programming Concurrency on the JVM (for jay muratore) - Venkat Subramaniam.epub"
	
	def setup(){
		List<String> ldv=DeviceUtils.getDevicesInfo();
		kdb=new KoboSQLite(DeviceUtils.getSQLitePathFromKoboPath(ldv.get(0)));
	}
	
	def "Number of ebooks on Kobo"() {
		when:
			List results=kdb.getAllBookNames()
		then:
			results.size()==918
	}
	
	def "Number of achievements on Kobo"(){
		when:
			List results=kdb.getAchievements()
		then:
			results.size()==27
	}
	
	def "Check Kobo DB Version"(){
		when:
			int version=kdb.getVersion()
		then:
			version==109
	}
	
	def "Get reading books IDs"(){
		when:
			List rb=kdb.getReadingBookIDs()
		then:
			rb.size()==351
	}
	
	def "Get Book by Content ID"(){
		when:
			KoboBook book=kdb.getBookByContentID("file:///mnt/onboard/Davies, Joshua/Implementing SSL _ TLS Using Cryptography and PKI - Davies, Joshua.pdf")
		then:
			book.getPercentageReaded()==99;
			book.getBookTitle().equals("Implementing SSL / TLS Using Cryptography and PKI")
			book.getContentType().equals(9)
			book.getMimeType().equals("application/pdf")
	}
	
	def "Get Books By Name"(){
		when:
			List books=kdb.getBooksByName("SSL")
		then:
			books.size()==6
	}
	
	def "Get current readings"(){
		when:
			List books=kdb.getReadingBookIDs()
		then:
			books.size()==351
	}
	
	
	def "Check last date read from Ebook Entry"(){
		when:
			KoboBook book=kdb.getBookByContentID(concurencyBook)
		then:
			String lastread=book.getDateLastRead()
			lastread.equals("2013-11-27T11:38:08Z");
	}
	
	def "Get achievements"(){
		when:
			List<KoboAchievement> achievements=kdb.getAchievements();
		then:
			Assert.assertEquals(achievements.size(),27);
	}
	
	def "Get Bookmarks"(){
		when:
			List<Bookmark> bookmarks = kdb.getBookmarks();
		then:
			Assert.assertEquals(bookmarks.size(),123);
	}
	
	def "Get Books ids"(){
		when:
			List<String> bookIds=kdb.getBooksIds();
		then:
			Assert.assertEquals(bookIds.size(),1781);
	}
	
	def "Get Ratings"(){
		when:
			List<Rating> ratings=kdb.getRatings();
		then:
			Assert.assertEquals(ratings.size(),14);
	}
}


package com.balhau.kobo.tests

import java.sql.ResultSet;
import java.util.List;

import spock.lang.*

import com.balhau.kobo.device.DeviceUtils;
import com.balhau.kobo.interfaces.IKoboDatabase
import com.balhau.kobo.model.KoboBook;
import com.balhau.kobo.sql.KoboSQLite

class KoboDatabaseTest extends Specification {
	
	private IKoboDatabase kdb
	
	def setup(){
		List<String> ldv=DeviceUtils.getDevicesInfo();
		kdb=new KoboSQLite(DeviceUtils.getSQLitePathFromKoboPath(ldv.get(0)));
	}
	
	def "Number of ebooks on Kobo"() {
		when:
			List results=kdb.getAllBookNames()
		then:
			results.size()==677
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
			version==92
	}
	
	def "Get reading books IDs"(){
		when:
			List rb=kdb.getReadingBookIDs()
		then:
			rb.size()==94
	}
	
	def "Get Book by Content ID"(){
		when:
			KoboBook book=kdb.getBookByContentID("file:///mnt/onboard/Davies, Joshua/Implementing SSL _ TLS Using Cryptography and PKI - Davies, Joshua.pdf")
		then:
			book.getPercentageReaded()==55;
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
			List books=kdb.getCurrentReadings()
		then:
			books.size()==94
	}
}
#Kobo Utility

This is a java component that interacts with the Kobo database and retrieve meaningful data. Now this is coupled with an applet implementation that it will serve us to extract data from the device in a web application context.

##The API

The java web start aplication serve as an example as well as a way to expose the Java API to the browser JavaScript virtual machine and in this way we got a tool to get meaningfull information from kobo's sqlite database.

The functionalities that are exposed to JavaScript are those described in the interface **IKoboAPI**. At the time of this writing these are the methods

    public interface IKoboAPI {

    	int getDatabaseVersion() throws KoboSQLException;
    	String getDetectedDevices() throws KoboSQLException;
    	String getCurrentReadings() throws KoboSQLException;
    	String getCurrentReadingsIDs() throws KoboSQLException;
    	String getBookByContentID(String contentID) throws KoboSQLException;
    	String getAchievements() throws KoboSQLException;
    	String getBookmarks() throws KoboSQLException;
    }


You'll find another interface, **IKoboDatabase** , that holds the operations that are being done to the SQLite database. You should note that not all of the operations to the database are exposed in **IKoboAPI**. At the time of this writing these are the operations defined in the **IKoboDatabse** interface

    public interface IKoboDatabase {
    	public int getVersion() throws KoboSQLException;
    	public List<KoboBook> getCurrentReadings() throws KoboSQLException;
    	public List<String> getReadingBooksContentID() throws KoboSQLException;
    	public List<String> getReadingBookIDs() throws KoboSQLException;
    	public List<String> getBooksIds() throws KoboSQLException;
    	public KoboBook getBookByContentID(String contentID) throws KoboSQLException;
    	public List<KoboBook> getBooksByName(String name) throws KoboSQLException;
    	public List<KoboAchievement> getAchievements() throws KoboSQLException;
    	public void exportToDatabase(IDBExporter dbexporter) throws KoboSQLException;
    	public List<Bookmark> getBookmarks() throws KoboSQLException;
    	public List<Rating> getRatings() throws KoboSQLException;
    }


###Note

This code has features of Java 8 so you must download the latest version of the JDK from Oracle to build this code.

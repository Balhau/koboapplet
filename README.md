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

#Kobo Utility

##Introduction
This is a java component that interacts with the Kobo database and retrieve meaningful data.
Now this is coupled with an applet implementation that it will serve us to extract data from the device in a web application context.
Aside from the java API and the Java Applet that exports the objects into a Javascript virtual machine environment we also have a JavaScript wrapper layer
to help the invocation of the Java methods and that wraps that in Javascript objects.



![Kobo Utility](http://shared.balhau.net/kobo/koboUtility.png "Kobo Utility Scheme")



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


You'll find another interface, **IKoboDatabase** , that holds the operations that are being done to the SQLite database. You should note that not all of the operations to the database are exposed in **IKoboAPI**. At the time of this writing these are the operations defined in the **IKoboDatabase** interface

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
      public List<Shelf> getShelfs() throws KoboSQLException;
    }


##The Javascript

The Javascript wrapper API is on the file called *kobo.js* and it is composed mainly with wrapper structures and wrapper methods.
The **Kobo** object is where all the structures and methods live and its constructor signature
are like this:

    var Kobo=function(appletObject){
      this.applet=appletObject;
    }

Where the *appletObject* is the DOM element that represents the applet object and
can be fetch as here:

    var appletObjecT=document.getElementsByTagName("applet")[position];

In the kobo.js file you also have some structures to hold the query results.
They live inside the Kobo object as sub objects. These are some interesting ones

    Kobo.Book=function(title,id,contentType,mimeType,percentageRead,dateLastRead){
      this.title=title;
      this.bookId=id;
      this.contentType=contentType;
      this.mimeType=mimeType;
      this.percentageRead=percentageRead;
      this.dateLastRead=dateLastRead;
    };

    Kobo.Achievement=function(completeDescription,eventLogDescription,incompleteDescription,dateCreated,imageId,name,percentComplete,userID){
      this.completeDescription=completeDescription;this.eventLogDescription=eventLogDescription;
      this.incompleteDescription=incompleteDescription;this.dateCreated=dateCreated;
      this.imageId=imageId;this.name=name;this.percentComplete=percentComplete;
      this.userID=userID;
    };

These objects hold the results of queries like the following

    var a=document.getElementsByTagName("applet")[0];
    var k=new Kobo(a);
    var achievements=k.getAchievements();
    achievements.map(function(el){
      console.log(JSON.stringify(el));
    });

This will result in the console printing of the achievements information that are
stored in Kobo Ereader

    {"completeDescription":"You're now part of Kobo, and an official Booklover!",
      "eventLogDescription":"BookLover. Welcome to Kobo! We're so glad you've joined us, a tribe of BookLovers, just like you.",
      "incompleteDescription":"Want to become a Kobo BookLover? Sign in to your account, or create a new one today.",
      "dateCreated":"2011-05-18T14:23:12.430",
      "imageId":"badge_trilogy_bw_0002_booklover",
      "name":"BookLover","percentComplete":100,
      "userID":""}
    {"completeDescription":"You started reading a new book.",
      "eventLogDescription":"Once Upon a Time. You're diving into a brand new book - we sure hope it's a great read.",
      "incompleteDescription":"It was a dark and stormy night… start a new book to earn the Once Upon a Time Award.",
      "dateCreated":"2011-05-18T14:23:12.445",
      "imageId":"badge_trilogy_bw_0005_onceuponatime",
      "name":"Once Upon a Time",
      "percentComplete":100,
      "userID":"4dd0aab3-8501-458b-ae35-80d5471ee30b"}
      .
      .
      .



###Note

This code has features of Java 8 so you must download the latest version of the JDK from Oracle to build this code.

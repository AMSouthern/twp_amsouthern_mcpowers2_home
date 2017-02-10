package edu.bsu.cs222.XML;


import javax.swing.text.Document;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 *
 * This class retrieves the XML data from Wikipedia.
 */


public class Retrieving {


        //Initial check for connection to Wikipedia.
        //Check the test (it is not importing this class correctly)
    public boolean checkForInternetConnection() throws Exception {
        try {
            final URL url = new URL("http://www.wikipedia.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }

     public static Document createDocument(String userEmailAddress, String title) throws IOException {
            URLConnection connection = connectToWikipedia(userEmailAddress, title);
            return makeXMLFile(connection);
     }


    public static URLConnection connectToWikipedia(String userEmailAddress, String query) throws IOException {
        query = query.replaceAll(" ", "+");
        URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&prop=revisions&format=xml&rvprop=timestamp%7Cuser&rvlimit=30&titles=" + query + "&redirects=");
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Revision Tracker/0.1 (" +userEmailAddress+ ")");
        connection.connect();
        return connection;
    }
    public String createHistoryOfQueryURL(String query){
        String url = "https://en.wikipedia.org/w/index.php?title="+ query.replace(" ", "+") + "&action=history";
        return url;
    }



}

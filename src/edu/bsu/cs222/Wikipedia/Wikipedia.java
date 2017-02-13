package edu.bsu.cs222.Wikipedia;


import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static edu.bsu.cs222.Wikipedia.Checks.checkForRedirect;
import static edu.bsu.cs222.Wikipedia.Checks.checkIfPageExists;
import static edu.bsu.cs222.Wikipedia.EditData.getRevisionsList;


/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 *
 * This class retrieves and parses all the Wikipedia data into readable/ usable information.
 */

public class Wikipedia {

    private static URL url;
    private boolean connectionAvailable;
    private URLConnection connection;
    static Document connected;

    public String queryInformation(String userEmailAddress, String searchTopic) throws Exception {
        connected = makeXMLFile(userEmailAddress, searchTopic);
        checkForInternetConnection(userEmailAddress);
        if (connectionAvailable){
            if(!checkIfPageExists(connected)){
                return "There is not a Wikipedia page for the query you entered.";
            }
            else{
                //This is where we actually search for the information
                //Add Labels for connection, timestamp and then query information

                String print  = checkForRedirect(connected);
                print = print + getRevisionsList();

                return (print);

                //return "";
            }
        }
        else{
            return "Check your connection.";
        }

    }

    //Initial check for connection to Wikipedia.
    private boolean checkForInternetConnection(String userEmailAddress) throws Exception {
        try {

            makeWikiConnection(userEmailAddress);
            connectionAvailable = true;
        } catch (IOException e) {
            connectionAvailable = false;
        }
        return false;
    }

    private static Document makeXMLFile(String userEmailAddress, String title) throws IOException, ParserConfigurationException, SAXException {
        URLConnection connection = connectToWikipedia(userEmailAddress, title);
        return readXMLFile(connection);
    }

    private static URLConnection connectToWikipedia(String userEmailAddress, String title) throws IOException {
        URL fullUrl = createHistoryOfQueryURL(title);
        URLConnection connection = fullUrl.openConnection();
        connection.setRequestProperty("User-Agent", "Revision Tracker/01 (" +userEmailAddress+ ")");
        connection.connect();
        return connection;
    }

    private static Document readXMLFile(URLConnection connection) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        //System.out.print(documentBuilder.parse(connection.getInputStream()));
        return documentBuilder.parse(connection.getInputStream());
    }


    private void makeWikiConnection(String userEmail) throws IOException {
        URL newURL = (url);

        connection = newURL.openConnection();
        connection.setRequestProperty("User-Agent", "Revision Tracker/0.1 (" + userEmail + ")");
        connection.connect();
    }


    private static URL createHistoryOfQueryURL(String query) throws MalformedURLException {
        query = query.replace(" ", "+");
        url = new URL("https://en.wikipedia.org/w/api.php?action=query&prop=revisions&format=xml&rvprop=timestamp%7Cuser&rvlimit=30&titles=" + query +"&redirects=");
        return url;
    }
}


package edu.bsu.cs222.Wikipedia;


import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URL;

import static edu.bsu.cs222.Wikipedia.Checks.checkIfPageExists;


/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 *
 * This class retrieves and parses all the Wikipedia data into readable/ usable information.
 */

public class Wikipedia {

    private String url;
    private boolean connectionAvailable;
    private URLConnection connection;

    public String queryInformation(String userEmailAddress, String searchTopic) throws Exception {
        Document connected = makeXMLFile(userEmailAddress, searchTopic);
        checkForInternetConnection(userEmailAddress);
        if (checkForInternetConnection(userEmailAddress)){
            if(!checkIfPageExists(connected )){
                return "There is not a Wikipedia page for the query you entered.";
            }
            else{
                return "";
            }

        }
        else{
            return "Check your connection.";
        }

    }





    //Initial check for connection to Wikipedia.
    public boolean checkForInternetConnection(String userEmailAddress) throws Exception {
        try {

            makeWikiConnection(userEmailAddress);
            connectionAvailable = true;
        } catch (IOException e) {
            connectionAvailable = false;
        }
        return false;
    }

    public InputStream returnInputStream() throws IOException {
        return connection.getInputStream();
    }


    public static Document makeXMLDocument(String userEmailAddress, String title) throws IOException, ParserConfigurationException, SAXException {
        return makeXMLFile(userEmailAddress, title);
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
        return documentBuilder.parse(connection.getInputStream());
    }


    private void makeWikiConnection(String userEmail) throws IOException {
        URL newURL = new URL(url);

        connection = newURL.openConnection();
        connection.setRequestProperty("User-Agent", "Revision Tracker/0.1 (" + userEmail + ")");
        connection.connect();
    }


    public static URL createHistoryOfQueryURL(String query) throws MalformedURLException {
        query = query.replace(" ", "+");
        URL url = new URL ("https://en.wikipedia.org/w/index.php?title=" + query + "&action=history");
        return url;
    }
}


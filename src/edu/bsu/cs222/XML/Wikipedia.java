package edu.bsu.cs222.XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 *
 * This class retrieves and parses all the XML data into readable/ usable information.
 */

public class Wikipedia {


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

    public static Document createDocument(String userEmailAddress, String title) throws IOException, ParserConfigurationException, SAXException {
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




    private static Document makeXMLFile(URLConnection entry) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory buildDocument = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = buildDocument.newDocumentBuilder();
        Document XMLDocument = builder.parse(entry.getInputStream());
        return XMLDocument;
    }


}


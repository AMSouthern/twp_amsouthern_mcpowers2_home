package edu.bsu.cs222.XML;


import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import static edu.bsu.cs222.XML.UseXML.makeRevisionsList;

/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 *
 * This class retrieves and parses all the XML data into readable/ usable information.
 */

public class Wikipedia {

    private String url;
    private boolean connectionAvailable;
    private URLConnection connection;
    private List<Revision> revisionsList = new ArrayList<>();
    private UseXML parseRevisions = new UseXML();

    public String queryInformation(String searchTopic) throws Exception {
        createHistoryOfQueryURL(searchTopic);
        checkForInternetConnection();
        if (connectionAvailable) {
            makeRevisionsList(revisionsList, parseRevisions, connection);
            if (revisionsList.equals(new ArrayList<>())) {
                return "The is not a Wikipedia page for the name given";
            } else {
                return "";
                //return prepareListForPrint(revisionsList);
            }
        } else {
            return "There is not a connection to Wikipedia.";
        }
    }

    //Initial check for connection to Wikipedia.
    public boolean checkForInternetConnection() throws Exception {
        try {

            makeWikiConnection("");
            connectionAvailable = true;
        } catch (IOException e) {
            connectionAvailable = false;
        }
        return false;
    }

    public InputStream returnInputStream() throws IOException {
        return connection.getInputStream();
    }
/*
    public static Document makeXMLDocument(String userEmailAddress, String title) throws IOException, ParserConfigurationException, SAXException {
        URLConnection connection = connectToWikipedia(userEmailAddress);
        return readXMLFile(connection);
    }
*/

    private void makeWikiConnection(String userEmail) throws IOException {
        URL newURL = new URL(url);

        connection = newURL.openConnection();
        connection.setRequestProperty("User-Agent", "Revision Tracker/0.1 (" + userEmail + ")");
        connection.connect();
    }


    public String createHistoryOfQueryURL(String query){
        query = query.replace(" ", "+");
        String url = "https://en.wikipedia.org/w/index.php?title=" + query + "&action=history";
        return url;
    }
}


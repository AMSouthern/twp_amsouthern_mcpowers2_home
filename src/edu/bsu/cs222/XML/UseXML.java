package edu.bsu.cs222.XML;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import static edu.bsu.cs222.XML.Checks.checkIfPageExists;



/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 *
 * This class uses the XML provided from Wikipedia.java.
 */

public class UseXML {

    private Document document;

    public  List<Revision> parse(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException{
        List<Revision> revisionsList = new ArrayList();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(inputStream);

        if (!checkIfPageExists(document)) {
            return revisionsList;
        }

        NodeList revisions = document.getElementsByTagName("revisions");
        Element firstRevision = (Element) revisions.item(0);
        NodeList users = firstRevision.getElementsByTagName("rev");

        for (int index = 0; index < users.getLength(); index++) {
            Element user = (Element) users.item(index);
            revisionsList.add( new Revision(user.getAttribute("user"),user.getAttribute("timestamp")));
        }

        return revisionsList;
    }

    public static SimpleDateFormat convertTimeZone(String timeStamp) throws ParseException {
        TimeZone local = TimeZone.getDefault();
        SimpleDateFormat localTimeZone = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.0Z'");
        localTimeZone.setTimeZone(local);
        return localTimeZone;
    }
    /*
    public String prepareListForPrint(List<Revision> revisionsList, UseXML parseRevisions) {
        String preparedString = parseRevisions.checkForRedirect();

        for (Object edits : revisionsList) {
            preparedString += edits.toString() + "\n";
        }
        return preparedString;
    }

    */
    public static void makeRevisionsList(List<Revision> revisionsList, UseXML parseRevisions, URLConnection connection) throws IOException, ParserConfigurationException, SAXException {
        revisionsList = parseRevisions.parse(connection.getInputStream());
    }



    public static void readXMLFile(){
//TO DO
    }
}



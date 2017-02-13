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
import java.util.ArrayList;
import java.util.List;
import java.util.*;

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


    private NodeList getRevisionsList() {
        NodeList revisions = document.getElementsByTagName("revisions");
        Element firstRevision = (Element) revisions.item(0);
        return firstRevision.getElementsByTagName("rev");
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

    public String sortUserList() {
        //sort into list
        sortListByFrequency();
        //generate String from list
        //return String to print
        return "";
    }

    public void sortListByFrequency() {
        NodeList users = getRevisionsList();
        Element user;

        int maxRevs = findMaximumRevs();
        HashMap<String, Integer> userMap = new HashMap<>();
        List<Map.Entry<String, Integer>> list = new ArrayList<>();

        for( int index = 0; index < maxRevs; index++) {
            user = (Element) users.item(index);
            Revision newUser = new Revision(user.getAttribute("user"),user.getAttribute("timestamp"));
            userMap.put(user.getAttribute("user"), countFrequency(maxRevs, newUser));
            list.add(new AbstractMap.SimpleEntry<>(user.getAttribute("user"), countFrequency(maxRevs,newUser)));
        }

        list.sort(Revision::comparator);

        for (int index = list.size() - 1; index >=0; index--) {
            System.out.println(29 - index + ".) Username: " + list.get(index).getKey() + "\n");
        }
    }

    private Integer countFrequency(int maxRevs, Revision newUser) {
        int frequency = 0;
        NodeList users = getRevisionsList();
        Element user;

        for (int index = 0; index < maxRevs; index++) {
            user = (Element) users.item(index);
            Revision compareUser = new Revision(user.getAttribute("user"),user.getAttribute("timestamp"));
            if(compareUser.equals(newUser)) {
                frequency++;
            }
        }
        return frequency;
    }

    private int findMaximumRevs() {
        NodeList users = getRevisionsList();
        if(users.getLength() > 30) {
            return 30;
        }
        return users.getLength();
    }

}



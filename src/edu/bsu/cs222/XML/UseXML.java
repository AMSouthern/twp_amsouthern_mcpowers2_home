package edu.bsu.cs222.XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 *
 * This class uses the XML provided from Wikipedia.java.
 */

public class UseXML {

    private String user;
    private String timeStamp;


    public UseXML(int revisionNumber, Document pageXml) {
       if (revisionNumber < MaxEdits(pageXml)) {
            user = getRevisedUser(revisionNumber, pageXml);
            timeStamp = getRevisedTimeStamp(revisionNumber, pageXml);
        }
        else {
            user = null;
            timeStamp = null;
        }
    }


    private String getUser() {
        return user;
    }

    private String getTimeStamp() {
        return timeStamp;
    }


    public static SimpleDateFormat convertTimeZone(String timeStamp) throws ParseException {
        TimeZone local = TimeZone.getDefault();
        SimpleDateFormat localTimeZone = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.0Z'");
        localTimeZone.setTimeZone(local);
        return localTimeZone;
    }



    public String getRevisedUser(int revisionNumber, Document pageXml) {
        NodeList revs = readEdits(pageXml);
        Element rev = (Element) revs.item(revisionNumber);
        return rev.getAttribute("user");
    }


    private String getRevisedTimeStamp(int editedNumber, Document pageXml) {
        NodeList edits = readEdits(pageXml);
        Element edit = (Element) edits.item(editedNumber);
        return edit.getAttribute("timestamp");
    }


    static void printEdits(Document pageXml) {
        int maximumNumberOfEdits = MaxEdits(pageXml);

        for (int i = 0; i < maximumNumberOfEdits; i++) {
            UseXML XMLData = new UseXML(i, pageXml);
            System.out.println(i+ ". Username: " + XMLData.getUser());
            System.out.println("Time of Edit: " + XMLData.getTimeStamp() + "\n");
        }
    }


    private static int MaxEdits(Document pageXml) {
        NodeList edits = readEdits(pageXml);
        int maxRevs;
        if (edits.getLength() > 30)
            maxRevs = 30;
        else
            maxRevs = edits.getLength();
        return maxRevs;

    }

    private static NodeList readEdits(Document XMLFile) {
        NodeList edits = XMLFile.getElementsByTagName("revisions");
        Element  firstEdit = (Element) edits.item(0);
        return getEditValue(firstEdit);

    }

    private static NodeList getEditValue(Element firstEdit) {
        return firstEdit.getElementsByTagName("rev");
    }


}

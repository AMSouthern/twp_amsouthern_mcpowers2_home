package edu.bsu.cs222.Wikipedia;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

import static edu.bsu.cs222.Wikipedia.EditFrequency.findFrequencyOfUserEdits;
import static edu.bsu.cs222.Wikipedia.EditFrequency.findMaximumNumberOfEditsToPrint;
import static edu.bsu.cs222.Wikipedia.TimeStamp.convertTimeStamp;

/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 *
 * This class edits the data from the XML file.
 */

public class EditData {
    
    private String userName;
    private String timeStamp;
    private int frequency;
    public static Document XMLDocument;

    public EditData(int revisionNumber) throws ParseException {
        if (revisionNumber < findMaximumNumberOfEditsToPrint()) {
            userName = grabAttributeOfRevision(revisionNumber, "user");
            String timeStampOnWiki = grabAttributeOfRevision(revisionNumber, "timestamp");
            timeStamp = convertTimeStamp(timeStampOnWiki);
            frequency = findFrequencyOfUserEdits(revisionNumber);
        }
        else {
            userName = null;
            timeStamp = null;
            frequency = 0;
        }
    }

    private String getUser() {
        return this.userName;
    }

    private String getTimeStamp() {
        return this.timeStamp;
    }

    private int getFrequency() {
        return this.frequency;
    }

    static void setDocument(Document XmlDocument) {
        XMLDocument = XmlDocument;
    }



    /////////////////////////////////////////////////////
    private static void QuickSort(ArrayList<EditData> arrayOfRevisions, int firstIndex, int lastIndex) {
        if (firstIndex < lastIndex) {
            int partitionIndex = Partition(arrayOfRevisions, firstIndex, lastIndex);
            QuickSort(arrayOfRevisions, firstIndex, partitionIndex-1);
            QuickSort(arrayOfRevisions, partitionIndex+1, lastIndex);
        }
    }

    private static int Partition(ArrayList<EditData> arrayOfRevisions, int firstIndex, int lastIndex) {
        int pivot = arrayOfRevisions.get(lastIndex).getFrequency();
        int leftIndex = firstIndex - 1;
        for (int rightIndex = firstIndex; rightIndex < lastIndex; rightIndex++) {
            if (arrayOfRevisions.get(rightIndex).getFrequency() > pivot) {
                leftIndex++;
                Collections.swap(arrayOfRevisions, leftIndex, rightIndex);
            }
        }
        Collections.swap(arrayOfRevisions, leftIndex+1, lastIndex);
        return leftIndex+1;
    }
    /////////////////////////////////////////////////////



    protected static int findMaximumEdits() {
        NodeList users = getRevisionsList();
        if (users.getLength() > 30) {
            return 30;
        }
        return users.getLength();
    }

    protected static NodeList getRevisionsList() {
        NodeList edits = XMLDocument.getElementsByTagName("revisions");
        Element firstEdit = (Element) edits.item(0);
        return firstEdit.getElementsByTagName("rev");
    }

    protected static String grabAttributeOfRevision(int revisionNumber, String attribute) {
        NodeList edits = readEdits();
        Element edit = (Element) edits.item(revisionNumber);
        return edit.getAttribute(attribute);
    }

    private static NodeList readEdits() {
        NodeList edits = XMLDocument.getElementsByTagName("revisions");
        Element firstEdit = (Element) edits.item(0);
        return grabEditValueFromEdits(edits, firstEdit);
    }

    protected static NodeList grabEditValueFromEdits(NodeList edits, Element firstEdit) {
        NodeList revs = null;
        if (edits.getLength() != 0)
            revs = firstEdit.getElementsByTagName("rev");
        return revs;
    }

    //Output
    static String printEdits(ArrayList<EditData> arrayOfRevisions) throws ParseException {
        int index = 1;
        String stringEdits = "";
        for (EditData data: arrayOfRevisions) {
            stringEdits += "Edit Number: " + index + "\n";
            stringEdits += "Username: " + data.getUser()+"\n";
            stringEdits += "Local TimeStamp: " + data.getTimeStamp()+"\n";
            stringEdits += "Frequency: " + data.getFrequency() +"\n\n";
            index++;
        }
        return stringEdits;
    }
}

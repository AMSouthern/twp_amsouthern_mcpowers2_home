package edu.bsu.cs222.Wikipedia;


import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.text.ParseException;
import java.util.*;


import static edu.bsu.cs222.Wikipedia.EditData.grabAttributeOfRevision;
import static edu.bsu.cs222.Wikipedia.EditData.grabEditValueFromEdits;


/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 *
 * This class gets the frequency of edits for a Wikipedia page.
 */
public class EditFrequency extends Wikipedia{


    protected static int findFrequencyOfUserEdits(int revisionNumber) throws ParseException {
        ArrayList<String> arrayOfUsers = arrayOfRevisionUsers();
        String username = grabAttributeOfRevision(revisionNumber, "user");
        return Collections.frequency(arrayOfUsers, username);
    }

    private static ArrayList<String> arrayOfRevisionUsers() throws ParseException {
        ArrayList<String> arrayOfUsers = new ArrayList<>();
        int maximumNumberOfRevs = findMaximumNumberOfEditsToPrint();
        String username;
        for (int i = 0; i < maximumNumberOfRevs; i++) {
            username = grabAttributeOfRevision(i, "user");
            arrayOfUsers.add(username);
        }
        return arrayOfUsers;
    }

    static int findMaximumNumberOfEditsToPrint() {
        NodeList revs = readRevisions();
        int maximumNumberOfRevs;
        if (revs.getLength() >=30)
            maximumNumberOfRevs = 30;
        else
            maximumNumberOfRevs = revs.getLength();
        return maximumNumberOfRevs;
    }

    private static NodeList readRevisions() {
        NodeList revisions = connected.getElementsByTagName("revisions");
        Element firstRevision = (Element) revisions.item(0);
        return grabEditValueFromEdits(revisions, firstRevision);
    }
}

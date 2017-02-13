package edu.bsu.cs222.Wikipedia;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 *
 * This class gets the frequency of edits for a Wikipedia page.
 */
public class EditFrequency {


    protected static int findFrequencyOfUserEdits(int revisionNumber) throws ParseException {
        ArrayList<String> arrayOfUsers = arrayOfRevisionUsers();
        String username = grabAttributeOfRevision(revisionNumber, "user");
        return Collections.frequency(arrayOfUsers, username);
    }

    private static ArrayList<String> arrayOfRevisionUsers() throws ParseException {
        ArrayList<String> arrayOfUsers = new ArrayList<>();
        int maximumNumberOfRevs = findMaximumNumberOfRevsToPrint();
        String username;
        for (int i = 0; i < maximumNumberOfRevs; i++) {
            username = grabAttributeOfRevision(i, "user");
            arrayOfUsers.add(username);
        }
        return arrayOfUsers;
    }


}

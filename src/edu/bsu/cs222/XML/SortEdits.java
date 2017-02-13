package edu.bsu.cs222.XML;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 *
 * This class checks for changes in the user's input,
 * as well as the page existence.
 */
public class SortEdits {


    public static SimpleDateFormat convertTimeZone(String timeStamp) throws ParseException {
        TimeZone local = TimeZone.getDefault();
        SimpleDateFormat localTimeZone = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.0Z'");
        localTimeZone.setTimeZone(local);
        return localTimeZone;
    }
}

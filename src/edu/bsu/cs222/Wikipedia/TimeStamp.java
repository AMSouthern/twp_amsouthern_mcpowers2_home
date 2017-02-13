package edu.bsu.cs222.Wikipedia;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 *
 * This class converts the TimeStamp to local time.
 */

public class TimeStamp {
    public static String convertTimeStamp(String timeStamp) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        Date date = format.parse(timeStamp);
        Date local = new Date(date.getTime() + TimeZone.getTimeZone("EST").getRawOffset());
        return local.toString();
    }
}

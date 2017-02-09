package edu.bsu.cs222.Exceutor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 *
 * This class converts the timezone to the local timezone.
 */

public class Timezone {

    public static SimpleDateFormat convertTimeZone(String timeStamp) throws ParseException {
        TimeZone local = TimeZone.getDefault();
        SimpleDateFormat localTimeZone = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.0Z'");
        localTimeZone.setTimeZone(local);
        return localTimeZone;
        }

}

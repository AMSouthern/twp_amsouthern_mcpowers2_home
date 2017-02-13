package edu.bsu.cs222.XML;

import java.util.Map;

/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 *
 * This class compares the first author and the second author.
 */

public class Revision {
    private String username;
    private String timestamp;

    public Revision(String name, String time) {
        username = name;
        timestamp = time;
    }

    @Override
    public String toString() {
        return username + "\t\t" + timestamp;
    }


    //Not finished
    public static int comparator(Map.Entry<String, Integer> user1, Map.Entry<String,Integer> user2) {
        if(user1.getValue() > user2.getValue()) {
            return 1;
        } else if (user1.getValue() < user2.getValue()) {
            return -1;
        } else if (user1.getKey().equals(user2.getKey())) {
            user2.setValue(user2.getValue() + 1);
            return 1;
        } else {
            return 0;
        }
    }
}

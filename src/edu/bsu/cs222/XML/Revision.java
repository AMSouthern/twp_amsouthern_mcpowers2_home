package edu.bsu.cs222.XML;

/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 *
 * This class
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
}

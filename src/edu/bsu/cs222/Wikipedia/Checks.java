package edu.bsu.cs222.Wikipedia;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 *
 * This class checks for changes in the user's input,
 * as well as the page existence.
 *
 * If the User was redirected it also provides the User with that information.
 */

public class Checks {

    private String From, To;

    public static boolean checkIfPageExists(Document document) {
        NodeList pageInformation = document.getElementsByTagName("page");
        Element page = (Element) pageInformation.item(0);
        if ((page.getAttribute("pageid")) == "") {
            return false;
        } else {
            return true;
        }
    }

    public static String checkForRedirect(Document document) {
        try {
            NodeList redirects = document.getElementsByTagName("r");
            Element redirect = (Element)redirects.item(0);
            //System.out.println("Redirect");
            return "Your search has been redirected from '" + redirect.getAttribute("from") + "' to '"
                    + redirect.getAttribute("to") + "'.\n\n";
        }
        catch(NullPointerException e){
            //System.out.println("No redirects");
            return "No redirects";
        }
    }

}

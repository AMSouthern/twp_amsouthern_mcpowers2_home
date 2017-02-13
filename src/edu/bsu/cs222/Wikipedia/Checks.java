package edu.bsu.cs222.Wikipedia;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.text.ParseException;

import static edu.bsu.cs222.Wikipedia.EditData.XMLDocument;

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

        if (page.getAttribute("pageid") == "") {
            return false;
        } else {
            return true;
        }
    }

    public String checkForRedirect(Document document) {
        try {
            NodeList redirects = document.getElementsByTagName("r");
            Element redirect = (Element)redirects.item(0);
            return "Your search has been redirected from '" + redirect.getAttribute("from") + "' to '"
                    + redirect.getAttribute("to") + "'.\n\n";
        }
        catch(NullPointerException e){
            return "";
        }
    }

    private Checks() {
        if (readRedirection() != null) {
            From = grabAttributeOfRedirection("from");
            To = grabAttributeOfRedirection("to");
        }
        else {
            From = null;
            To = null;
        }
    }

    private String grabAttributeOfRedirection(String attribute) {
        NodeList redirection = readRedirection();
        Element firstRedirect = (Element) redirection.item(0);
        String attributeOfRevision = firstRedirect.getAttribute(attribute);
        return attributeOfRevision;
    }
    public static String showRedirection() throws ParseException {
        String redirection;
        if (readRedirection() != null)
            redirection = printRedirection();
        else
            redirection = "No redirection was necessary!\n";
        return redirection;
    }

    public static String printRedirection() throws ParseException {
        NodeList reds = readRedirection();
        String redirection = "";
        if (reds.getLength() != 0) {
            Checks redirect = new Checks();
            redirection = "Redirected from: " + redirect.From + " to " + redirect.To + "\n";
        }
        return redirection;
    }

    public static NodeList readRedirection() {
        NodeList redirection = XMLDocument.getElementsByTagName("redirects");
        Element firstRedirect = (Element) redirection.item(0);
        NodeList reds = grabRedFromRedirections(redirection, firstRedirect);
        return reds;
    }

    public static NodeList grabRedFromRedirections(NodeList redirections, Element redirection) {
        NodeList reds = null;
        if (redirections.getLength() != 0)
            reds = redirection.getElementsByTagName("r");
        return reds;
    }
}

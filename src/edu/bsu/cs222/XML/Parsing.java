package edu.bsu.cs222.XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URLConnection;

/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 *
 * This class parses all the XML data into readable/ usable information.
 */

public class Parsing {

    private Document makeXMLFile(URLConnection entry) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory buildDocument = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = buildDocument.newDocumentBuilder();
        Document XMLDocument = builder.parse(entry.getInputStream());
        return XMLDocument;
    }

    private static Element getAuthorFromFile(Document XMLFile){
        NodeList books = XMLFile.getElementsByTagName("book");
        Element firstBook = (Element)books.item(0);
        NodeList authors = firstBook.getElementsByTagName("author");
        Element author = (Element)authors.item(0);
        return author;
    }


    private static String getFirst30AuthorFromFile(Document XMLFile){
        NodeList books = XMLFile.getElementsByTagName("book");
        Element firstBook = (Element)books.item(0);

        String list = new String();
        for (int i = 0; i<30; i++) {
            NodeList authors = firstBook.getElementsByTagName("author");
            Element author = (Element) authors.item(i);
            list = list + author;
        }
        return list;
    }

}

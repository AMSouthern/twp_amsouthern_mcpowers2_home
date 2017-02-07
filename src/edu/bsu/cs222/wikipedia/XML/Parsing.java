package edu.bsu.cs222.wikipedia.XML;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 */

public class Parsing {

    private static void makeXMLFile(String entry){
        try{
            File xmlFile = new File(entry);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void readXMLFile(String XMLFile){

    }
}

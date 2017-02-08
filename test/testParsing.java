import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ authors: Alexandria Southern and Marley Powers
 *
 * CS 222 - S2 David Largent
 * February 14, 2017
 *
 * This class tests all of the methods implemented.
 */
public class testParsing {


    private Document document;

    @Before
    public void initializeTests() throws ParserConfigurationException, IOException, SAXException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("demo.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(inputStream);
    }

    @Test
    public void testReadCatalogElement(){

        NodeList children = document.getChildNodes();
        Element catalogElement = (Element)children.item(0);
        Assert.assertEquals("catalog", catalogElement.getNodeName());
    }

    @Test
    public void testReadFirstAuthor(){
        NodeList books = document.getElementsByTagName("book");
        Element firstBook = (Element)books.item(0);
        NodeList authors = firstBook.getElementsByTagName("author");
        Element author = (Element)authors.item(0);
        Assert.assertEquals("Gambardella, Matthew", author.getTextContent());

    }
}

import edu.bsu.cs222.Wikipedia.Wikipedia;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
 * This class tests all of the methods implemented in UseXML and Wikipedia.
 */
public class testParsing {

    private Document document;
    private InputStream inputStream;
    private UseXML parser;
    private Wikipedia wikiPage;

    @Before
    public void setUp() throws ParserConfigurationException, IOException, SAXException {
        inputStream = getClass().getClassLoader().getResourceAsStream("test.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(inputStream);
    }

    @Test
    public void testReadWikiPage() {
        NodeList children = document.getChildNodes();
        Element apiElement = (Element) children.item(0);
        Assert.assertEquals("api", apiElement.getNodeName());
    }

    @Test
    public void testReadFirstRevisionUser() {
        NodeList revisions = document.getElementsByTagName("revisions");
        Element firstRevision = (Element) revisions.item(0);
        NodeList users = firstRevision.getElementsByTagName("rev");
        Element user = (Element)users.item(0);
        Assert.assertEquals("Northamerica1000",user.getAttribute("user"));
    }

    @Test
    public void testReadFirstRevisionTimestamp() {
        NodeList revisions = document.getElementsByTagName("revisions");
        Element firstEdit = (Element) revisions.item(0);
        NodeList users = firstEdit.getElementsByTagName("rev");
        Element user = (Element)users.item(0);
        Assert.assertEquals("2016-12-23T16:25:19Z",user.getAttribute("timestamp"));
    }
/*
    private void testMethods() throws IOException, ParserConfigurationException, SAXException {
        wikiPage.connect();
        parser.parse(wikiPage.returnInputStream());
    }
*/
}


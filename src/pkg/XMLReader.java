package pkg;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by sebo on 12/2/18.
 * janko.sebastian@gmail.com
 */
class XMLReader {
    String findGPSPosition(File f) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = documentBuilder != null ? documentBuilder.parse(f) : null;
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        assert document != null;
        String out = "";

        try {
            out = document.getElementsByTagName("Position").item(0).getTextContent();
        } catch (NullPointerException e) {
        }

        return out;
    }
}

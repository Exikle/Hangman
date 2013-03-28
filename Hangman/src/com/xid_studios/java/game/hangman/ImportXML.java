package com.xid_studios.java.game.hangman;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ImportXML {
    static class Values {
        static int height;
        static int width;
    }

    public static void importValues() {
        try {
            File xmlFile = new File("res/information.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            NodeList nList = doc.getElementsByTagName("int");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    Values.height = Integer.parseInt(eElement
                            .getElementsByTagName("height").item(0)
                            .getTextContent());

                    Values.width = Integer.parseInt(eElement
                            .getElementsByTagName("width").item(0)
                            .getTextContent());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

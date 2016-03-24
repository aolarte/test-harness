package com.andresolarte.harness.xml;


import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;

public class DOMCreate {

    public static void main(String argv[]) {

        try {
            Document doc = createDocument();
            //Root element
            Element rootElement = doc.createElement("test");
            doc.appendChild(rootElement);

            Element list = doc.createElement("list");
            rootElement.appendChild(list);

            //Attribute to element
            Attr attr = doc.createAttribute("test-attr");
            attr.setValue("Value #1");
            list.setAttributeNode(attr);

            //Element
            Element item = doc.createElement("item");
            Attr attrType = doc.createAttribute("item-attr");
            attrType.setValue("Value #2");
            item.setAttributeNode(attrType);
            item.appendChild(
                    doc.createTextNode("My Text "));
            list.appendChild(item);

            //Use a transformer
            Transformer transformer = createTransformer();

            DOMSource source = new DOMSource(doc);

            // Output to string
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            StreamResult byteArrayResult =
                    new StreamResult(byteArrayOutputStream);
            transformer.transform(source, byteArrayResult);

            String result=new String(byteArrayOutputStream.toByteArray());
            System.out.println("Result:");
            System.out.println( result);
        } catch (TransformerException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private static Transformer createTransformer() throws TransformerConfigurationException {
        TransformerFactory transformerFactory =
                TransformerFactory.newInstance();
        Transformer transformer =
                transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "2");
        return transformer;
    }

    private static Document createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder =
                dbFactory.newDocumentBuilder();
        return dBuilder.newDocument();
    }
}
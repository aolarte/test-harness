package com.andresolarte.harness.xml;


import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.StringWriter;

public class StAXCreate {
    public static void main(String... args) {
        try {
            StringWriter stringWriter = new StringWriter();

            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(stringWriter);
            xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeStartElement("test");

            xmlStreamWriter.writeStartElement("list");
            xmlStreamWriter.writeAttribute("test-attr","Value #1");

            xmlStreamWriter.writeStartElement("item");
            xmlStreamWriter.writeAttribute("item-attr","Value #2");
            xmlStreamWriter.writeCharacters("My Text");
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();


            String result = stringWriter.getBuffer().toString();

            stringWriter.close();

            System.out.println("Result:");
            System.out.println(result);

        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

    }
}

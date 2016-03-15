package com.andresolarte.harness.xml;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringWriter;


public class XLSTRunner {
    public static void main(String... args) {
        try {
            new XLSTRunner().run();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void run() throws TransformerException {

        StreamSource styleSource = new StreamSource(getClass().getResourceAsStream("/style1.xlst"));

        StreamSource input = new StreamSource(getClass().getResourceAsStream("/input1.xml"));

        Transformer transformer = TransformerFactory.newInstance().newTransformer(styleSource);

        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);

        transformer.transform(input,result);

        System.out.println(writer.toString());

    }
}

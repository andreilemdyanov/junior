package ru.job4j.jdbc;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Class SaxP.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 23.12.2017
 */
public class SaxP {

    public SaxP(final String fileName) {
        this.fileName = fileName;
    }

    final String fileName;

    public void init() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                long count = 0;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equals("entry")) {
                        for (int i = 0; i < attributes.getLength(); i++) {
                            String aname = attributes.getLocalName(i);
                            if (aname.equals("field")) {
                                count += Long.valueOf(attributes.getValue(i));
                            }
                        }
                    }
                }

                @Override
                public void endDocument() throws SAXException {
                    System.out.printf("Summ of all values = %s\n", count);
                }
            };
            saxParser.parse(fileName, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
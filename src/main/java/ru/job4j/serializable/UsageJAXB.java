package ru.job4j.serializable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UsageJAXB {

    public static void main(String[] args) throws Exception {
        final Student student = new Student(false, 30, "Den",
                new Contact(30, "7867"), "Math", "Computer science"
        );
        try (PrintWriter pw = new PrintWriter("./xmlJAXB.txt")) {
            pw.print(getXMLWithJAXB(student));
        }
        String xml = Files.readString(Paths.get("./xmlJAXB.txt"));
        Student fromXMLToObject = getObjectFromXML(xml);
        System.out.println(fromXMLToObject);
    }

    public static Student getObjectFromXML(String xml) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(xml);
        return  (Student) unmarshaller.unmarshal(reader);
    }

    public static String getXMLWithJAXB(Student student) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter writer = new StringWriter();
        marshaller.marshal(student, writer);
        return writer.getBuffer().toString();
    }
}

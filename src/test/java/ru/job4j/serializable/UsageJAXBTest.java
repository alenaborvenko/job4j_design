package ru.job4j.serializable;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UsageJAXBTest {

    @Test
    public void whenGetXMLWithJAXB() throws Exception {
        Student student = new Student(false, 30, "Den",
                new Contact(30, "7867"), "Math", "Computer science"
        );
        String actual = UsageJAXB.getXMLWithJAXB(student);
        assertThat(actual, is(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                    + "<student sex=\"false\" age=\"30\" name=\"Den\">\n"
                    + "    <contact zipCode=\"30\" phone=\"7867\"/>\n"
                    + "    <courses>\n"
                    + "        <course>Math</course>\n"
                    + "        <course>Computer science</course>\n"
                    + "    </courses>\n"
                    + "</student>\n"));
    }

    @Test
    public void whengetObjectFromXML() throws Exception {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<student sex=\"false\" age=\"30\" name=\"Den\">\n"
                + "    <contact zipCode=\"30\" phone=\"7867\"/>\n"
                + "    <courses>\n"
                + "        <course>Math</course>\n"
                + "        <course>Computer science</course>\n"
                + "    </courses>\n"
                + "</student>\n";
        Student actual = UsageJAXB.getObjectFromXML(xml);
        assertThat(actual, is(
                new Student(false, 30, "Den",
                        new Contact(30, "7867"), "Math", "Computer science")
                ));
    }

    @Test
    public void whenTestMain() throws Exception {
        PrintStream old = System.out;
        ByteArrayOutputStream print = new ByteArrayOutputStream();
        System.setOut(new PrintStream(print));
        UsageJAXB.main(new String[0]);
        assertThat(print.toString(), is(
                new Student(false, 30, "Den",
                            new Contact(30, "7867"), "Math", "Computer science")
                        + System.lineSeparator()
        ));
        System.setOut(old);
    }
}
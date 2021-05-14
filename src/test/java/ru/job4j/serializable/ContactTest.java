package ru.job4j.serializable;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ContactTest {
    private String path;

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();

    @Before
    public void setUp() throws IOException {
        path = tmp.newFile("serial.txt").toString();
    }

    @Test
    public void whenSerializationThenDeserializ() throws IOException, ClassNotFoundException {
        Contact contact = new Contact(34, "8-913-875-98-09");
        Contact.serialization(contact, path);
        assertThat(Contact.deserialization(path), is(contact));
    }

    @Test
    public void whenTestToString() {
        Contact contact = new Contact(34, "8-913-875-98-09");
        assertThat(contact.toString(), is(
                "Contact[zipCode="
                + contact.getZipCode()
                +", phone='"
                + contact.getPhone()
                +"']"
        ));
    }

    @Test
    public void whenTestMain() throws IOException, ClassNotFoundException {
        PrintStream oldPrint = System.out;
        ByteArrayOutputStream println = new ByteArrayOutputStream();
        System.setOut(new PrintStream(println));
        String[] empty = new String[0];
        Contact.main(empty);
        assertThat(println.toString(), is(
                "Contact[zipCode=123456, phone='+7 (111) 111-11-11']"
                + System.lineSeparator()
        ));
        System.setOut(oldPrint);
    }
}
package ru.job4j.serializable;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UsageJsonTest {

    @Test
    public void whenMainTest() {
        ByteArrayOutputStream println = new ByteArrayOutputStream();
        PrintStream oldPrintln = System.out;
        System.setOut(new PrintStream(println));
        String[] empty = new String[0];
        UsageJson.main(empty);
        assertThat(println.toString(), is(
                "{"
                        + "\"sex\":true,"
                        + "\"age\":20,"
                        + "\"name\":\"Pol\","
                        + "\"contact\":"
                            + "{"
                                    + "\"zipCode\":1,"
                                    + "\"phone\":\"7987545\""
                            + "},"
                        + "\"course\":"
                            + "[\"Math\",\"Computer science\"]"
                     + "}" + System.lineSeparator()
                        + "Student[sex=true, age=20, name='Pol', "
                        + "contact=Contact[zipCode=1, phone='7987545'], "
                        + "course=[Math, Computer science]]" + System.lineSeparator()
        ));
        System.setOut(oldPrintln);
    }
}
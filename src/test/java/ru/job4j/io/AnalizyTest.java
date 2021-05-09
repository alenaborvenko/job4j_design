package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void whenTestUnavailable() throws IOException {
        File sourceFile = temp.newFile("server.log");
        try (PrintWriter pw = new PrintWriter(sourceFile)) {
            pw.print("200 10:56:01" + System.lineSeparator()
                    + "500 10:57:01" + System.lineSeparator()
                    + "400 10:58:01\n" + System.lineSeparator()
                    + "200 10:59:01\n" + System.lineSeparator()
                    + "500 11:01:02\n" + System.lineSeparator()
                    + "200 11:02:02\n" + System.lineSeparator()
                    + "400 13:54:00\n" + System.lineSeparator()
                    + "500 13:56:45\n" + System.lineSeparator()
                    + "200 15:27:09");
        }
        String source = sourceFile.toString();
        String target = temp.newFile("target.log").toString();
        Analizy.unavailable(source, target);
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader buffReader = new BufferedReader(new FileReader(target))) {
            buffReader.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:57:01;10:59:01;" + "11:01:02;11:02:02;"
                                        + "13:54:00;15:27:09;"));
    }
}
package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Test
    public void whenTestUnavailable() throws IOException {
        String source = "./src/test/java/ru/job4j/io/server.log";
        String target = "./src/test/java/ru/job4j/io/tagret.log";
        Analizy.unavailable(source, target);
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader buffReader = new BufferedReader(new FileReader(target))) {
            buffReader.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:57:01;10:59:01;" + "11:01:02;11:02:02;"
                                        + "13:54:00;15:27:09;"));
    }
}
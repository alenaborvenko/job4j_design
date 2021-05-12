package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class SearchTest {
    Path path;

    @Before
    public void setUp() {
        path = Paths.get("./src");
    }

    @Test
    public void wheNoFoundFile() throws IOException {
        Predicate<Path> condition = s -> s.toFile().getName().endsWith(".ghg");
        assertTrue(!Search.search(path, condition).isEmpty());
    }

    @Test
    public void when3FoundFile() throws IOException {
        Predicate<Path> condition = s -> s.toFile().getName().endsWith(".properties");
        assertThat(Search.search(path, condition).size(), is(7));
    }
}
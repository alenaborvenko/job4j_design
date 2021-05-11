package ru.job4j.io.duplicates;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DuplicatesFinderTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() throws IOException {
        folder.newFile("new.txt");
        folder.newFile("new.xml");
        folder.newFile("new.java");
        folder.newFile("newSecond.java");
        folder.newFolder("folder");
    }
    @Test
    public void whenNotDuplicatesTest() throws IOException {
        DuplicatesFinder df = new DuplicatesFinder();
        df.search(folder.getRoot().toPath());
        assertThat(df.getDuplicates(), is(Collections.emptyList()));
    }


    @Test
    public void searchDuplicatesTest() throws IOException {
        folder.newFile("folder/new.java");
        folder.newFile("folder/new.xml");
        DuplicatesFinder df = new DuplicatesFinder();
        df.search(folder.getRoot().toPath());
        assertFalse(df.getDuplicates().isEmpty());
        assertEquals(2, df.getDuplicates().size());
        assertThat(df.getDuplicates(), containsInAnyOrder(
                new FileProperty(0, "new.xml"),
                new FileProperty(0, "new.java")));
    }
}
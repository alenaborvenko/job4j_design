package ru.job4j.finderfiles;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class FindTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private final String[] args = new String[4];
    private final PrintStream old = System.out;
    private final ByteArrayOutputStream print = new ByteArrayOutputStream();
    private File log;

    @Before
    public void setUp() throws IOException {
        folder.newFile("1.txt");
        folder.newFile("list.txt");
        folder.newFolder("list");
        folder.newFile("list/list.txt");
        folder.newFile("list/1.txt");
        log = folder.newFile("log.txt");
        args[0] = "-d=" + folder.getRoot().toString();
        args[1] = "-n=1.txt";
        args[2] = "-t=name";
        args[3] = "-o=log.txt";
        System.setOut(new PrintStream(print));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTestMainNoArgs() throws IOException {
        String[] args = new String[0];
        Find.main(args);
    }

    @Test
    public void whenTestMainFileNameFindToFile() throws IOException {
        Find.main(args);
        List<String> rsl = Files.readAllLines(log.toPath());
        assertThat(rsl,  containsInAnyOrder(
                       Path.of(folder.getRoot() + "/1.txt").toString(),
                       Path.of(folder.getRoot() + "/list/1.txt").toString()
                ));
    }

    @Test
    public void whenTestMainFileNameFindToConsole() throws IOException {
        args[3] = "-o=console";
        Find.main(args);
        List<String> actual = Arrays.asList(print.toString().split(", "));
        actual.set(0, actual.get(0).substring(1));
        int indexLastActual = actual.size() - 1;
        int sizeString = actual.get(indexLastActual).length();
        actual.set(indexLastActual, actual.get(indexLastActual).substring(0, sizeString - 1));
        assertThat(actual, containsInAnyOrder(
                Path.of(folder.getRoot() + "/1.txt").toString(),
                Path.of(folder.getRoot() + "/list/1.txt").toString()));
    }

    @Test
    public void whenTestMainFileMaskFindToFile() throws IOException {
        args[2] = "-t=mask";
        args[1] = "-n=*.txt";
        Find.main(args);
        List<String> rsl = Files.readAllLines(log.toPath());
        assertThat(rsl, containsInAnyOrder(
                        Path.of(folder.getRoot() + "/1.txt").toString(),
                        Path.of(folder.getRoot() + "/list/1.txt").toString(),
                        Path.of(folder.getRoot() + "/list/list.txt").toString(),
                        Path.of(folder.getRoot() + "/list.txt").toString(),
                        Path.of(folder.getRoot() + "/log.txt").toString()
        ));
    }

    @Test
    public void whenTestMainFileMaskFindToConsole() throws IOException {
        args[2] = "-t=mask";
        args[1] = "-n=*.txt";
        args[3] = "-o=console";
        Find.main(args);
        List<String> actual = Arrays.asList(print.toString().split(", "));
        actual.set(0, actual.get(0).substring(1));
        int indexLastActual = actual.size() - 1;
        int sizeString = actual.get(indexLastActual).length();
        actual.set(indexLastActual, actual.get(indexLastActual).substring(0, sizeString - 1));
        assertThat(actual, containsInAnyOrder(
                Path.of(folder.getRoot() + "/1.txt").toString(),
                        Path.of(folder.getRoot() + "/list/1.txt").toString(),
                        Path.of(folder.getRoot() + "/list/list.txt").toString(),
                        Path.of(folder.getRoot() + "/list.txt").toString(),
                        Path.of(folder.getRoot() + "/log.txt").toString()
        ));
    }

    @Test
    public void whenTestMainFileRegExFindToFile() throws IOException {
        args[2] = "-t=regex";
        args[1] = "-n=.i[w]*";
        Find.main(args);
        List<String> rsl = Files.readAllLines(log.toPath());
        assertThat(rsl, containsInAnyOrder(
                Path.of(folder.getRoot() + "/list/list.txt").toString(),
                        Path.of(folder.getRoot() + "/list.txt").toString()
        ));
    }

    @Test
    public void whenTestMainFileRefExFindToConsole() throws IOException {
        args[2] = "-t=regex";
        args[1] = "-n=.i[w]*";
        args[3] = "-o=console";
        Find.main(args);
        List<String> actual = Arrays.asList(print.toString().split(", "));
        actual.set(0, actual.get(0).substring(1));
        int indexLastActual = actual.size() - 1;
        int sizeString = actual.get(indexLastActual).length();
        actual.set(indexLastActual, actual.get(indexLastActual).substring(0, sizeString - 1));
        assertThat(actual, containsInAnyOrder(
                Path.of(folder.getRoot() + "/list/list.txt").toString(),
                       Path.of(folder.getRoot() + "/list.txt").toString()
        ));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenMainTestUnsupportedOperation() throws IOException {
        args[2] = "-t=value";
        Find.main(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMainTestIllegalKey() throws IOException {
        args[0] = "d=1";
        Find.main(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMainTestIllegalExpressionOnArgs() throws IOException {
        args[0] = "-d=1=2";
        Find.main(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMainTestNotAllKey() throws IOException {
        args[0] = "-t=name";
        Find.main(args);
    }

    @After
    public void setOldSystemOut() {
        System.setOut(old);
    }
}
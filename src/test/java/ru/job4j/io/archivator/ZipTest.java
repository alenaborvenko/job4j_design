package ru.job4j.io.archivator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.io.Search;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertFalse;

public class ZipTest {
    public File singleFile;
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() throws IOException {
        singleFile = folder.newFile("1.txt");
        folder.newFile("1.html");
        folder.newFolder("folder");
        folder.newFile("folder/2.txt");
    }

    @Test
    public void whenZipWithoutHTML() throws IOException {
        Predicate<Path> condition = s -> !s.toFile().getName().endsWith("html");
        Path root = folder.getRoot().toPath();
        Zip.packFiles(Search.search(root, condition), folder.newFile("zip.zip").toPath());
        List<Path> findZip = Search.search(root, s -> s.toFile().getName().equals("zip.zip"));
         assertFalse(findZip.isEmpty());
         assertFalse(findZip.contains(Path.of("1.html")));
    }

    @Test
    public void whenZipSingleFile() throws IOException {
        Path root = folder.getRoot().toPath();
        Zip.packSingleFile(singleFile, folder.newFile("zip.zip"));
        List<Path> findZip = Search.search(root, s -> s.toFile().getName().equals("zip.zip"));
        assertFalse(findZip.isEmpty());
        assertFalse(findZip.contains(Path.of("1.html")));
    }
}
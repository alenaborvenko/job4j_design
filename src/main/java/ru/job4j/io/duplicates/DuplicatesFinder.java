package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DuplicatesFinder {
    private List<FileProperty> duplicates = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        DuplicatesFinder df = new DuplicatesFinder();
        df.search(Path.of(args[0]));
        df.duplicates.forEach(System.out::println);
    }
    public void search(Path path) throws IOException {
        Files.walkFileTree(path, new DuplicatesVisitor(duplicates));
    }

    public List<FileProperty> getDuplicates() {
        return duplicates;
    }
}

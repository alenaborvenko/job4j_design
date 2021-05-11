package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Set<FileProperty> unique = new HashSet<>();
    private List<FileProperty> duplicates;

    public DuplicatesVisitor(List<FileProperty> duplicates) {
        this.duplicates = duplicates;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        FileProperty newFile = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (!unique.add(newFile)) {
            duplicates.add(newFile);
        }
        return FileVisitResult.CONTINUE;
    }
}

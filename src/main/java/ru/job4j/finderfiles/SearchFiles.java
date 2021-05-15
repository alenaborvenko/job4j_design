package ru.job4j.finderfiles;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles extends SimpleFileVisitor<Path> {
    private final List<Path> pathList;
    private final Predicate<Path> contidition;

    public SearchFiles(List<Path> pathList, Predicate<Path> contidition) {
        this.pathList = pathList;
        this.contidition = contidition;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (contidition.test(file)) {
            pathList.add(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }
}

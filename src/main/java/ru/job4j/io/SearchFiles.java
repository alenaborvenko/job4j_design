package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles extends SimpleFileVisitor<Path> {
    private Predicate<Path> condition;
    private List<Path> pathList;

    public SearchFiles(Predicate<Path> condition, List<Path> pathList) {
        this.condition = condition;
        this.pathList = pathList;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (condition.test(file)) {
            pathList.add(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }
}

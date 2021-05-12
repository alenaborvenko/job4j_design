package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    private static List<Path> pathList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Usage java -jar search.jar root_folder .file_extension");
        }
        Path root = Path.of(args[0]);
        Predicate<Path> condition = s -> s.toFile().getName().endsWith(args[1]);
        pathList = search(root, condition);
        pathList.forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        pathList.clear();
        SearchFiles searcher = new SearchFiles(condition, pathList);
        Files.walkFileTree(root, searcher);
        return pathList;
    }
}

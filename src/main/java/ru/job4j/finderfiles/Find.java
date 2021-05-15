package ru.job4j.finderfiles;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Find {

    public static void main(String[] args) throws IOException {
        if (args.length < 4) {
            throw new IllegalArgumentException("Usage java -jar find.jar -d=directory -n=name/mask/regEx"
                    + " -t=selectSearch -o=textFile/console");
        }
        ArgsName arg = ArgsName.of(args);
        Path root = Path.of(arg.get("d"));
        String selectionFind = arg.get("t");
        String nameFile = arg.get("n");
        Predicate<Path> condition = getPredicate(selectionFind, nameFile);
        List<Path> file = findFileByPredicate(root, condition);
        if (!"console".equals(arg.get("o"))) {
            try (PrintWriter pw = new PrintWriter(Path.of(root + "/" + arg.get("o")).toString())) {
                for (Path filePath : file) {
                    pw.println(filePath.toString());
                }
            }
        } else {
            System.out.print(file);
        }
    }

    private static Predicate<Path> getPredicate(String selectionFind, String nameFile) {
        Predicate<Path> condition;
        if ("name".equals(selectionFind)) {
            condition = s -> s.toFile().getName().equals(nameFile);
        } else if ("mask".equals(selectionFind)) {
            condition = s -> {
                String tmpName = nameFile;
                if (tmpName.startsWith("*")) {
                    tmpName = tmpName.substring(1);
                }
                return s.toFile().getName().endsWith(tmpName);
            };
        } else if ("regEx".equalsIgnoreCase(selectionFind)) {
            condition = s -> {
                Pattern pattern = Pattern.compile(nameFile);
                return pattern.matcher(s.toFile().getName()).find();
            };
        } else {
            throw new UnsupportedOperationException("Unknown selection find. Usage for key -t name/mask/regExp");
        }
        return condition;
    }

    public static List<Path> findFileByPredicate(Path root, Predicate<Path> condition) throws IOException {
        List<Path> rsl = new ArrayList<>();
        SearchFiles searcher = new SearchFiles(rsl, condition);
        Files.walkFileTree(root, searcher);
        return rsl;
    }
}

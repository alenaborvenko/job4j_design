package ru.job4j.io.archivator;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private static ArgsName parameters = new ArgsName();

    public static void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path currentPath : sources) {
                zip.putNextEntry(new ZipEntry(currentPath.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(currentPath.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        parameters = ArgsName.of(args);
        Path root = Path.of(parameters.get("d"));
        Path out = Path.of(parameters.get("o"));
        if (root == null || out == null) {
            throw new IllegalArgumentException("Usage key -d for directory and -o for output name file");
        }
        String exclude = parameters.get("e");
        try {
            List<Path> search = Search.search(root, s -> !s.toFile().getName().endsWith(exclude));
            packFiles(search, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

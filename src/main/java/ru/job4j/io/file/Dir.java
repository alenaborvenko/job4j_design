package ru.job4j.io.file;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("C:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.printf("size : %s%n", file.getTotalSpace());
        for (File subfile : file.listFiles()) {
            System.out.printf("%s %d bytes%n", subfile.getName(), subfile.length());
        }
    }
}
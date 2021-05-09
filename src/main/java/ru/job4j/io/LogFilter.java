package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> filter = new ArrayList<>();
        try (BufferedReader buffReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = buffReader.readLine()) != null) {
                String[] parseLine = line.split(" ");
                if ("404".equals(parseLine[parseLine.length - 2])) {
                    filter.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filter;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}

package ru.job4j.io;

import java.io.*;

public class Analizy {
    public static void unavailable(String source, String target) {
        try (BufferedReader buffReader = new BufferedReader(new FileReader(source));
             PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            String lineSource;
            String timeStart = null;
            while ((lineSource = buffReader.readLine()) != null) {
                if (lineSource.isEmpty()) {
                    continue;
                }
                String[] parseLine = lineSource.split(" ");
                if (parseLine.length != 2) {
                    throw new IllegalArgumentException("Pattern exception. Must be server_answer time");
                }
                if ("400".equals(parseLine[0]) || "500".equals(parseLine[0])) {
                    if (timeStart == null) {
                        timeStart = parseLine[1];
                    }
                } else if (timeStart != null && ("200".equals(parseLine[0]) || "300".equals(parseLine[0]))) {
                    printWriter.printf("%s;%s;%n", timeStart, parseLine[1]);
                    timeStart = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

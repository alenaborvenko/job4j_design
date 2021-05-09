package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        if (!path.isEmpty()) {
            try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
                String line;
                while ((line = read.readLine()) != null) {
                    if (line.startsWith("#") || line.isEmpty()) {
                        continue;
                    }
                    String[] parseLine = line.split("=");
                    if (parseLine.length != 2) {
                        throw new IllegalArgumentException();
                    }
                    values.put(parseLine[0], parseLine[1]);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String value(String key) {
        return values.get(key);
    }


    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}

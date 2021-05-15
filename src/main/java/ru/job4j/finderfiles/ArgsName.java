package ru.job4j.finderfiles;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> value = new HashMap<>();

    public static ArgsName of(String[] args) {
        ArgsName argsName = new ArgsName();
        argsName.parse(args);
        return argsName;
    }

    private void parse(String[] args) {
        for (String arg : args) {
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException("Key not starts with -. Usage -key=value");
            }
            String[] parse = arg.split("=");
            if (parse.length != 2) {
                throw new IllegalArgumentException("Error expressions. Usage -key=value");
            }
            value.putIfAbsent(parse[0].substring(1), parse[1]);
        }
    }

    public String get(String key) {
        if (!value.containsKey(key)) {
            throw new IllegalArgumentException("Key not found. Usage all key "
                    + "(for key -o if write in console write console)");
        }
        return value.get(key);
    }
}

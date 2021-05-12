package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(key + " not found");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String param : args) {
            if (!param.startsWith("-")) {
                throw new IllegalArgumentException("Key not starts with -. Usage -key=value");
            }
            String[] parse = param.split("=");
            if (parse.length != 2) {
                throw new IllegalArgumentException("Value not correct. Usage -key=value");
            }
            values.put(parse[0].substring(1), parse[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}

package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class MultiplicationTable {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream(".\\src\\main\\java\\ru\\job4j\\io\\out.txt")) {
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    out.write(Integer.toString(i).getBytes());
                    out.write(" * ".getBytes());
                    out.write(Integer.toString(j).getBytes());
                    out.write(" = ".getBytes());
                    out.write(Integer.toString(i * j).getBytes());
                    out.write(System.lineSeparator().getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

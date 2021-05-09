package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            int read;
            StringBuilder str = new StringBuilder();
            while ((read = in.read()) != -1) {
                str.append((char) read);
            }
            String[] allNumbers = str.toString().split(System.lineSeparator());
            for (String num : allNumbers) {
                System.out.println(Integer.parseInt(num) % 2 == 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

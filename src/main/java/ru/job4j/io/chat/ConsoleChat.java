package ru.job4j.io.chat;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static List<String> logGialog = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> answer = getAnswer(this.botAnswers);
        Scanner in = new Scanner(System.in);
        String question = in.nextLine();
        Random rand = new Random();
        boolean contin = true;
        while (!OUT.equals(question)) {
            if (STOP.equals(question) || !contin) {
                saveDialog(question);
                contin = false;
                question = in.nextLine();
                if (!CONTINUE.equals(question)) {
                    continue;
                } else {
                    contin = true;
                }
            }
            String botAnswer = answer.get(rand.nextInt(answer.size() - 1));
            System.out.println(botAnswer);
            saveDialog(question);
            saveDialog(botAnswer);
            question = in.nextLine();
        }
        saveDialogToFile(logGialog);
    }

    private void saveDialogToFile(List<String> logGialog) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"))))) {
            for (String line : logGialog) {
                pw.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveDialog(String question) {
        logGialog.add(question);
    }

    private List<String> getAnswer(String botAnswers) {
        List<String> answer = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            String line;
            while ((line = br.readLine()) != null) {
                answer.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/main/java/ru/job4j/io/chat/chat.txt",
                "./src/main/java/ru/job4j/io/chat/answer.txt");
        cc.run();
    }
}

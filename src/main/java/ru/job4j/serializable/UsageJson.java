package ru.job4j.serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UsageJson {

    public static void main(String[] args) {
        Student student = new Student(
                true, 20, "Pol", new Contact(1, "7987545"),
                "Math", "Computer science"
        );
        final Gson gson = new GsonBuilder().create();
        String studentToJson = gson.toJson(student);
        System.out.println(studentToJson);
        Student studentFromJson = gson.fromJson(studentToJson, Student.class);
        System.out.println(studentFromJson);
    }
}

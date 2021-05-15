package ru.job4j.serializable;

import org.json.JSONArray;
import org.json.JSONObject;

public class UsageJsonObject {
    public static void main(String[] args) {
        Student student = new Student(
                true, 20, "Pol", new Contact(1, "7987545"),
                "Math", "Computer science"
        );
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", student.isSex());
        jsonObject.put("age", student.getAge());
        jsonObject.put("name", student.getName());
        jsonObject.put("contact", new JSONObject(student.getContact()));
        jsonObject.put("course", new JSONArray(student.getCourse()));
        System.out.println(jsonObject);
        System.out.println(new JSONObject(student));
    }
}

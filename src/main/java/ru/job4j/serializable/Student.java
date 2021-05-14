package ru.job4j.serializable;

import java.util.Arrays;
import java.util.StringJoiner;

public class Student {
    private boolean sex;
    private int age;
    private String name;
    private Contact contact;
    private String[] course;

    public Student(boolean sex, int age, String name, Contact contact, String... course) {
        this.sex = sex;
        this.age = age;
        this.name = name;
        this.contact = contact;
        this.course = course;
    }

    public boolean isSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add("sex=" + sex)
                .add("age=" + age)
                .add("name='" + name + "'")
                .add("contact=" + contact)
                .add("course=" + Arrays.toString(course))
                .toString();
    }
}

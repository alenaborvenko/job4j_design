package ru.job4j.collection.map;

import java.util.Calendar;
import java.util.Objects;
import java.util.StringJoiner;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("children=" + children)
                .add("birthday=" + birthday.getTime())
                .toString();
    }
}

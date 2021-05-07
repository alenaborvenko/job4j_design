package ru.job4j.collection.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UserUsage {
    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        birthday.set(1991, Calendar.FEBRUARY, 22);
        Map<User, Object> map = new HashMap<>();
        Object value = new Object();
        User user1 = new User("Alan", 2, birthday);
        User user2 = new User("Alan", 2, birthday);
        map.put(user1, value);
        map.put(user2, value);
        map.entrySet().forEach(System.out::println);
    }
}

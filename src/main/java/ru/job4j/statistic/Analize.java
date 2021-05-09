package ru.job4j.statistic;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Analize {
    public static Info diff(List<User> previous, List<User> current) {
        Info diff = new Info();
        Map<Integer, String> mapUser = previous
                                        .stream()
                                        .collect(Collectors.toMap(User::getId, User::getName));
        for (User currentUser : current) {
            Integer userId = currentUser.getId();
            if (mapUser.containsKey(userId)) {
                if (!mapUser.get(userId).equals(currentUser.getName())) {
                    diff.changed++;
                }
                mapUser.remove(userId);
            } else {
                diff.added++;
            }
        }
        diff.deleted = mapUser.size();
        return diff;
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static class Info {
        private int added = 0;
        private int changed = 0;
        private int deleted = 0;

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }
}
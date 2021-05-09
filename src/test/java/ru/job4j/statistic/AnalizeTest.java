package ru.job4j.statistic;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static ru.job4j.statistic.Analize.User;
import static ru.job4j.statistic.Analize.Info;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AnalizeTest {

    @Test
    public void whenAddedDeletedChangedThenTest() {
        List<User> prevList = new ArrayList<>(List.of(
                new User(1, "1"),
                new User(2, "2"),
                new User(3, "3"),
                new User(4, "4"),
                new User(5, "5"),
                new User(6, "6"),
                new User(7, "7"),
                new User(8, "8")
        ));
        List<User> newList = new ArrayList<>(List.copyOf(prevList));
        User chaged1 = new User(newList.get(0).getId(), "new value");
        User chaged2 = new User(newList.get(3).getId(), "new value");
        User chaged3 = new User(newList.get(5).getId(), "new value");
        newList.set(0, chaged1);
        newList.set(3, chaged2);
        newList.set(5, chaged3);
        newList.add(new User(9, "new User1"));
        newList.add(new User(10, "new User2"));
        newList.remove(1);
        newList.remove(1);
        newList.remove(2);
        newList.remove(3);
        Info info = Analize.diff(prevList, newList);

        assertThat(info.getAdded(), is(2));
        assertThat(info.getChanged(), is(3));
        assertThat(info.getDeleted(), is(4));
    }

}
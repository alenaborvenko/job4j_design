package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class UserStoreTest {

    private final Store<User> userStore = new UserStore();

    @Before
    public void setUp() {
        userStore.add(new User("1"));
        userStore.add(new User("2"));
    }

    @Test
    public void whenFindBy() {
        assertThat(userStore.findById("1"), is(new User("1")));
    }

    @Test
    public void whenFindByNotFound() {
        assertThat(userStore.findById("4"), is(nullValue()));
    }

    @Test
    public void whenAddSameUser() {
        userStore.add(userStore.findById("1"));
        assertThat(userStore.size(), is(2));
    }

    @Test
    public void whenAddOtherUser() {
        userStore.add(new User("3"));
        assertThat(userStore.size(), is(3));
        assertThat(userStore.findById("1"), is(new User("1")));
        assertThat(userStore.findById("2"), is(new User("2")));
        assertThat(userStore.findById("3"), is(new User("3")));
    }

    @Test
    public void whenReplaceUser() {
        assertTrue(userStore.replace("2", new User("5")));
        assertThat(userStore.findById("2"), is(nullValue()));
        assertThat(userStore.findById("5"), is(new User("5")));
    }

    @Test
    public void whenReplaceUserNotFound() {
        assertFalse(userStore.replace("6", new User("5")));
        assertThat(userStore.findById("6"), is(nullValue()));
        assertThat(userStore.findById("5"), is(nullValue()));
    }

    @Test
    public void whenDeleteUserNotFound() {
        assertFalse(userStore.delete("6"));
    }

    @Test
    public void whenDeleteUser() {
        assertTrue(userStore.delete("2"));
        assertThat(userStore.size(), is(1));
        assertThat(userStore.findById("2"), is(nullValue()));
        assertThat(userStore.findById("1"), is(new User("1")));
    }
}
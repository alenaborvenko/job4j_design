package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class RoleStoreTest {

    private Store<Role> roleStore = new RoleStore();

    @Before
    public void setUp() {
        roleStore.add(new Role("1"));
        roleStore.add(new Role("2"));
    }

    @Test
    public void whenFindBy() {
        assertThat(roleStore.findById("1"), is(new Role("1")));
    }

    @Test
    public void whenFindByNotFound() {
        assertThat(roleStore.findById("4"), is(nullValue()));
    }

    @Test
    public void whenAddSameRole() {
        roleStore.add(roleStore.findById("1"));
        assertThat(roleStore.size(), is(2));
    }

    @Test
    public void whenAddOtherRole() {
        roleStore.add(new Role("3"));
        assertThat(roleStore.size(), is(3));
        assertThat(roleStore.findById("1"), is(new Role("1")));
        assertThat(roleStore.findById("2"), is(new Role("2")));
        assertThat(roleStore.findById("3"), is(new Role("3")));
    }

    @Test
    public void whenReplaceRole() {
        assertTrue(roleStore.replace("2", new Role("5")));
        assertThat(roleStore.findById("2"), is(nullValue()));
        assertThat(roleStore.findById("5"), is(new Role("5")));
    }

    @Test
    public void whenReplaceRoleNotFound() {
        assertFalse(roleStore.replace("6", new Role("5")));
        assertThat(roleStore.findById("6"), is(nullValue()));
        assertThat(roleStore.findById("5"), is(nullValue()));
    }

    @Test
    public void whenDeleteRoleNotFound() {
        assertFalse(roleStore.delete("6"));
    }

    @Test
    public void whenDeleteRole() {
        assertTrue(roleStore.delete("2"));
        assertThat(roleStore.size(), is(1));
        assertThat(roleStore.findById("2"), is(nullValue()));
        assertThat(roleStore.findById("1"), is(new Role("1")));
    }
}
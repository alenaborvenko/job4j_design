package ru.job4j.collection.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenNotContains() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertFalse(set.contains(2));
        assertFalse(set.add(1));
    }

    @Test
    public void whenIterator() {
        Set<String> set = new SimpleSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        Iterator<String> it = set.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is("a"));
        assertTrue(it.hasNext());
        assertThat(it.next(), is("b"));
        assertTrue(it.hasNext());
        assertThat(it.next(), is("c"));
        assertFalse(it.hasNext());
    }
}
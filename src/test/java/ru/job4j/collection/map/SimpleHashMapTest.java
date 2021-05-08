package ru.job4j.collection.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    private final SimpleHashMap<String, Integer> input = new SimpleHashMap<>();

    @Before
    public void setUp() {
        input.insert("first", 1);
        input.insert("second", 2);
        input.insert("third", 3);

    }

    @Test
    public void whenCreatedThenEmpty() {
        SimpleHashMap<String, SameClass> empty = new SimpleHashMap<>();
        assertTrue(empty.isEmpty());
    }

    @Test
    public void whenInsert() {
        SimpleHashMap<String, SameClass> insert = new SimpleHashMap<>();
        insert.insert("first", new SameClass("1sameclass", 0));
        assertTrue(insert.size() == 1);
        assertThat(insert.get("first"), is(new SameClass("1sameclass", 0)));
    }

    @Test
    public void whenFewInsert() {
        SimpleHashMap<String, SameClass> insert = new SimpleHashMap<>();
        insert.insert("first", new SameClass("1sameclass", 0));
        insert.insert("second", new SameClass("2sameclass", 1));
        assertTrue(insert.size() == 2);
        assertThat(insert.get("first"), is(new SameClass("1sameclass", 0)));
        assertThat(insert.get("second"), is(new SameClass("2sameclass", 1)));
    }

    @Test
    public void whenIsertSameObject() {
        SimpleHashMap<String, SameClass> insert = new SimpleHashMap<>();
        insert.insert("first", new SameClass("1sameclass", 0));
        assertFalse(insert.insert("first", new SameClass("1sameclass", 0)));
        assertTrue(insert.size() == 1);
    }

    @Test
    public void whenGetNotFoundElem() {
        assertThat(input.get("two"), is(nullValue()));
    }

    @Test
    public void whenGetNotFoundElemColliz() {
        assertThat(input.get("ten"), is(nullValue()));
    }

    @Test
    public void whenGetFoundElem() {
        assertThat(input.get("first"), is(1));
        assertThat(input.get("second"), is(2));
        assertThat(input.get("third"), is(3));
    }

    @Test
    public void whenResize() {
        input.insert("fourth",4);
        input.insert("fifth",5);
        assertThat(input.size(), is(5));
        assertThat(input.get("first"), is(1));
        assertThat(input.get("second"), is(2));
        assertThat(input.get("third"), is(3));
        assertThat(input.get("fourth"), is(4));
        assertThat(input.get("fifth"), is(5));
    }

    @Test
    public void whenDeleteNotFoundColliz() {
        assertFalse(input.delete("ten"));
    }

    @Test
    public void whenDeleteNotFoundElem() {
        assertFalse(input.delete("two"));
    }

    @Test
    public void whenDeleteWasSuccess() {
        assertTrue(input.delete("second"));
        assertThat(input.size(), is(2));
        assertThat(input.get("first"), is(1));
        assertThat(input.get("third"), is(3));
        assertThat(input.get("second"), is(nullValue()));

    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIterAndThenModified() {
        Iterator<Integer> iterator = input.iterator();
        assertTrue(iterator.hasNext());
        input.insert("two", 2);
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterNoElemThenNext() {
        SimpleHashMap<String, SameClass> empty = new SimpleHashMap<>();
        Iterator<SameClass> iterator = empty.iterator();
        iterator.next();
    }

    @Test
    public void whenIter() {
        Iterator<Integer> iterator = input.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
    }
}
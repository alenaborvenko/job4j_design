package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    SimpleArray<String> input = new SimpleArray<>(10);

    @Before
    public void setUp() {
        input.add("first");
        input.add("second");
        input.add("third");
        input.add("fourth");
        input.add("fifth");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNegativeCapacity() {
        new SimpleArray<Integer>(-1);
    }

    @Test
    public void whenCreateSimpleArray() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);
        assertThat(simpleArray.size(), is(0));
    }

    @Test
    public void whenAdd2Elemen() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        simpleArray.add(1);
        simpleArray.add(2);
        assertThat(simpleArray.size(), is(2));
        assertThat(simpleArray.get(0), is(1));
        assertThat(simpleArray.get(1), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetNoElement() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        assertThat(simpleArray.get(0), is(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenCreateEmptyAndAddElement() {
        SimpleArray<String> simpleArray = new SimpleArray<>(0);
        simpleArray.add("first");
        assertThat(simpleArray.size(), is(1));
        assertThat(simpleArray.get(0), is("first"));
    }

    @Test
    public void whenSetOn3Elem() {
        input.set(2, "new value");
        assertThat(input.get(2), is("new value"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetOn9ElemThenException() {
        input.set(9, "new value");
    }

    @Test
    public void whenRemove24Elem() {
        input.remove(1);
        input.remove(2);
        assertThat(input.get(0), is("first"));
        assertThat(input.get(1), is("third"));
        assertThat(input.get(2), is("fifth"));
        assertThat(input.size(), is(3));
    }

    @Test
    public void whenRemove1ElemFromArrayWith1Elem() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(1);
        simpleArray.remove(0);
        assertThat(simpleArray.size(), is(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveNoElem() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.remove(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveNoIndex() {
        input.remove(8);
    }

    @Test
    public void whenIter() {
        Iterator<?> it = input.iterator();
        assertThat(it.next(), is("first"));
        assertTrue(it.hasNext());
        assertThat(it.next(), is("second"));
        assertTrue(it.hasNext());
        assertThat(it.next(), is("third"));
        assertTrue(it.hasNext());
        assertThat(it.next(), is("fourth"));
        assertTrue(it.hasNext());
        assertThat(it.next(), is("fifth"));
        assertFalse(it.hasNext());
    }

    @Test
    public void whenEmptySimpleArrayHasNextThenFalse() {
        SimpleArray<Double> simpleArray = new SimpleArray<>(3);
        assertFalse(simpleArray.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptySimpleArrayNextThenException() {
        SimpleArray<Double> simpleArray = new SimpleArray<>(3);
        simpleArray.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorThenAdd() {
        SimpleArray<Double> simpleArray = new SimpleArray<>(3);
        Iterator<Double> it = simpleArray.iterator();
        simpleArray.add(4.5);
        it.next();
    }
}
package ru.job4j.it;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, Is.is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenRemoveif() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        ListUtils.removeIf(input, s -> s % 2 == 1);
        assertThat(input, Is.is(Arrays.asList(0, 2)));
    }

    @Test
    public void whenReplaceif() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        ListUtils.replaceIf(input, s -> s % 2 == 0, 4);
        assertThat(input, Is.is(Arrays.asList(4, 1, 4, 3)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        ListUtils.removeAll(input, Arrays.asList(0, 2));
        assertThat(input, Is.is(Arrays.asList(1, 3)));
    }
}
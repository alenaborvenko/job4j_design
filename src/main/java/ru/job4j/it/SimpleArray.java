package ru.job4j.it;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] data;
    private int size = 0;
    private int modCount = 0;

    public SimpleArray(int capacity) {
        this.data = new Object[capacity];
    }

    public SimpleArray() {
        this.data = new Object[]{};
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) data[index];
    }

    public void add(T model) {
        if (size == data.length) {
            data = grow();
        }
        data[size++] = model;
        modCount++;
    }

    private Object[] grow() {
        Object[] newData = new Object[2 * data.length + 1];
        System.arraycopy(data, 0, newData, 0, data.length);
        return newData;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<T> {
        private final int expectedModCount = modCount;
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            checkModified(expectedModCount);
            return (T) data[cursor++];
        }

        private void checkModified(int expectedModCount) {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}

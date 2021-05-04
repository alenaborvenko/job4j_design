package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> {
    private final Object[] data;
    private int size = 0;

    public SimpleArray(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity < 0");
        }
        this.data = new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean add(T model) {
        Objects.checkIndex(size, data.length);
        data[size++] = model;
        return true;
    }

    public boolean set(int index, T model) {
        Objects.checkIndex(index, size);
        data[index] = model;
        return true;
    }

    public boolean remove(int index) {
        Objects.checkIndex(index, size);
        if (size != 0) {
            size--;
            System.arraycopy(data, index + 1, data, index, size - index);
        }
        data[size] = null;
        return true;
    }


    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) data[index];
    }

    public Iterator<T> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<T> {
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
            return (T) data[cursor++];
        }
    }
}

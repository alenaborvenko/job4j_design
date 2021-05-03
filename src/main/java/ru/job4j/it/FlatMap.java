package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor;

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
        cursor = nextCursor(data.next());
    }

    private Iterator<T> nextCursor(Iterator<T> iterator) {
        while (!iterator.hasNext() && data.hasNext()) {
            iterator = data.next();
        }
        return iterator;
    }

    @Override
    public boolean hasNext() {
        return cursor.hasNext() || data.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        cursor = nextCursor(cursor);
        return cursor.next();
    }
}

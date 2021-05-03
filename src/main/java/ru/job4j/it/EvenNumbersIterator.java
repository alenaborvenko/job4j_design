package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] data;
    private int cursor = -1;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    private int nextCursor(int cursor) {
        for (int i = cursor + 1; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean hasNext() {
        return nextCursor(cursor) != -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        cursor = nextCursor(cursor);
        return data[cursor];
    }
}

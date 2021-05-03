package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;
    private int countElem;

    public MatrixIt(int[][] data) {
        this.data = data;
        countElem = countElem(data);
    }

    private int countElem(int[][] data) {
        int count = 0;
        for (int[] row : data) {
                count += row.length;
        }
        return count;
    }

    @Override
    public boolean hasNext() {
        return row + column < countElem;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (column == data[row].length) {
            column = 0;
            row++;
        }
        while (data[row].length == 0) {
            row++;
        }
        return data[row][column++];
    }
}

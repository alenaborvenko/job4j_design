package ru.job4j.collection.map;

import java.util.Objects;

public class SameClass {
    private String name;
    private int count;

    public SameClass(String name, int count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SameClass sameClass = (SameClass) o;
        return count == sameClass.count && Objects.equals(name, sameClass.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, count);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SameClass{");
        sb.append("name='").append(name).append('\'');
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }
}

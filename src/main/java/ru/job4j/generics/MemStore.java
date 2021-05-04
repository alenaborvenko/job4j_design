package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        if (findById(model.getId()) == null) {
            mem.add(model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        T find = findById(id);
        if (find == null) {
            return false;
        }
        mem.set(indexOf(find), model);
        return true;
    }

    private int indexOf(T byId) {
        return mem.indexOf(byId);
    }

    @Override
    public boolean delete(String id) {
        T find = findById(id);
        int index = indexOf(find);
        if (index == -1) {
            return false;
        }
        mem.remove(index);
        return true;
    }

    @Override
    public T findById(String id) {
        return mem.stream()
                .filter(s -> Objects.equals(s.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public int size() {
        return mem.size();
    }
}

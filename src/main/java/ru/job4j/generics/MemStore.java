package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        if (model != null && indexById(model.getId()) == -1) {
            mem.add(model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        int index = indexById(id);
        if (index == -1) {
            return false;
        }
        mem.set(index, model);
        return true;
    }

    private int indexById(String id) {
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean delete(String id) {
        int index = indexById(id);
        if (index == -1) {
            return false;
        }
        mem.remove(index);
        return true;
    }

    @Override
    public T findById(String id) {
        int index = indexById(id);
        return index != -1 ? mem.get(index) : null;
    }

    @Override
    public int size() {
        return mem.size();
    }
}

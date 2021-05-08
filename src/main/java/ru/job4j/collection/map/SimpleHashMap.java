package ru.job4j.collection.map;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<V> {

    private Mapping<K, V>[] source;
    private int modCount = 0;
    private int size = 0;
    private float lOADFACTOR = 0.75f;

    public SimpleHashMap() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean insert(K key, V value) {
        if (size == 0 || source.length * lOADFACTOR == size) {
            resize();
        }
        int hash = hash(key);
        Mapping<K, V> newElem = new Mapping<>(hash, key, value);
        int index = (source.length - 1) & hash;
        if (source[index] != null) {
            return false;
        }
        modCount++;
        size++;
        source[index] = newElem;
        return true;
    }

    public V get(K key) {
        Mapping<K, V> findElem = source[(source.length - 1) & hash(key)];
        if (findElem == null) {
            return null;
        }
        return key.equals(findElem.getKey()) ? findElem.getValue() : null;
    }

    public boolean delete(K key) {
        int index = (source.length - 1) & hash(key);
        if (source[index] == null) {
            return false;
        }
        if (source[index].getKey().equals(key)) {
            source[index] = null;
            size--;
            modCount++;
            return true;
        }
        return false;
    }

    final void resize() {
        Mapping<K, V>[] newSource = new Mapping[size == 0 ? 4 : source.length * 2];
        if (size != 0) {
            for (Mapping<K, V> elem : source) {
                if (elem != null) {
                    int newIndex = (newSource.length - 1) & elem.getHash();
                    newSource[newIndex] = elem;
                }
            }
        }
        source = newSource;
    }

    private static int hash(Object key) {
        int h = key.hashCode();
        return (key == null) ? 0 : (h) ^ (h >>> 16);
    }

    @Override
    public Iterator<V> iterator() {
        return new MapIter();
    }

    private static class Mapping<K, V> {
        final int hash;
        final K key;
        V value;

        public Mapping(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public int getHash() {
            return hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    private class MapIter implements Iterator<V> {
        private final Mapping<K, V>[] source = SimpleHashMap.this.source;
        private final int expectedModCount = modCount;
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            if (size == 0) {
                return false;
            }
            for (int i = cursor; i < source.length; i++) {
                if (source[i] != null) {
                    break;
                }
                cursor++;
            }
            return cursor <= size;
        }

        @Override
        public V next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            checkModified(expectedModCount);
            return source[cursor++].getValue();
        }

        private void checkModified(int expectedModCount) {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}

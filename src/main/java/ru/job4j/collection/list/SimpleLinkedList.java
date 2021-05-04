package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Класс для упрощеного связного списка
 * @param <E> - тип коллекции
 */
public class SimpleLinkedList<E> implements List<E> {
    private Node<E> last;
    private Node<E> first;
    private int size = 0;
    private int modCount = 0;

    public SimpleLinkedList() {
    }

    /**
     * Метод, добавляющий в конец списка заданный элемент
     * @param value - значение, которое необходимо добавить
     */
    @Override
    public void add(E value) {
        Node<E> l = last;
            Node<E> newNode = new Node<>(value, last, null);
            last = newNode;
            if (l == null) {
                first = newNode;
            } else {
                l.next = newNode;
            }
            size++;
            modCount++;
    }

    /**
     * Метод, возвращающий элемент коллекции по индексу.
     * @param index - индекс, эоемент которого надо получить. Индекс >= 0 и < size (размер коллекции)
     * @return - элемент
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> tmpLast = last;
        for (int i = size - 1; i > index; i--) {
            tmpLast = tmpLast.prev;
        }
        return tmpLast.item;
    }

    /**
     * Метод, позволяющий получить итератор
     * @return - итератор для этой коллекции
     */
    @Override
    public Iterator<E> iterator() {
        return new IterList();
    }

    private static class Node<E> {
        private final E item;
        private final Node<E> prev;
        private Node<E> next;

        Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private class IterList implements Iterator<E> {
        private final int expectedModCount = modCount;
        private int cursor = 0;

        /**
         * Метод, позволяющий узнать, если в коллекции еще элементы
         * @return - true - элементы есть. false - достигли конца коллекции и элементов нет
         */
        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        /**
         * Метод, возвращающий следующий элемент коллеции. Если метод вызывается, когда нет больше элементов,
         * то NoSuchElementException. Если коллекции изменилась во время обхода коллекции, то
         * ConcurrentModificationException()
         * @return - следующий элемент
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            checkModifier();
            Node<E> tmp = first;
            for (int i = 0; i < cursor; i++) {
                tmp = tmp.next;
            }
            cursor++;
            return tmp.item;
        }

        private void checkModifier() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}

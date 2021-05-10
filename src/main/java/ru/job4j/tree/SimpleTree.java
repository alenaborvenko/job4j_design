package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> parentNode = findBy(parent);
        if (parentNode.isEmpty()) {
            return false;
        }
        Optional<Node<E>> findChild = findBy(child);
        if (findChild.isPresent()) {
            return false;
        }
        List<Node<E>> children = parentNode.get().children;
        children.add(new Node<>(child));
        return true;
    }

    @Override
    public boolean isBinary() {
        return findByPredicate(s -> s.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(s -> s.value.equals(value));
    }
}

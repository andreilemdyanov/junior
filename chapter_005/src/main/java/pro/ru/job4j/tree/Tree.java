package pro.ru.job4j.tree;

import pro.ru.job4j.list.SimpleQueue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Class Tree.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 16.07.2017
 */

/**
 * Tree.
 *
 * @param <E> type.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Корень дерева.
     */
    private Node<E> root;
    /**
     * Количество родителей.
     */
    private int size;

    /**
     * Геттер size.
     *
     * @return size.
     */
    public int getSize() {
        return size;
    }

    /**
     * Очередь для сортировки по порядку.
     */
    private SimpleQueue<Node<E>> queue = new SimpleQueue<>();

    /**
     * Конструктор дерева.
     *
     * @param root корень.
     */
    public Tree(E root) {
        this.root = new Node<>(root);
        this.size = 1;
    }

    /**
     * Узел дерева.
     *
     * @param <E> тип.
     */
    private static class Node<E> {
        /**
         * Список детей.
         */
        private List<Node<E>> children;
        /**
         * Значение узла.
         */
        private E value;
        /**
         * Ссылки на левую и правую ветвь.
         */
        private Node<E> left, right;
        /**
         * Счетчик ветвей.
         */
        private int count = 0;

        /**
         * Конструктор узла.
         *
         * @param value значение.
         */
        Node(E value) {
            this.value = value;
        }

        /**
         * Геттер количества ветвей у нода.
         *
         * @return число.
         */
        public int getCount() {
            return count;
        }

        /**
         * Сеттер количества ветвей у нода.
         *
         * @param count число.
         */
        public void setCount(int count) {
            this.count = count;
        }

        /**
         * Переопределение toString.
         *
         * @return строка.
         */
        @Override
        public String toString() {
            return "Node{"
                    + "value="
                    + value
                    + '}';
        }
    }

    /**
     * Добавить родителя.
     *
     * @param parent родитель.
     */
    public void addParent(E parent) {
        Node<E> first = root, second = null;
        while (first != null) {
            int cmp = parent.compareTo(first.value);
            second = first;
            if (cmp <= 0) {
                first = first.left;
            } else {
                first = first.right;
            }

        }
        Node<E> newNode = new Node<E>(parent);
        if (parent.compareTo(second.value) <= 0) {
            second.left = newNode;
        } else {
            second.right = newNode;
        }
        second.setCount(second.getCount() + 1);
        size++;
    }


    /**
     * Реализация добавления детей родителям.
     *
     * @param parent parent.
     * @param child  child.
     * @return добавлен?
     */
    @Override
    public boolean add(E parent, E child) {
        Node<E> first = root;
        boolean flag = false;
        while (first != null && !flag) {
            int cmp = parent.compareTo(first.value);
            if (cmp == 0) {
                if (first.children == null) {
                    first.children = new ArrayList<>();
                }
                first.children.add(new Node<E>(child));
                flag = true;
            } else if (cmp < 0) {
                first = first.left;
            } else {
                first = first.right;
            }
        }
        return flag;
    }

    /**
     * Итератор.
     *
     * @return итератор.
     */
    @Override
    public Iterator iterator() {
        this.contInOrder();
        return new Iterator() {
            @Override
            public boolean hasNext() {
                return queue.size() > 0;
            }

            @Override
            public Object next() {
                return queue.pop();
            }
        };
    }

    /**
     * Сортировка узлов по возрастанию и помещение их в очередь.
     */
    public void contInOrder() {
        Stack<Node<E>> stack = new Stack<>();
        Node<E> local = root;
        while (local != null || !stack.empty()) {
            if (!stack.empty()) {
                Node<E> a = stack.pop();
                queue.push(a);
                local = a;
                if (local.right != null) {
                    local = local.right;
                } else {
                    local = null;
                }
            }
            while (local != null) {
                stack.push(local);
                local = local.left;
            }
        }
    }

    /**
     * Метод определяет бинарность дерева.
     *
     * @return бинарно?
     */
    public boolean isBinary() {
        Iterator it = this.iterator();
        boolean flag = true;
        while (it.hasNext()) {
            if (!(((Node) it.next()).count <= 2)) {
                flag = false;
            }
        }
        return flag;
    }
}

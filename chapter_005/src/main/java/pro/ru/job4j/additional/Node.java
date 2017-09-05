package pro.ru.job4j.additional;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Node.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 31.08.2017
 */
public class Node {
    /**
     * Cсылка на следующий нод.
     */
    private Node next;
    /**
     * Значение нода.
     */
    private int value;

    /**
     * Конструктор.
     *
     * @param value значение.
     */
    public Node(int value) {
        this.value = value;
    }

    /**
     * Метод меняет последовательность ссылок на обратную.
     */
    public void reverse() {
        List<Node> list = new ArrayList<>();
        Node current = this;
        while (current != null) {
            list.add(current);
            current = current.next;
        }
        for (int i = list.size() - 1; i > 0; i--) {
            list.get(i).next = list.get(i - 1);
        }
        list.get(0).next = null;
    }

    @Override
    public String toString() {
        return "Node{"
                + " value="
                + value
                + '}';
    }

    /**
     * Точка входа.
     *
     * @param args массив строк.
     */
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node2.next = node3;
        System.out.println(node1.value + " " + node1.next.value + " "
                + node1.next.next.value + " " + node1.next.next.next);
        node1.reverse();
        System.out.println(node3.value + " " + node3.next.value + " "
                + node3.next.next.value + " " + node3.next.next.next);
    }
}


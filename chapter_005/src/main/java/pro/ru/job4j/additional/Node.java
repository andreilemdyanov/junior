package pro.ru.job4j.additional;

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
        this.next.next.next = this.next;
        this.next.next = this;
        this.next = null;

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
        System.out.println(node1.value + " " + node1.next.value + " " + node1.next.next.value);
        node1.reverse();
        System.out.println(node3.value + " " + node3.next.value + " " + node3.next.next.value);
    }
}


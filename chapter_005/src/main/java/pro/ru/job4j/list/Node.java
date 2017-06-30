package pro.ru.job4j.list;

/**
 * Class Node.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 30.06.2017
 */

/**
 * Node.
 *
 * @param <T> type.
 */
public class Node<T> {
    /**
     * Значение.
     */
    private T value;
    /**
     * Ссылка на следующий элемент.
     */
    private Node<T> next;

    /**
     * Конструктор.
     *
     * @param value значение.
     */
    Node(T value) {
        this.value = value;
    }

    /**
     * Есть цикл?
     *
     * @return да/нет
     */
    public boolean hasCycle() {
        Integer temp = this.hashCode();
        Node o = this;
        while (o.hashCode() != temp) {
            o = o.next;
            if (o == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Точка входа.
     * @param args массив строк.
     */
    public static void main(String[] args) {

        Node first = new Node<>(1);
        Node two = new Node<>(2);
        Node third = new Node<>(3);
        Node four = new Node<>(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        System.out.println(first.hasCycle());

    }
}

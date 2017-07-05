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
     * Сеттер.
     *
     * @param next элемент.
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

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
        Node twoStep = this.next;
        Node oneStep = this.next;
        if (oneStep == null || twoStep.next == null) {
            return false;
        }
        while (oneStep.hashCode() != temp) {
            oneStep = oneStep.next;
            twoStep = twoStep.next.next;
            if (oneStep == null || twoStep == null) {
                return false;
            }
            if (oneStep.hashCode() == twoStep.hashCode()) {
                break;
            }
        }
        return true;
    }

}

package pro.ru.job4j.additional;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class Sort.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 29.08.2017
 */
public class Sort {
    /**
     * Коллекция заявок.
     */
    private Collection<Order> orders;
    /**
     * Компаратор по возрастанию.
     */
    private static Comparator<Float> forward = new Comparator<Float>() {
        @Override
        public int compare(Float o1, Float o2) {
            return o1.compareTo(o2);
        }
    };
    /**
     * Компаратор по убыванию.
     */
    private static Comparator<Float> back = new Comparator<Float>() {
        @Override
        public int compare(Float o1, Float o2) {
            return o2.compareTo(o1);
        }
    };

    /**
     * Конструктор.
     *
     * @param orders коллекция заявок.
     */
    public Sort(Collection<Order> orders) {
        this.orders = orders;
    }

    /**
     * Метод для агрегации заявок.
     */
    public void calculate() {
        Map<Float, Order> sell = new TreeMap<>(forward);
        Map<Float, Order> buy = new TreeMap<>(back);
        for (Order order : orders) {
            this.add(order.getOperation() == Order.Type.BUY ? buy : sell, order);
        }
        this.show(sell, buy);
    }

    /**
     * Добавление заявки.
     *
     * @param map   книга заявок.
     * @param order заявка.
     */
    public void add(Map<Float, Order> map, Order order) {
        Order find = map.get(order.getPrice());
        if (find != null) {
            map.put(find.getPrice(), new Order(find.getBook(), find.getOperation(), find.getPrice(), find.getVolume() + order.getVolume(), find.getId()));
        } else {
            map.put(order.getPrice(), order);
        }
    }

    /**
     * Сокращение и выведение заявок.
     *
     * @param sell продажа.
     * @param buy  покупка.
     */
    public void show(Map<Float, Order> sell, Map<Float, Order> buy) {
        for (Iterator<Map.Entry<Float, Order>> s = sell.entrySet().iterator(), b = buy.entrySet().iterator(); s.hasNext() && b.hasNext();) {
            Map.Entry<Float, Order> entryS = s.next();
            Map.Entry<Float, Order> entryB = b.next();
            while (entryB.getKey() >= entryS.getKey()) {
                if (entryS.getValue().getVolume() > entryB.getValue().getVolume()) {
                    entryS.getValue().setVolume(entryS.getValue().getVolume() - entryB.getValue().getVolume());
                    b.remove();
                    entryB = b.next();
                } else if (entryS.getValue().getVolume() < entryB.getValue().getVolume()) {
                    entryB.getValue().setVolume(entryB.getValue().getVolume() - entryS.getValue().getVolume());
                    s.remove();
                    entryS = s.next();
                } else {
                    b.remove();
                    s.remove();
                    entryB = b.next();
                    entryS = s.next();
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        for (Iterator<Map.Entry<Float, Order>> s = sell.entrySet().iterator(), b = buy.entrySet().iterator(); s.hasNext() || b.hasNext();) {
            if (s.hasNext() && !b.hasNext()) {
                Map.Entry<Float, Order> entryS = s.next();
                builder.append(String.format("%12s\t- %7s@%5s\n", "-------", entryS.getValue().getVolume(), entryS.getKey()));
            } else if (!s.hasNext() && b.hasNext()) {
                Map.Entry<Float, Order> entryB = b.next();
                builder.append(String.format("%7s@%5s\t- %12s\n", entryB.getValue().getVolume(), entryB.getKey(), "-------"));

            } else if (s.hasNext() && b.hasNext()) {
                Map.Entry<Float, Order> entryS = s.next();
                Map.Entry<Float, Order> entryB = b.next();
                builder.append(String.format("%7s@%5s\t- %7s@%5s\n", entryB.getValue().getVolume(), entryB.getKey(), entryS.getValue().getVolume(), entryS.getKey()));

            }
        }
        System.out.printf("%9s\t %14s\n%13s\t %14s\n", "BID", "ASK", "Volume@Price", "Volume@Price");
        System.out.println(builder);
    }
}


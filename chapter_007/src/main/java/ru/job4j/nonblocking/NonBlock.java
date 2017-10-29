package ru.job4j.nonblocking;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Class NonBlock.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 29.10.2017
 */
public class NonBlock {

    private final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    public void add(User user) {
        map.put(user.getName(), user.getVersion());
    }

    public void delete(String name) {
        map.remove(name);
    }

    public void update(User user) {
        user.setVersion(map.get(user.getName()));
        map.computeIfPresent(user.getName(), (key, oldVal) -> {
            if (map.get(user.getName()) != user.getVersion()) {
                throw new OplimisticException();
            }
            return oldVal + 1;
        });
    }

    @Override
    public String toString() {
        return "NonBlock{"
                + "map="
                + map
                + '}';
    }

    private class OplimisticException extends RuntimeException {

    }

    public static void main(String[] args) {
        NonBlock nb = new NonBlock();
        nb.add(new User("Паша"));
        nb.add(new User("Миша"));
        nb.add(new User("Маша"));
        System.out.println(nb);
        nb.delete("Миша");
        System.out.println(nb);

        nb.update(new User("Маша"));
        nb.update(new User("Паша"));
        System.out.println(nb);

        nb.update(new User("Маша"));
        System.out.println(nb);

        Thread a = new Thread(() -> {
            nb.update(new User("Маша"));
        });
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nb);

        Thread b = new Thread(() -> {
            nb.update(new User("Маша"));
        });
        b.start();
        try {
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nb);

        Thread c = new Thread(() -> {
            nb.update(new User("Маша"));
        });
        c.start();
        try {
            c.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nb);
    }
}

package pro.ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Class IteratorInteger.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class IteratorInteger implements IteratorIter {


    private Iterator[] ar;

    private int position = 0;

    public IteratorInteger(Iterator[] ar) {
        this.ar = ar;
    }

    public void check() {
        if (!ar[position].hasNext()) {
            position++;
        }
    }

    @Override
    public Object next() {
//         int result = 0;
        check();
//        while (ar[position].hasNext()) {
//            result = (Integer) ar[position].next();
//        }
        return ar[position].next();
    }

    @Override
    public boolean hasNext() {
        if (position == ar.length) {
            return false;
        }
        check();
        if (position == ar.length) {
            return false;
        }
        return ar[position].hasNext();
    }

    @Override
    public Iterator convert(Iterator it) {

        return null;
    }


    public static void main(String[] args) {
        Iterator<Integer> it1 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7)).iterator();
        Iterator<Integer> it2 = new ArrayList<Integer>(Arrays.asList(11, 22, 33, 44, 55, 66, 77)).iterator();
        Iterator<Integer> it3 = new ArrayList<Integer>(Arrays.asList(10, 20, 30, 40, 50, 60, 70)).iterator();
        Iterator[] mas = new Iterator[]{it1, it2, it3};
        IteratorInteger iter = new IteratorInteger(mas);
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        System.out.println(iter.hasNext());
        System.out.println(iter.hasNext());

    }
}

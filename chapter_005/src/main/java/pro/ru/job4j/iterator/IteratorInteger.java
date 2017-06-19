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

    private Iterator<Integer> current;
//    private Iterator<Integer> result;

    private Iterator<Iterator<Integer>> ar;


    public IteratorInteger(Iterator<Iterator<Integer>> ar) {
        this.ar = ar;
        this.current = ar.next();
    }

    @Override
    public Object next() {
        if (current.hasNext()) {
            return current.next();
        } else if (ar.hasNext()) {
            current = ar.next();
            return current.next();
        }
        return current.next();
    }

    @Override
    public boolean hasNext() {

        return current.hasNext() || ar.hasNext();
    }


    @Override
    public Iterator convert(Iterator it) {
//        List<Integer> list = new ArrayList<>();
//        IteratorInteger t = new IteratorInteger(it);
//        while (t.hasNext()) {
//            Iterator current = (Iterator) t.next();
//            while (current.hasNext()) {
//                list.add((Integer) (current.next()));
//            }
//        }
//        result = new ArrayList<Integer>(list).iterator();
//        while (t.hasNext()) {
//            t.next();
//        }
        return this;
    }


    public static void main(String[] args) {
        Iterator<Integer> it1 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7)).iterator();
        Iterator<Integer> it2 = new ArrayList<Integer>(Arrays.asList(11, 22, 33, 44, 55, 66, 77)).iterator();
        Iterator<Integer> it3 = new ArrayList<Integer>(Arrays.asList(10, 20, 30, 40, 50, 60, 70)).iterator();
        Iterator<Iterator<Integer>> it = Arrays.asList(it1, it2, it3).iterator();
        IteratorInteger a = new IteratorInteger(it);
        while (a.hasNext()) {
            System.out.println(a.next());
        }
        System.out.println(a.hasNext());

    }
}

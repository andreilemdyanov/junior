package pro.ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Class IteratorInteger.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class IteratorInteger implements IteratorIter {

    private Iterator<Integer> result;

    private Iterator<Iterator<Integer>> ar;

    private int position = 0;

    public IteratorInteger(Iterator<Iterator<Integer>> ar) {
        this.ar = ar;
    }

    @Override
    public Object next() {
        return ar.next();
    }

    @Override
    public boolean hasNext() {

        return ar.hasNext();
    }


    @Override
    public Iterator convert(Iterator it) {
        List<Integer> list = new ArrayList<>();
        IteratorInteger t = new IteratorInteger(it);
        while(t.hasNext()) {
            Iterator current =(Iterator) t.next();
            while(current.hasNext()) {
                list.add((Integer)(current.next()));
            }
        }
        result = new ArrayList<Integer>(list).iterator();
        return result;
    }



    public static void main(String[] args) {
        Iterator<Integer> it1 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7)).iterator();
        Iterator<Integer> it2 = new ArrayList<Integer>(Arrays.asList(11, 22, 33, 44, 55, 66, 77)).iterator();
        Iterator<Integer> it3 = new ArrayList<Integer>(Arrays.asList(10, 20, 30, 40, 50, 60, 70)).iterator();
        Iterator<Iterator<Integer>> it = Arrays.asList(it1, it2, it3).iterator();
        IteratorInteger a = new IteratorInteger(it);
        a.convert(it);
        while(a.result.hasNext()) {
            System.out.println(a.result.next());
        }


    }
}

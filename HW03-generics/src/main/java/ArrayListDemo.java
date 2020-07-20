import ru.otus.generics.DIYArrayList;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args) {

        List<String> list = new DIYArrayList();
        for (int i = 0; i < 20; i++) {
            list.add("str" + i);
        }
//        System.out.println("size: " + list.size());
        Collections.addAll(list, "6", "7");
//        System.out.println("size: " + list.size());

//        Collections.static <T> void copy(List<? super T> dest, List<? extends T> src)
//        Collections.static <T> void sort(List<T> list, Comparator<? super T> c)
        List<String> listSource = new DIYArrayList();
        for (int i = 0; i < 11; i++) {
            listSource.add("str" + (i + 21));
        }
        //Collections.copy(list,listSource);

        Iterator<String> iterator = list.listIterator();
        for(int i = 0; i < list.size(); i++) {
            System.out.println(iterator.next());
        }
    }
}

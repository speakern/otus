import ru.otus.io.DIYArrayList;
import ru.otus.io.entries.Cat;
import ru.otus.io.entries.HomeCat;
import ru.otus.io.entries.WildCat;

import java.util.*;

public class ArrayListDemo {
    public static void main(String[] args) {

        List<String> list = new DIYArrayList();
        fillIn(list, 20, 0);

        List<String> listArray = new ArrayList();
        fillIn(listArray, 20, 0);

        Collections.addAll(list, "6", "7");
        Collections.addAll(listArray, "6", "7");

        List<String> listSource = new DIYArrayList();
        fillIn(listSource, 10, 30);

        List<String> listSourceArray = new ArrayList();
        fillIn(listSourceArray, 10, 30);

        Collections.copy(list, listSource);
        Collections.copy(listArray, listSourceArray);

        Collections.sort(list, NAME);
        Collections.sort(listArray, NAME);

        System.out.println(list.size());
        System.out.println(listArray.size());

        print(listArray);

//        List<Cat> list = new DIYArrayList();
//        fillInCat(list, 20, 0);
//
//        List<Cat> listArray = new ArrayList();
//        fillInCat(listArray, 20, 0);
//
//        Collections.addAll(list, new HomeCat("Пушистик"), new HomeCat("Пушистик2"));
//        Collections.addAll(listArray,  new HomeCat("Пушистик"), new HomeCat("Пушистик2"));
//
//        List<Cat> listSource = new DIYArrayList();
//        fillInCat(listSource, 10, 30);
//
//        List<Cat> listSourceArray = new ArrayList();
//        fillInCat(listSourceArray, 10, 30);
//
//        Collections.copy(list, listSource);
//        Collections.copy(listArray, listSourceArray);
//
//        Collections.sort(list, CAT);
//        Collections.sort(listArray, CAT);
//
//        print(list);
//        System.out.println("-----------------------------");
//        print(listArray);

    }

    public static Comparator<String> NAME = Comparator.comparingInt(Integer::valueOf);

//    public static Comparator<Cat> CAT = new Comparator<Cat>() {
//        @Override
//        public int compare(Cat o1, Cat o2) {
//            return o1.getName().compareTo(o2.getName());
//        }
//    };

    private static void fillIn(List<String> list, int size, int shift) {
        for (int i = 0; i < size; i++) {
            list.add(String.valueOf(i + shift));
        }
    }

    private static void fillInCat(List<Cat> list, int size, int shift) {
        for (int i = 0; i < size; i++) {
            list.add(new HomeCat("Барсик" + (i + shift)));
            list.add(new WildCat("Барсик" + (i + shift)));
        }
    }

    private static <T> void print(List<T> list) {
        Iterator<T> iterator = list.listIterator();
        for(int i = 0; i < list.size(); i++) {
            System.out.println(iterator.next());
        }
    }
}

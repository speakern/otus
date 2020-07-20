import ru.otus.generics.DIYArrayList;

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

        print(listArray);
    }

    public static Comparator<String> NAME = Comparator.comparingInt(Integer::valueOf);


    private static void fillIn(List<String> list, int size, int shift) {
        for (int i = 0; i < size; i++) {
            list.add(String.valueOf(i + shift));
        }
    }

    private static void print(List<String> list) {
        Iterator<String> iterator = list.listIterator();
        for(int i = 0; i < list.size(); i++) {
            System.out.println(iterator.next());
        }
    }
}

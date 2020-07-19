import ru.otus.generics.DIYArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {
//        DIYArrayList<Integer> list = new DIYArrayList();
//        list.add(2);
        //list.addAll()
//        List<String> listA = new ArrayList();


        DIYArrayList<String> list = new DIYArrayList();
        list.add("1");
        list.add("5");
        list.add("4");
        list.add("3");
        list.add("2");

        System.out.println("list.sizeArray: " + list.sizeArray + " list.size: " + list.size);
        list.add("1");
        System.out.println("list.sizeArray: " + list.sizeArray + " list.size: " + list.size);

//        Set<String> set = new HashSet<>();
//        set.add("7");
//        set.add("6");
//        list.addAll(set);

 //       list.forEach(System.out::println);

    }
}

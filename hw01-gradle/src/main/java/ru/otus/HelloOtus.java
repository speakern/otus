package ru.otus;

import com.google.common.collect.Lists;

public class HelloOtus {
    public static void main(String... args) {
        String [] strings =  {"2", "3", "4"};
        System.out.println(Lists.asList("1", strings));
    }
}

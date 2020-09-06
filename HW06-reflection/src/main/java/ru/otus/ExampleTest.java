package ru.otus;

import ru.otus.tester.annotations.After;
import ru.otus.tester.annotations.Before;
import ru.otus.tester.annotations.Test;

public class ExampleTest {

    @Before
    public void beforeEachTest1(){
        System.out.println("метод before1");
    }

    @Before
    public void beforeEachTest2(){
        System.out.println("метод before2");
    }

    @After
    public void afterEachTest1(){
        System.out.println("метод after1");
    }

    @After
    public void afterEachTest2(){
        System.out.println("метод after2");
    }

    @Test
    public void test1(){
        System.out.println("Тестирование1");
        throw new ArrayIndexOutOfBoundsException();
    }

    @Test
    public void test2(){
        System.out.println("Тестирование2");
    }


}

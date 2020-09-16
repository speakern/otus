package ru.otus.tester;

import ru.otus.tester.annotations.After;
import ru.otus.tester.annotations.Before;
import ru.otus.tester.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MyTester<T> {
    private T testClass;
    private Method methodTest;

    private static List<Method> methodsBefore;
    private static List<Method> methodsAfter;
    private static int countSuccess;
    private static int countError;
    private final static String SEPARATOR = "-------------------------------------------";

    public static <T> void startTest(Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Method[] methods = clazz.getDeclaredMethods();

        methodsBefore = new ArrayList<>();
        methodsAfter = new ArrayList<>();
        countError = 0;
        countSuccess = 0;
        List<Method> methodsTest = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Before.class)) {
                methodsBefore.add(method);
            }
            if (method.isAnnotationPresent(After.class)) {
                methodsAfter.add(method);
            }
            if (!method.isAnnotationPresent(After.class) && !method.isAnnotationPresent(After.class)
                    && method.isAnnotationPresent(Test.class)) {
                methodsTest.add(method);
            }
        }

        for (Method method : methodsTest) {
            System.out.println("Тестируем метод: " + method.toString());
            MyTester myTester = new MyTester(clazz.getDeclaredConstructor().newInstance(), method);
            myTester.run();
            System.out.println(SEPARATOR);
        }
        System.out.println("Всего тестов: " + (countSuccess + countError));
        System.out.println("Количество пройденных тестов: " + countSuccess);
        System.out.println("Количество непрошедших тестов: " + countError);
    }

    public MyTester(T testClass, Method methodTest) {
        this.testClass = testClass;
        this.methodTest = methodTest;
    }

    public void run() {
        try {
            for (Method method : methodsBefore) {
                method.invoke(testClass);
            }
            methodTest.invoke(testClass);
            System.out.println("Тест пройден");
            countSuccess++;
        } catch (Exception e) {
            System.out.println("Тест не пройден");
            countError++;
            //e.printStackTrace();
        }

        try {
            for (Method method : methodsAfter) {
                method.invoke(testClass);
            }
        } catch (Exception e){
            System.out.println("Ошибка в методе after");
        }
    }
}

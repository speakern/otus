package ru.otus.appcontainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.Method;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private static final Logger logger = LoggerFactory.getLogger(AppComponentsContainerImpl.class);
    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        // You code here...

        Method[] methodsAll = configClass.getDeclaredMethods();

        Map<String, Method> methods = new TreeMap<>();

        Arrays.stream(methodsAll).forEach(
                method -> {
                    System.out.println(method.getName());
                    AppComponent annotation = method.getDeclaredAnnotation(AppComponent.class);
                    if (annotation != null) {
                        System.out.println(annotation.toString() + "  " + annotation.name() + "   " + annotation.order());
                    }
                    //cells = new TreeMap<>(Comparator.comparingInt(BankNote::getValue).reversed());
//                    for (int i = 0; i < annotations.length; i++) {
//                        System.out.println(annotations[i].toString());
//                    }
                });

    }

    private Comparator<Method> getComparator(){
        return new Comparator<Method>() {
            @Override
            public int compare(Method m1, Method m2) {
                AppComponent annotationM1 = m1.getDeclaredAnnotation(AppComponent.class);
                AppComponent annotationM2 = m2.getDeclaredAnnotation(AppComponent.class);

                return 0;
            }
        };
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        return null;
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return null;
    }
}

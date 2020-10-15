package ru.otus.appcontainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private static final Logger logger = LoggerFactory.getLogger(AppComponentsContainerImpl.class);
    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) throws Exception {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) throws Exception {
        checkConfigClass(configClass);
        // You code here...

        Constructor<?> constructor = configClass.getConstructor();
        Object object = constructor.newInstance();

        Method[] methodsAll = configClass.getDeclaredMethods();
        Set<Method> methods = new TreeSet<>(getMethodComparator());

//        for (Method method : methodsAll) {
//            System.out.println(method.getName());
//            System.out.println(((Class) method.getGenericReturnType()).getSimpleName());
//            Type[] types = method.getGenericParameterTypes();
//
//            for (int i = 0; i < types.length; i++) {
//                System.out.println(((Class) types[i]).getSimpleName());
//            }
//
//            AppComponent annotation = method.getDeclaredAnnotation(AppComponent.class);
//            if (annotation != null) {
//                System.out.println(annotation.toString() + "  " + annotation.name() + "   " + annotation.order());
//            }
//        }
            methods.addAll(Arrays.asList(methodsAll));

        for (Method method : methods) {
            System.out.println(method.getName());
            System.out.println(((Class) method.getGenericReturnType()).getSimpleName());
        }

    }

    private Comparator<Method> getMethodComparator(){
        return new Comparator<Method>() {
            @Override
            public int compare(Method m1, Method m2) {
                AppComponent annotationM1 = m1.getDeclaredAnnotation(AppComponent.class);
                AppComponent annotationM2 = m2.getDeclaredAnnotation(AppComponent.class);
                if ((annotationM1 != null) && (annotationM2 != null)) {
                    return Integer.compare(annotationM1.order(), annotationM2.order());
                }
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

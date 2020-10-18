package ru.otus.appcontainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private static final Logger logger = LoggerFactory.getLogger(AppComponentsContainerImpl.class);
    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByNameClass = new HashMap<>();
    private final Map<String, String> classNameByInterfaceName = new HashMap<>();
    private final Map<String, String> classNameByAnnotationName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) throws Exception {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) throws Exception {
        checkConfigClass(configClass);

        Constructor<?> constructor = configClass.getConstructor();
        Object object = constructor.newInstance();

        Method[] methodsAll = configClass.getDeclaredMethods();
        Set<Method> methods = new TreeSet<>(getMethodComparator());

        methods.addAll(Arrays.asList(methodsAll));

        for (Method method : methods) {
            if (method.getDeclaredAnnotation(AppComponent.class) == null) continue;

            Type[] types = method.getGenericParameterTypes();
            Object resultObject = null;
            if (types.length > 0) {
                int sizeArray = types.length;
                Object[] args = new Object[sizeArray];
                for (int i = 0; i < types.length; i++) {
                    args[i] = getAppComponent((Class) types[i]);
                }
                if (!checkArrayWithNull(args)) {
                    resultObject = method.invoke(object, args);
                }
            } else {
                resultObject = method.invoke(object);
            }

            if (resultObject != null) {
                saveComponent(resultObject, method);
            }
        }
        appComponentsByNameClass.entrySet().forEach(e -> logger.info("{} - {}", e.getKey(), e.getValue()));
    }

    private void saveComponent(Object resultObject, Method method) {
        String classNameResultObject = resultObject.getClass().getSimpleName();
        String interfaceNameReturnMethod = ((Class) method.getGenericReturnType()).getSimpleName();
        String annotationName = method.getDeclaredAnnotation(AppComponent.class).name();

        appComponentsByNameClass.put(classNameResultObject, resultObject);
        classNameByInterfaceName.put(interfaceNameReturnMethod, classNameResultObject);
        classNameByAnnotationName.put(annotationName, classNameResultObject);
    }

    private Comparator<Method> getMethodComparator() {
        return new Comparator<Method>() {
            @Override
            public int compare(Method m1, Method m2) {
                AppComponent annotationM1 = m1.getDeclaredAnnotation(AppComponent.class);
                AppComponent annotationM2 = m2.getDeclaredAnnotation(AppComponent.class);
                int compareOrder = 0;
                if ((annotationM1 != null) && (annotationM2 != null)) {
                    compareOrder = Integer.compare(annotationM1.order(), annotationM2.order());
                    if (compareOrder == 0) {
                        return annotationM1.name().compareTo(annotationM2.name());
                    } else {
                        return compareOrder;
                    }
                }
                if (annotationM1 != null) return 1;
                if (annotationM2 != null) return -1;
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
        C result = null;
        String componentClassName = componentClass.getSimpleName();
        result = (C) appComponentsByNameClass.get(componentClassName);
        if (result == null) {
            result = (C) appComponentsByNameClass.get(classNameByInterfaceName.get(componentClassName));
        }
        return result;
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        C result = null;
        result = (C) appComponentsByNameClass.get(componentName);
        if (result == null) {
            result = (C) appComponentsByNameClass.get(classNameByAnnotationName.get(componentName));
        }
        return result;
    }

    private boolean checkArrayWithNull(Object[] objects) {
        boolean result = false;
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == null) {
                result = true;
            }
        }
        return result;
    }

}

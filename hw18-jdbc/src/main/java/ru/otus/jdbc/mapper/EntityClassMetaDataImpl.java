package ru.otus.jdbc.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {

    private final Class<T> tClass;

    public EntityClassMetaDataImpl(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Constructor<T> getConstructor() {

        Constructor<?>[] constructors = tClass.getConstructors();
        return (Constructor<T> ) constructors[0];
    }

    @Override
    public Field getIdField() {
        List<Field> allFields = getAllFields();
        Field fieldWithId = null;
        for (Field field: allFields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation: annotations) {
                String currentAnnotation = annotation.toString();
                if (currentAnnotation.equals("@ru.otus.annotations.Id()")) {
                    fieldWithId = field;
                    break;
                }
            }
        }
        return fieldWithId;
    }

    @Override
    public List<Field> getAllFields() {
        Field[] fieldsAll = tClass.getDeclaredFields();
        return Arrays.asList(fieldsAll);
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return null;
    }
}

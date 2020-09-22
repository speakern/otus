package ru.otus.io;

import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.lang.reflect.Field;
import java.util.Collection;

public class AddFieldToObjectToJson implements AddToJson {

    private final JsonObjectBuilder jsonObj;
    private final Field field;
    private final Object object;
    private final MyGson myGson;

    public AddFieldToObjectToJson(JsonObjectBuilder jsonObj, Field field, Object object, MyGson myGson) {
        this.jsonObj = jsonObj;
        this.field = field;
        this.object = object;
        this.myGson = myGson;
    }

    @Override
    public void addNull() {
        jsonObj.add(field.getName(), JsonValue.NULL);
    }

    @Override
    public void addPrimitive() {
        myGson.addPrimitiveTo(new AddPrimitiveToObject(jsonObj, field, object));
    }

    @Override
    public void addArray() {
        jsonObj.add(field.getName(), myGson.createArrayToJson(object));
    }

    @Override
    public void addCollection() {
        jsonObj.add(field.getName(), myGson.createCollectionToJson((Collection) object));
    }

    @Override
    public void addObject() {
        jsonObj.add(field.getName(), myGson.createObjectToJson(object));
    }

    public Object getObject() {
        return object;
    }
}

package ru.otus.io;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.util.Collection;

public class CreateObjectToJson implements AddToJson {
    private JsonObjectBuilder jsonObj;
    private JsonArrayBuilder jsonArray;
    private final Object object;
    private final MyGson myGson;

    public CreateObjectToJson(Object object, MyGson myGson) {
        this.object = object;
        this.myGson = myGson;
    }

    @Override
    public void addNull() {

    }

    @Override
    public void addPrimitive() {

    }

    @Override
    public void addArray() {
        jsonArray = myGson.createArrayToJson(object);
    }

    @Override
    public void addCollection() {
        jsonArray = myGson.createCollectionToJson((Collection) object);
    }

    @Override
    public void addObject() {
        jsonObj = myGson.createObjectToJson(object);
    }

    @Override
    public Object getObject() {
        return object;
    }

    @Override
    public String toString() {
        if (jsonArray != null) return jsonArray.build().toString();
        if (jsonObj != null) return jsonObj.build().toString();
        return "";
    }
}

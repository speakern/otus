package ru.otus.io;

import javax.json.JsonArrayBuilder;
import javax.json.JsonValue;
import java.util.Collection;

public class AddMemberToArrayToJson implements AddToJson {

    private final JsonArrayBuilder jsonArray;
    private final Object object;
    private final MyGson myGson;

    public AddMemberToArrayToJson(JsonArrayBuilder jsonArray, Object object, MyGson myGson) {
        this.jsonArray = jsonArray;
        this.object = object;
        this.myGson = myGson;
    }

    @Override
    public void addNull() {
        jsonArray.add(JsonValue.NULL);
    }

    @Override
    public void addPrimitive() {
        myGson.addPrimitiveTo(new AddPrimitiveToArray(jsonArray, object));
    }

    @Override
    public void addArray() {
        jsonArray.add(myGson.createArrayToJson(object));
    }

    @Override
    public void addCollection() {
        jsonArray.add(myGson.createCollectionToJson((Collection) object));
    }

    @Override
    public void addObject() {
        jsonArray.add(myGson.createObjectToJson(object));
    }

    @Override
    public Object getObject() {
        return object;
    }
}

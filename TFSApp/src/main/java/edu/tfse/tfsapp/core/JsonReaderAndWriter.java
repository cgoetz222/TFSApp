package edu.tfse.tfsapp.core;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JsonReaderAndWriter {
  
  public static void test() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(StringProperty.class, new StringPropertyAdapter());
    Gson gson = gsonBuilder.create();
    List<Person> persons = new ArrayList<>();
    persons.add(new Person(1, "firstname 1", "lastname 1"));
    persons.add(new Person(2, "firstname 2", "lastname 2"));
    
    String json = gson.toJson(persons);
    System.out.println("exported json:\n" + json + "\n\n");
    
    Type type = new TypeToken<List<Person>>() {
    }.getType();
    List<Person> readedData = gson.fromJson(json, type);
    System.out.println("imported json: ");
    System.out.println(readedData);
    
  }
}

final class StringPropertyAdapter implements JsonSerializer<StringProperty>, JsonDeserializer<StringProperty> {
  
  @Override
  public StringProperty deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    return new SimpleStringProperty(json.getAsJsonPrimitive().getAsString());
  }
  
  @Override
  public JsonElement serialize(StringProperty src, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(src.getValue());
  }
}

class Person {
  private long id;
  private StringProperty firstName;
  private StringProperty lastName;
  
  Person(long id, String firstName, String lastName) {
    this.id = id;
    this.firstName = new SimpleStringProperty(firstName);
    this.lastName = new SimpleStringProperty(lastName);
  }
  
  @Override
  public String toString() {
    return "(id = " + id + ", firstName = " + firstName + ", lastName = " + lastName + ")";
  }
}
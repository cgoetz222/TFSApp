package edu.tfse.tfsapp.helper;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// https://www.java-forum.org/thema/gson-propleme-bei-stringproperty.174049/
public class StringPropertyAdapter implements JsonSerializer<StringProperty>, JsonDeserializer<StringProperty> {
	  
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
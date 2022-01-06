package edu.tfse.tfsapp.helper;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

// https://www.java-forum.org/thema/gson-propleme-bei-stringproperty.174049/
public class BooleanPropertyAdapter implements JsonSerializer<BooleanProperty>, JsonDeserializer<BooleanProperty> {
	  
	  @Override
	  public BooleanProperty deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
	      throws JsonParseException {
		  return new SimpleBooleanProperty(json.getAsJsonPrimitive().getAsBoolean());
	  }
	  
	  @Override
	  public JsonElement serialize(BooleanProperty src, Type typeOfSrc, JsonSerializationContext context) {
		  return new JsonPrimitive(src.getValue());
	  }
}
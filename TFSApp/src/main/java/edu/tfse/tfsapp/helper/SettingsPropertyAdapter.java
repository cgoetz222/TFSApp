package edu.tfse.tfsapp.helper;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.tfse.tfsapp.core.Settings;

//source: https://stackoverflow.com/questions/11038553/serialize-java-object-with-gson
public class SettingsPropertyAdapter implements JsonSerializer<Settings>, JsonDeserializer<Settings> {
	  
	  @Override
	  public Settings deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
	      throws JsonParseException {
	    return new Settings();
	  }
	  
	  @Override
	  public JsonElement serialize(final Settings settings, Type typeOfSrc, JsonSerializationContext context) {
		  JsonObject result = new JsonObject();
		  
		  result.add("enableLogging", new JsonPrimitive(settings.getEnableLogging().getValue()));
		  
		  return result;
	  }
}
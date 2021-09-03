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

import edu.tfse.tfsapp.data.User;

//source: https://stackoverflow.com/questions/11038553/serialize-java-object-with-gson
public class UserPropertyAdapter implements JsonSerializer<User>, JsonDeserializer<User> {
	  
	  @Override
	  public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
	      throws JsonParseException {
	    return new User();
	  }
	  
	  @Override
	  public JsonElement serialize(final User user, Type typeOfSrc, JsonSerializationContext context) {
		  JsonObject result = new JsonObject();
		  
		  result.add("name", new JsonPrimitive(user.getName().get()));
		  result.add("age", new JsonPrimitive(user.getAge().get()));
		  result.add("height", new JsonPrimitive(user.getHeight().get()));
		  result.add("weight", new JsonPrimitive(user.getWeight().get()));
		  
		  return result;
	  }
}
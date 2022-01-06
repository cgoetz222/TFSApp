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

import edu.tfse.tfsapp.data.Screening;

//source: https://stackoverflow.com/questions/11038553/serialize-java-object-with-gson
public class ScreeningPropertyAdapter implements JsonSerializer<Screening>, JsonDeserializer<Screening> {
	  
	  @Override
	  public Screening deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
	      throws JsonParseException {
	    return new Screening();
	  }
	  
	  @Override
	  public JsonElement serialize(final Screening screening, Type typeOfSrc, JsonSerializationContext context) {
		  JsonObject result = new JsonObject();
		  
		  result.add("normalHeartRate", new JsonPrimitive(screening.getNormalHeartRate().get()));
		  result.add("maxHeartRate", new JsonPrimitive(screening.getMaxHeartRate().get()));
		  result.add("backMobility", new JsonPrimitive(screening.getBackMobility().get()));
		  result.add("bellyMuscleStrength", new JsonPrimitive(screening.getBellyMuscleStrength().get()));
		  
		  return result;
	  }
}
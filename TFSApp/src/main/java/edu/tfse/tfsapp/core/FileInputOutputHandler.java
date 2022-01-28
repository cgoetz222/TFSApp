package edu.tfse.tfsapp.core;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.gluonhq.attach.storage.StorageService;
import com.gluonhq.attach.util.Services;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import edu.tfse.tfsapp.data.Screening;
import edu.tfse.tfsapp.data.User;
import edu.tfse.tfsapp.helper.BooleanPropertyAdapter;
import edu.tfse.tfsapp.helper.Messages;
import edu.tfse.tfsapp.helper.ScreeningPropertyAdapter;
import edu.tfse.tfsapp.helper.SettingsPropertyAdapter;
import edu.tfse.tfsapp.helper.StringPropertyAdapter;
import edu.tfse.tfsapp.helper.UserPropertyAdapter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

public class FileInputOutputHandler {
	private static final String SCREENING_FILE = "TFSEAPPScreeningData.json"; //$NON-NLS-1$
	private static final String SETTINGS_FILE = "TFSEAPPSettings.json"; //$NON-NLS-1$
	private static final String USERDATA_FILE = "TFSEAPPData.json"; //$NON-NLS-1$
	
    // singleton instance
    private static FileInputOutputHandler instance = null;
             
    public static final File ROOT_DIR;
    
    static {
        ROOT_DIR = Services.get(StorageService.class)
                    .flatMap(StorageService::getPrivateStorage)
                    .orElseThrow(() -> new RuntimeException(Messages.getString("DataHandler.error.readJSONFile"))); //$NON-NLS-1$
    }
    
	/**
	 * returns the singleton instance of the data center
	 * @return
	 */
	public static FileInputOutputHandler getInstance() {
		if(instance != null) {
		} else {
			instance = new FileInputOutputHandler();
		}
		
		return instance;
	}
	
	/**
	 * Initialize the screening data for the current user from the according JSON file
	 * @return screening data
	 */
	public Screening initScreeningData() {
		if(instance != null) {
			File screeningFile = new File(ROOT_DIR, SCREENING_FILE); 
			
			try {
	            if (!screeningFile.exists()) {
	            	// create file if it doesn't exist
	                try (FileWriter writer = new FileWriter(screeningFile)) {
	                    writer.write("{\"normalHeartRate\":\"60\",\"maxHeartRate\":\"200\",\"backMobility\":\"1\",\"bellyMuscleStrength\":\"1\"}"); //$NON-NLS-1$
	                    writer.flush();
	                    writer.close();
	                }
	            }
        		GsonBuilder gsonBuilder = new GsonBuilder();
        	    gsonBuilder.registerTypeAdapter(StringProperty.class, new StringPropertyAdapter());
        	    Gson gson = gsonBuilder.create();
            	JsonReader reader = new JsonReader(new FileReader(new File(ROOT_DIR, SCREENING_FILE))); 
        		
            	return gson.fromJson(reader, new TypeToken<Screening>() {}.getType());	            
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}

		return new Screening();
	}
	
	/**
	 * Initialize the app settings from the according JSON file
	 * @return settings
	 */
	public Settings initSettings() {
		if(instance != null) {
			File settingsFile = new File(ROOT_DIR, SETTINGS_FILE); 
			
			try {
	            if (!settingsFile.exists()) {
	            	// create file if it doesn't exist
	                try (FileWriter writer = new FileWriter(settingsFile)) {
	                    writer.write("{\"enableLogging\":\"false\"}"); //$NON-NLS-1$
	                    writer.flush();
	                    writer.close();
	                }
	            } else {
	        		GsonBuilder gsonBuilder = new GsonBuilder();
	        	    gsonBuilder.registerTypeAdapter(BooleanProperty.class, new BooleanPropertyAdapter());
	        	    Gson gson = gsonBuilder.create();
	            	JsonReader reader = new JsonReader(new FileReader(new File(ROOT_DIR, SETTINGS_FILE))); 
	        		
	            	return gson.fromJson(reader, new TypeToken<Settings>() {}.getType());
	            }
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return new Settings();
	}
	
	/**
	 * Initializes the user data from a JSON-file
	 * @return user
	 */
	public User initUserData() {
		if(instance != null) {
			File jsonFile = new File(ROOT_DIR, USERDATA_FILE);
			
			try {
	            if (!jsonFile.exists()) {
	            	// create file if it doesn't exist
	                try (FileWriter writer = new FileWriter(jsonFile)) {
	                    writer.write("{\"name\":\"Name\",\"sex\":\"m\",\"birthdate\":\"01.01.2000\",\"height\":\"180\",\"weight\":\"75\"}"); //$NON-NLS-1$
	                    writer.flush();
	                    writer.close();
	                }
	            } 
        		GsonBuilder gsonBuilder = new GsonBuilder();
        	    gsonBuilder.registerTypeAdapter(StringProperty.class, new StringPropertyAdapter());
        	    Gson gson = gsonBuilder.create();
            	JsonReader reader = new JsonReader(new FileReader(new File(ROOT_DIR, USERDATA_FILE)));
        		
            	// init user from JSON file
            	return gson.fromJson(reader, new TypeToken<User>() {}.getType());
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return new User();
	}
	
	/**
	 * Save the screening data to the appropriate JSON-file.
	 * @param screening data
	 */
	public void saveScreeningData(Screening screeningData) {
		if(instance != null) {
			GsonBuilder gsonBuilder = new GsonBuilder();
    	    gsonBuilder.registerTypeAdapter(Screening.class, new ScreeningPropertyAdapter());
    	    Gson gson = gsonBuilder.create();
    	    FileWriter writer;
			try {
				writer = new FileWriter(new File(ROOT_DIR, SCREENING_FILE)); 

				gson.toJson(screeningData, writer);
	        	writer.flush();
	        	writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}		
	}
	
	/**
	 * Save the settings to the appropriate JSON-file.
	 * @param settings
	 */
	public void saveSettings(Settings settings) {
		if(instance != null) {
			GsonBuilder gsonBuilder = new GsonBuilder();
    	    gsonBuilder.registerTypeAdapter(Settings.class, new SettingsPropertyAdapter());
    	    Gson gson = gsonBuilder.create();
    	    FileWriter writer;
			try {
				writer = new FileWriter(new File(ROOT_DIR, SETTINGS_FILE)); 

				gson.toJson(settings, writer);
	        	writer.flush();
	        	writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}		
	}
	
	/**
	 * Save the user data to the appropriate JSON-file.
	 * @param user
	 */
	public void saveUserData(User user) {
		if(instance != null) {
			GsonBuilder gsonBuilder = new GsonBuilder();
    	    gsonBuilder.registerTypeAdapter(User.class, new UserPropertyAdapter());
    	    Gson gson = gsonBuilder.create();
    	    FileWriter writer;
			try {
				writer = new FileWriter(new File(ROOT_DIR, USERDATA_FILE)); 

				gson.toJson(user, writer);
	        	writer.flush();
	        	writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}		
	}
}
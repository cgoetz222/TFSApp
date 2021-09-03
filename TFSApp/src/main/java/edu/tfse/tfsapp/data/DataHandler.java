package edu.tfse.tfsapp.data;

import java.io.File;
import java.io.FileWriter;

import com.gluonhq.attach.storage.StorageService;
import com.gluonhq.attach.util.Services;

public class DataHandler {
    // singleton instance
    private static DataHandler instance = null;
    
    private File jsonFile;
       
    public static final File ROOT_DIR; 
    
    static {
        ROOT_DIR = Services.get(StorageService.class)
                    .flatMap(StorageService::getPrivateStorage)
                    .orElseThrow(() -> new RuntimeException("Error retrieving private storage"));
    }
    
	/**
	 * returns the singleton instance of the data center
	 * @return
	 */
	public static DataHandler getInstance() {
		if(instance != null) {
		} else {
			instance = new DataHandler();
		}
		
		return instance;
	}
	
	/**
	 * initializes the data center
	 */
	public void initFromJSON() {
		if(instance != null) {
			jsonFile = new File(ROOT_DIR, "TFSEAPPData.json"); //"TFSEAPPData.json");
			
			try {
	            if (!jsonFile.exists()) {
	                try (FileWriter writer = new FileWriter(jsonFile)) {
	                    //writer.write("{\"name\":\"Duke\",\"subscribed\":true}");
	                    writer.write("{\"name\":\"Test\",\"age\":\"22\",\"height\":\"182\",\"weight\":\"75\"}");
	                    writer.flush();
	                    writer.close();
	                }
	            } else {
	            }
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}		
	}
}
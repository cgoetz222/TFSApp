package edu.tfse.tfsapp.views;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.gluonhq.attach.storage.StorageService;
import com.gluonhq.attach.util.Services;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Icon;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import edu.tfse.tfsapp.data.DataHandler;
import edu.tfse.tfsapp.data.User;
import edu.tfse.tfsapp.helper.StringPropertyAdapter;
import edu.tfse.tfsapp.helper.UserPropertyAdapter;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TFSAppMainPresenter {
    @FXML
    private View tfsappmain;

    @FXML
    private Label label;
    
    @FXML 
    private TextField textFieldName;
    @FXML 
    private TextField textFieldAge;
    @FXML 
    private TextField textFieldHeight;
    @FXML 
    private TextField textFieldWeight;
    
    @FXML
    private Button buttonSave;
    
    public static final File ROOT_DIR; 
    
    static {
        ROOT_DIR = Services.get(StorageService.class)
                    .flatMap(StorageService::getPrivateStorage)
                    .orElseThrow(() -> new RuntimeException("Error retrieving private storage"));
    }
   
    private User user;
  
    public void initialize() {
    	try {
    		DataHandler dh = DataHandler.getInstance();
    		dh.initFromJSON();

    		GsonBuilder gsonBuilder = new GsonBuilder();
    	    gsonBuilder.registerTypeAdapter(StringProperty.class, new StringPropertyAdapter());
    	    Gson gson = gsonBuilder.create();
        	JsonReader reader = new JsonReader(new FileReader(new File(ROOT_DIR, "TFSEAPPData.json")));
    		
        	user = gson.fromJson(reader, new TypeToken<User>() {}.getType());
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	     
        tfsappmain.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().getDrawer().open()));
               
                appBar.setTitleText("Theodor-Frey-Schule App");
                
                buttonSave.setGraphic(new Icon(MaterialDesignIcon.SAVE));
            }
            
            initDataBinding();
        });
    }
    
    @FXML
    void buttonClick() {
        // store an object with an ObjectDataWriter created from the FileClient
    	try {
    	    GsonBuilder gsonBuilder = new GsonBuilder();
    	    gsonBuilder.registerTypeAdapter(User.class, new UserPropertyAdapter());
    	    Gson gson = gsonBuilder.create();
    	    FileWriter writer = new FileWriter(new File(ROOT_DIR, "TFSEAPPData.json"));
    	    
        	gson.toJson(user, writer);
        	writer.flush();
        	writer.close();
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }

    private void initDataBinding() {
       	textFieldName.textProperty().bindBidirectional(user.getName());
       	textFieldAge.textProperty().bindBidirectional(user.getAge());
       	textFieldHeight.textProperty().bindBidirectional(user.getHeight());
       	textFieldWeight.textProperty().bindBidirectional(user.getWeight());
    }
}
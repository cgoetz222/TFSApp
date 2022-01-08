package edu.tfse.tfsapp.views;

import java.io.File;
import java.io.FileWriter;
import java.util.ResourceBundle;

import com.gluonhq.attach.storage.StorageService;
import com.gluonhq.attach.util.Services;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.tfse.tfsapp.core.FileInputOutputHandler;
import edu.tfse.tfsapp.data.User;
import edu.tfse.tfsapp.helper.Messages;
import edu.tfse.tfsapp.helper.UserPropertyAdapter;
import edu.zgb.ui.fx.util.DoubleTextField;
import edu.zgb.ui.fx.util.IntegerTextField;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class TFSAppMainPresenter {
    @FXML
    private View tfsappmain;
    
    @FXML 
    private TextField textFieldName;
    @FXML 
    private IntegerTextField textFieldAge;
    @FXML 
    private IntegerTextField textFieldHeight;
    @FXML 
    private DoubleTextField textFieldWeight;
    
    @FXML
    private Label labelMyBMI;
    @FXML
    private Label labelBMIRange;
    @FXML
    private Label labelWeightClassification;
       
    @FXML
    private ComboBox<String> comboBoxSex;
    
    @FXML
    DatePicker datePicker;
   
    public static final File ROOT_DIR; 
    
    static {
        ROOT_DIR = Services.get(StorageService.class)
                    .flatMap(StorageService::getPrivateStorage)
                    .orElseThrow(() -> new RuntimeException(Messages.getString("TFSAppMainPresenter.error.readJSONFile"))); //$NON-NLS-1$
    }
   
    private User user;
  
    public void initialize() {
    	try {
    		FileInputOutputHandler fioHandler = FileInputOutputHandler.getInstance();
    		user = fioHandler.initUserData();

        	user.update();
        	
        	// fill combo box for the choice of the sex of the current user
        	String bundleName = "edu.tfse.tfsapp.views.views"; //$NON-NLS-1$
        	ResourceBundle resourceBundle = ResourceBundle.getBundle(bundleName);
        	comboBoxSex.getItems().removeAll(comboBoxSex.getItems());
        	comboBoxSex.getItems().addAll(resourceBundle.getString("main.sex.m"), resourceBundle.getString("main.sex.f"), resourceBundle.getString("main.sex.d"));
        	switch(user.getSex().get().trim()) {
        		case "m": comboBoxSex.getSelectionModel().select(resourceBundle.getString("main.sex.m")); break;
        		case "f": comboBoxSex.getSelectionModel().select(resourceBundle.getString("main.sex.f")); break;
        		case "d": comboBoxSex.getSelectionModel().select(resourceBundle.getString("main.sex.d")); break;
        	}
        	
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	
    	textFieldAge.setMaxValue(100);
    	textFieldHeight.setMaxValue(250);
    	
    	// add the main menu
        tfsappmain.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().getDrawer().open()));
               
                appBar.setTitleText(Messages.getString("TFSAppMainPresenter.appbar")); //$NON-NLS-1$
            }
            
            initDataBinding();
        }); 
    }
    
    @FXML
    void buttonClick() {
    	storeUserData();
    }
    
    @FXML 
    void chooseSex() {
    	switch(comboBoxSex.getSelectionModel().getSelectedIndex()) {
    		case 0: user.setSex("m"); break;
    		case 1: user.setSex("f"); break;
    		case 2: user.setSex("d"); break;
    	}
    	updateUser();
    }
    
    void onAgeChanged(String age) {
    	user.setAge(age);
    	updateUser();
    }
    
    void onHeightChanged(String height) {
    	user.setHeight(height);
    	updateUser();
    }
    
    void onWeightChanged(String weight) {
    	user.setWeight(weight);
    	updateUser();
    }
    
    @FXML
    void onBirthdateSelected() {
    	user.setBirthdate(datePicker.getValue());
    	storeUserData();
    }

    private void initDataBinding() {
       	textFieldName.textProperty().bindBidirectional(user.getName());
       	textFieldAge.textProperty().bindBidirectional(user.getAge());
       	textFieldAge.textProperty().addListener((observable, oldValue, newValue) -> {
       	    onAgeChanged(newValue);
       	});
       	textFieldHeight.textProperty().bindBidirectional(user.getHeight());
       	textFieldHeight.textProperty().addListener((observable, oldValue, newValue) -> {
       	    onHeightChanged(newValue);
       	});
       	textFieldWeight.textProperty().bindBidirectional(user.getWeight());
       	textFieldWeight.textProperty().addListener((observable, oldValue, newValue) -> {
       	    onWeightChanged(newValue);
       	});
       	labelMyBMI.textProperty().bind(user.getBMI());
       	labelBMIRange.textProperty().bind(user.getBMIRange());
       	labelWeightClassification.textProperty().bind(user.getWeightClassification());       
       	
       	setBMIClassification();
       	
       	datePicker.setValue(user.getBirthdateAsDate());
    }
    
    private void updateUser() {
    	user.update();
    	storeUserData();
    	
    	setBMIClassification();
    }
    
    private void setBMIClassification() {
       	if(user.getBMIStatus() == User.HIGH_WEIGHT) {
           	labelWeightClassification.getStyleClass().removeAll(labelWeightClassification.getStyleClass());
           	labelWeightClassification.getStyleClass().add("highWeight");
       	} else if(user.getBMIStatus() == User.LOW_WEIGHT) {
           	labelWeightClassification.getStyleClass().removeAll(labelWeightClassification.getStyleClass());
           	labelWeightClassification.getStyleClass().add("lowWeight");
       	} else {
           	labelWeightClassification.getStyleClass().removeAll(labelWeightClassification.getStyleClass());
           	labelWeightClassification.getStyleClass().add("normalWeight");
       	}
    }
    
    public void onShutdown() {
    	storeUserData();
    }
    
    private void storeUserData() {
        // store an object with an ObjectDataWriter created from the FileClient
    	try {
    	    GsonBuilder gsonBuilder = new GsonBuilder();
    	    gsonBuilder.registerTypeAdapter(User.class, new UserPropertyAdapter());
    	    Gson gson = gsonBuilder.create();
    	    FileWriter writer = new FileWriter(new File(ROOT_DIR, "TFSEAPPData.json")); //$NON-NLS-1$
    	    
        	gson.toJson(user, writer);
        	writer.flush();
        	writer.close();
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
}
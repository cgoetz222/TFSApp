package edu.tfse.tfsapp.views;

import java.util.ArrayList;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CardPane;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import edu.tfse.tfsapp.core.FileInputOutputHandler;
import edu.tfse.tfsapp.data.Screening;
import edu.tfse.tfsapp.helper.Messages;
import edu.zgb.ui.fx.util.IntegerTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class TFSAppScreeningPresenter {
	private static final int NUMBER_SCREENING_STEPS = 3;
	
    @FXML
    View viewScreening;

	@FXML
	HBox hboxScreeningProgess;
	@FXML
	VBox vboxMiddle;
	@FXML
	CardPane<VBox> cardPaneScreening;
	@FXML
	VBox vboxScreeningIcon1;
	@FXML
	VBox vboxScreeningIcon2;
	@FXML
	VBox vboxScreeningIcon3;
	@FXML
	VBox vboxScreeningData1;
	@FXML
	VBox vboxScreeningData2;
	@FXML
	VBox vboxScreeningData3;
	@FXML
	VBox vboxScreeningProgress;
	
	@FXML
	IntegerTextField itfNormalHeartRate;
	@FXML
	IntegerTextField itfMaxHeartRate;
	
	@FXML
	ToggleGroup toggleGroupBack;
	@FXML
	RadioButton rbMobilityBack1;
	@FXML
	RadioButton rbMobilityBack2;
	@FXML
	RadioButton rbMobilityBack3;
	@FXML
	RadioButton rbMobilityBack4;
	@FXML
	RadioButton rbMobilityBack5;
	
	@FXML
	ToggleGroup toggleGroupBelly;
	@FXML
	RadioButton rbBellyMen1;
	@FXML
	RadioButton rbBellyMen2;
	@FXML
	RadioButton rbBellyMen3;
	@FXML
	RadioButton rbBellyWomen1;
	@FXML
	RadioButton rbBellyWomen2;
	@FXML
	RadioButton rbBellyWomen3;
	
	private ArrayList<Circle> progressCircles;
	private ArrayList<VBox> vboxScreeningIcons;
	private ArrayList<VBox> vboxScreeningData;
	private int currentScreeningTask = 0;
	
	private Screening screeningData;
	
    public void initialize() {
		FileInputOutputHandler fioHandler = FileInputOutputHandler.getInstance();
		screeningData = fioHandler.initScreeningData();
		
    	// animation while switching the screen
    	viewScreening.setShowTransitionFactory(BounceInRightTransition::new);
               
    	// install main menu
    	viewScreening.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().getDrawer().open()));
                appBar.setTitleText(Messages.getString("TFSAppAboutPresenter.appbar")); //$NON-NLS-1$
            }
            
            initDataBinding();
        });

    	// array lists for the header icon and the VBox containing the data for the screening steps
    	vboxScreeningData = new ArrayList<VBox>();
    	vboxScreeningIcons = new ArrayList<VBox>();
    	
    	// add the VBoxes for the screening data 
    	vboxScreeningData.add(vboxScreeningData1);
    	vboxScreeningData.add(vboxScreeningData2);
    	vboxScreeningData.add(vboxScreeningData3);
    	
    	// add the VBoxes for the screening icons 
    	vboxScreeningIcons.add(vboxScreeningIcon1);
    	vboxScreeningIcons.add(vboxScreeningIcon2);
    	vboxScreeningIcons.add(vboxScreeningIcon3);
    	
    	/*
    	 * heart rate
    	 */
    	// initialize the text input fields for the normal heart rate and the maximum heart rate 
    	itfNormalHeartRate.setMaxValue(300);
    	itfMaxHeartRate.setMaxValue(300);
    	
    	// initialize the circles for the progess in the screening process - a red circle indicates the current screening step
    	progressCircles = new ArrayList<Circle>();
    	for(int i = 0; i < NUMBER_SCREENING_STEPS; i++) {
    		Circle c = new Circle(5);
			c.getStyleClass().removeAll(c.getStyleClass());
    		if(i == 0) {
    			c.getStyleClass().add("circleSelected");
    		} else {
    			c.getStyleClass().add("circleUnselected");
    		}
    		progressCircles.add(c);    		
    	}
		hboxScreeningProgess.getChildren().addAll(progressCircles);

		// initialize the first screen for the screening process
		cardPaneScreening.getItems().removeAll(cardPaneScreening.getItems());
		cardPaneScreening.getItems().add(vboxScreeningIcon1);
		cardPaneScreening.getItems().add(vboxScreeningData1);
		cardPaneScreening.getItems().add(vboxScreeningProgress);
		
    	// only for testing on a PC - swiping doesn't work
    	vboxMiddle.setOnScroll((ScrollEvent event) -> {
    		if(event.getDeltaY() < 0) {
    			previousScreeningStep();
    		} else {
    			nextScreeningStep();
    		}
        });
    }
    
    @FXML
    void onSwipeLeft() {
    	nextScreeningStep();
    }
    
    @FXML 
    void onSwipeRight() {
    	previousScreeningStep();
    }
    
    /*
     * prepare the screen for the next screening task
     */
    private void nextScreeningStep() {
    	updateScreeningData();
		if(currentScreeningTask < NUMBER_SCREENING_STEPS - 1) {  // check whether the current screening step is less than the maximum
			// remove all the progress circles
			progressCircles.get(currentScreeningTask).getStyleClass().removeAll(progressCircles.get(currentScreeningTask).getStyleClass());
			// set the current circle as unselected
			progressCircles.get(currentScreeningTask).getStyleClass().add("circleUnselected");
			// increase the counter for the current screening task and set the progress circle accordingly
			currentScreeningTask++;
			progressCircles.get(currentScreeningTask).getStyleClass().removeAll(progressCircles.get(currentScreeningTask).getStyleClass());
			progressCircles.get(currentScreeningTask).getStyleClass().add("circleSelected");

			// remove all the content from the view
			cardPaneScreening.getItems().removeAll(cardPaneScreening.getItems());
			// add the content for the next screening task
			cardPaneScreening.getItems().add(vboxScreeningIcons.get(currentScreeningTask));
			cardPaneScreening.getItems().add(vboxScreeningData.get(currentScreeningTask));
			cardPaneScreening.getItems().add(vboxScreeningProgress);
		}
    }
    
    /*
     * prepare the screen for the previous screening task
     */
    private void previousScreeningStep() {
    	updateScreeningData();
		if(currentScreeningTask > 0) { // check whether the current screening step is higher than zero
			// remove all the progress circles
			progressCircles.get(currentScreeningTask).getStyleClass().removeAll(progressCircles.get(currentScreeningTask).getStyleClass());
			// set the current circle as unselected
			progressCircles.get(currentScreeningTask).getStyleClass().add("circleUnselected");
			// decrease the counter for the current screening task and set the progress circle accordingly
			currentScreeningTask--;
			progressCircles.get(currentScreeningTask).getStyleClass().removeAll(progressCircles.get(currentScreeningTask).getStyleClass());
			progressCircles.get(currentScreeningTask).getStyleClass().add("circleSelected");
			
			// remove all the content from the view
			cardPaneScreening.getItems().removeAll(cardPaneScreening.getItems());
			// add the content for the previous screening task
			cardPaneScreening.getItems().add(vboxScreeningIcons.get(currentScreeningTask));
			cardPaneScreening.getItems().add(vboxScreeningData.get(currentScreeningTask));
			cardPaneScreening.getItems().add(vboxScreeningProgress);
		}
    }
    
    /**
     * update the screening data according to the user input
     */
    private void updateScreeningData() {
    	FileInputOutputHandler.getInstance().saveScreeningData(screeningData);
    }
    
    /**
     * initialize the dataBinding
     */
    private void initDataBinding() {
    	// data binding for the heart rates - screening task 1
    	itfNormalHeartRate.textProperty().bindBidirectional(screeningData.getNormalHeartRate());
    	itfMaxHeartRate.textProperty().bindBidirectional(screeningData.getMaxHeartRate());
    	
    	// setting the data for the screening task 2 - mobility of the back
    	switch(screeningData.getBackMobilityAsInt()) {
			case 1: rbMobilityBack1.setSelected(true); break;
			case 2: rbMobilityBack2.setSelected(true); break;
			case 3: rbMobilityBack3.setSelected(true); break;
			case 4: rbMobilityBack4.setSelected(true); break;
			case 5: rbMobilityBack5.setSelected(true); break;
			default: break;
    	}
	
    	toggleGroupBack.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
        @Override
        	public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
            	if(newValue.equals(rbMobilityBack1)){
            		screeningData.setBackMobility("1");
            	} else if(newValue.equals(rbMobilityBack2)) {
            		screeningData.setBackMobility("2");
            	} else if(newValue.equals(rbMobilityBack3)) {
            		screeningData.setBackMobility("3");
            	} else if(newValue.equals(rbMobilityBack4)) {
            		screeningData.setBackMobility("4");
            	} else if(newValue.equals(rbMobilityBack5)) {
            		screeningData.setBackMobility("5");
            	}
        	}
    	});
    	
    	
    	// setting the data for screening task 3 - belly muscles strength
    	switch(screeningData.getBellyMuscleStrengthAsInt()) {
    		case 1: rbBellyMen1.setSelected(true); break;
    		case 2: rbBellyMen2.setSelected(true); break;
    		case 3: rbBellyMen3.setSelected(true); break;
    		case 4: rbBellyWomen1.setSelected(true); break;
    		case 5: rbBellyWomen2.setSelected(true); break;
    		case 6: rbBellyWomen3.setSelected(true); break;
    		default: break;
    	}
    	
    	toggleGroupBelly.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if(newValue.equals(rbBellyMen1)){
                	screeningData.setBellyMuscleStrength("1");
                } else if(newValue.equals(rbBellyMen2)) {
                	screeningData.setBellyMuscleStrength("2");
                } else if(newValue.equals(rbBellyMen3)) {
                	screeningData.setBellyMuscleStrength("3");
                } else if(newValue.equals(rbBellyWomen1)) {
                	screeningData.setBellyMuscleStrength("4");
                } else if(newValue.equals(rbBellyWomen2)) {
                	screeningData.setBellyMuscleStrength("5");
                } else if(newValue.equals(rbBellyWomen3)) {
                	screeningData.setBellyMuscleStrength("6");
                }
            }
        });
    }
}
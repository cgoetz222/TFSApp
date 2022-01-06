package edu.tfse.tfsapp.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import edu.tfse.tfsapp.core.FileInputOutputHandler;
import edu.tfse.tfsapp.core.Settings;
import edu.tfse.tfsapp.helper.Messages;
import edu.tfse.tfsapp.views.controls.ToggleSwitch;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class TFSAppSettingsPresenter {
	private Settings settings;

    @FXML
    private View tfsappsettings;
    @FXML
    private Pane paneToggleSwitchLogging;
    
    private ToggleSwitch toggleswitchLogging;

    public void initialize() {
    	tfsappsettings.setShowTransitionFactory(BounceInRightTransition::new);
        
        settings = FileInputOutputHandler.getInstance().initSettings();
    	toggleswitchLogging = new ToggleSwitch(settings.isLoggingEnabled());
    	paneToggleSwitchLogging.getChildren().add(toggleswitchLogging);
    	
    	toggleswitchLogging.switchedOnProperty().addListener((obs, oldState, newState) -> {
            boolean isOn = newState.booleanValue();
            
            settings.setLoggingEnabled(isOn);
            FileInputOutputHandler.getInstance().saveSettings(settings);
        });
        
        tfsappsettings.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().getDrawer().open()));
                appBar.setTitleText(Messages.getString("TFSAppSettingsPresenter.appbar")); //$NON-NLS-1$
            }
        });
    }
}
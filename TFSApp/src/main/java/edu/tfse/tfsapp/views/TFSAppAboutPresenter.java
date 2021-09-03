package edu.tfse.tfsapp.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import edu.tfse.tfsapp.core.Version;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TFSAppAboutPresenter {

    @FXML
    private View tfsappabout;
    @FXML
    private Label labelAppVersion;

    public void initialize() {
        tfsappabout.setShowTransitionFactory(BounceInRightTransition::new);
        
        labelAppVersion.setText(Version.VERSION);
        
        tfsappabout.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().getDrawer().open()));
                appBar.setTitleText("Theodor-Frey-Schule App");
            }
        });
    }
}
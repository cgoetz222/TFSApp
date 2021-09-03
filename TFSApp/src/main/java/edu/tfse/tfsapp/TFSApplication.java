package edu.tfse.tfsapp;

import edu.tfse.tfsapp.views.TFSAppMainView;
import edu.tfse.tfsapp.data.DataHandler;
import edu.tfse.tfsapp.views.TFSAppAboutView;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.visual.Swatch;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TFSApplication extends MobileApplication {
	private static DataHandler _dataHandler;

    public static final String TFSAPPMAIN_VIEW = HOME_VIEW;
    public static final String TFSAPPABOUT_VIEW = "TFSAppAbout View";
    
    @Override
    public void init() {
        addViewFactory(TFSAPPMAIN_VIEW, () -> new TFSAppMainView().getView());
        addViewFactory(TFSAPPABOUT_VIEW, () -> new TFSAppAboutView().getView());

        DrawerManager.buildDrawer(this);
        
        //System.out.println(System.getProperty("javafx.version"));
        //System.out.println("JavaFX Version: " + System.getProperty("javafx.version"));
        //System.out.println("JavaFX Runtime Version: " + System.getProperty("javafx.runtime.version"));
        
        _dataHandler = DataHandler.getInstance();
        _dataHandler.initFromJSON();
    }

    @Override
    public void postInit(Scene scene) {
        Swatch.BLUE.assignTo(scene);

        scene.getStylesheets().add(TFSApplication.class.getResource("style.css").toExternalForm());
        ((Stage) scene.getWindow()).getIcons().add(new Image(TFSApplication.class.getResourceAsStream("/icon.png")));
    }

    public static void main(String args[]) {
        launch(args);
    }
}
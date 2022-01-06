package edu.tfse.tfsapp;

import edu.tfse.tfsapp.views.TFSAppMainView;
import edu.tfse.tfsapp.views.TFSAppScreeningView;
import edu.tfse.tfsapp.views.TFSAppSettingsView;
import edu.tfse.tfsapp.core.FileInputOutputHandler;
import edu.tfse.tfsapp.views.TFSAppAboutView;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.visual.Swatch;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TFSApplication extends MobileApplication {
	private static FileInputOutputHandler _fioHandler;

    public static final String TFSAPPMAIN_VIEW = HOME_VIEW;
//    public static final String TFSAPPMAIN_VIEW = "TFSAppMain View"; //$NON-NLS-1$
    public static final String TFSAPPABOUT_VIEW = "TFSAppAbout View"; //$NON-NLS-1$
//    public static final String TFSAPPSSCREENING_VIEW = HOME_VIEW; //$NON-NLS-1$
    public static final String TFSAPPSSCREENING_VIEW = "TFSAppScreening View"; //$NON-NLS-1$
    public static final String TFSAPPSETTINGS_VIEW = "TFSAppSettings View"; //$NON-NLS-1$
       
    @Override
    public void init() {
        addViewFactory(TFSAPPMAIN_VIEW, () -> new TFSAppMainView().getView());
        addViewFactory(TFSAPPSSCREENING_VIEW, () -> new TFSAppScreeningView().getView());
        addViewFactory(TFSAPPABOUT_VIEW, () -> new TFSAppAboutView().getView());
        addViewFactory(TFSAPPSETTINGS_VIEW, () -> new TFSAppSettingsView().getView());

        DrawerManager.buildDrawer(this);
        
        //System.out.println(System.getProperty("javafx.version"));
        //System.out.println("JavaFX Version: " + System.getProperty("javafx.version"));
        //System.out.println("JavaFX Runtime Version: " + System.getProperty("javafx.runtime.version"));
        
        _fioHandler = FileInputOutputHandler.getInstance();
        _fioHandler.initSettings();
    }

    @Override
    public void postInit(Scene scene) {
        Swatch.BLUE.assignTo(scene);

        scene.getStylesheets().add(TFSApplication.class.getResource("style.css").toExternalForm()); //$NON-NLS-1$
        ((Stage) scene.getWindow()).getIcons().add(new Image(TFSApplication.class.getResourceAsStream("/pictures/icon.png"))); //$NON-NLS-1$
    }

    public static void main(String args[]) {
        launch(args);
    }
}
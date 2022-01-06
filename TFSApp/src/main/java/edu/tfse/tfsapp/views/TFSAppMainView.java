package edu.tfse.tfsapp.views;

import com.gluonhq.charm.glisten.mvc.View;

import edu.tfse.tfsapp.helper.Messages;

import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class TFSAppMainView {

    public View getView() {
        try {
//            View view = FXMLLoader.load(TFSAppMainView.class.getResource("tfsappmain.fxml"), Localaziation.getBundle(this)); //$NON-NLS-1$
            FXMLLoader loader = new FXMLLoader(TFSAppMainView.class.getResource("tfsappmain.fxml"), Localiziation.getBundle(this));
            View view = loader.load();
            
            TFSAppMainPresenter controller = loader.getController();
            view.setOnHidden(e -> controller.onShutdown());
            
            return view;
        } catch (IOException e) {
            System.out.println(Messages.getString("TFSAppMainView.error.readFXML") + e); //$NON-NLS-1$
            return new View();
        }
    }    
}
package edu.tfse.tfsapp.views;

import com.gluonhq.charm.glisten.mvc.View;

import edu.tfse.tfsapp.helper.Messages;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

public class TFSAppScreeningView {
    
    public View getView() {
        try {       	
            View view = FXMLLoader.load(TFSAppScreeningView.class.getResource("tfsappscreening.fxml"), Localiziation.getBundle(this)); //$NON-NLS-1$
            return view;
        } catch (IOException e) {
            System.out.println(Messages.getString("TFSAppScreeningView.error.readFXML") + e); //$NON-NLS-1$
            e.printStackTrace();
            return new View();
        }
    }
}
package edu.tfse.tfsapp.views;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class TFSAppMainView {

    public View getView() {
        try {
            View view = FXMLLoader.load(TFSAppMainView.class.getResource("tfsappmain.fxml"));
            
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }    
}
package edu.tfse.tfsapp.views;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class TFSAppAboutView {
    
    public View getView() {
        try {
            View view = FXMLLoader.load(TFSAppAboutView.class.getResource("tfsappabout.fxml"));
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }
}
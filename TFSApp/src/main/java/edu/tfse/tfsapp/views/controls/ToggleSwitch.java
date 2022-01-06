package edu.tfse.tfsapp.views.controls;
/**
 * inspired by: https://github.com/AlmasB/FXTutorials/blob/master/src/main/java/com/almasb/ios/IOSApp.java
 */

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ToggleSwitch extends Parent {
	private static final int RECT_WIDTH = 40;
	private static final int RECT_HEIGHT = RECT_WIDTH / 2;
	private static final int CIRCLE_RADIUS = RECT_WIDTH / 4;
	
	private static final Color COLOR_ON = new Color(254.0/255.0, 108.0/255.0, 97.0/255.0, 1.0);
	private static final Color COLOR_OFF = new Color(44.0/255.0, 51.0/255.0, 56.0/255.0, 1.0);
	
    private BooleanProperty switchedOn = new SimpleBooleanProperty(false);

    private TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
    private FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25));

    private ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);
    
    private Label onoffLabel;
    private HBox container;
    private Pane switchPane;

    public BooleanProperty switchedOnProperty() {
        return switchedOn;
    }

    public ToggleSwitch(boolean on) {
    	switchedOn.set(on);
    	Rectangle background = new Rectangle(RECT_WIDTH, RECT_HEIGHT);
        background.setArcWidth(RECT_HEIGHT);
        background.setArcHeight(RECT_HEIGHT);
        if(on) {
            background.setFill(COLOR_ON);
        } else {
            background.setFill(COLOR_OFF);
        }
        background.setStroke(Color.LIGHTGRAY);

        Circle trigger = new Circle(CIRCLE_RADIUS);
        trigger.setCenterX(CIRCLE_RADIUS);
        trigger.setCenterY(CIRCLE_RADIUS);
        if(on) {
            trigger.setFill(COLOR_ON);
            trigger.setTranslateX(RECT_WIDTH - RECT_HEIGHT);
        } else {
            trigger.setFill(COLOR_OFF);
        }
        trigger.setStroke(Color.LIGHTGRAY);

        DropShadow shadow = new DropShadow();
        shadow.setRadius(2);
        trigger.setEffect(shadow);

        translateAnimation.setNode(trigger);
        fillAnimation.setShape(background);
        
        if(on) {
            onoffLabel = new Label("ein");
        } else {
            onoffLabel = new Label("aus");
        }
        onoffLabel.getStyleClass().add("normalText");

        switchPane = new Pane();
        switchPane.getChildren().addAll(background, trigger);
        
        container = new HBox();
        container.setAlignment(Pos.CENTER_RIGHT);
        container.getChildren().addAll(onoffLabel, switchPane);
        
        getChildren().addAll(container);

        switchedOn.addListener((obs, oldState, newState) -> {
            boolean isOn = newState.booleanValue();
            if(isOn) {
            	trigger.setFill(COLOR_ON);
            	onoffLabel.setText("ein");
            } else {
            	trigger.setFill(COLOR_OFF);
            	onoffLabel.setText("aus");
            }
            translateAnimation.setToX(isOn ? RECT_WIDTH - RECT_HEIGHT : 0);
            fillAnimation.setFromValue(isOn ? COLOR_OFF : COLOR_ON);
            fillAnimation.setToValue(isOn ? COLOR_ON : COLOR_OFF);

            animation.play();
        });

        setOnMouseClicked(event -> {
            switchedOn.set(!switchedOn.get());
        });
    }
}
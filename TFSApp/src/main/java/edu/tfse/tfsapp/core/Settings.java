package edu.tfse.tfsapp.core;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Settings {
	private BooleanProperty enableLogging = new SimpleBooleanProperty();
	
	public Settings() {
		
	}
	
	public BooleanProperty getEnableLogging() {
		return enableLogging;
	}
	
	public boolean isLoggingEnabled() {
		return enableLogging.get();
	}
	
	public void setLoggingEnabled(boolean enabled) {
		enableLogging.set(enabled);
	}
}
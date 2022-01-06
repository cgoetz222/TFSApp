package edu.tfse.tfsapp.views;

import java.util.Locale;
import java.util.ResourceBundle;

public class Localiziation {
	private static final String VIEWS_BUNDLE_NAME = "edu.tfse.tfsapp.views.views"; //$NON-NLS-1$

	private Localiziation() {
	}

	public static ResourceBundle getBundle(Object obj) {
		try {
        	Locale currentLocale = Locale.getDefault();
        	if(obj instanceof TFSAppAboutView || obj instanceof TFSAppScreeningView || obj instanceof TFSAppMainView || obj instanceof TFSAppSettingsView) {
            	ResourceBundle bundle = ResourceBundle.getBundle(VIEWS_BUNDLE_NAME, currentLocale);
            	return bundle;
			}        	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
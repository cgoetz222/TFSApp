package edu.tfse.tfsapp.views;

import java.util.Locale;
import java.util.ResourceBundle;

public class Localaziation {
	private static final String VIEWS_BUNDLE_NAME = "edu.tfse.tfsapp.views.views"; //$NON-NLS-1$

	private Localaziation() {
	}

	public static ResourceBundle getBundle(Object obj) {
		try {
        	Locale currentLocale = Locale.getDefault();
        	if(obj instanceof TFSAppAboutView || obj instanceof TFSAppMainView) {
            	ResourceBundle bundle = ResourceBundle.getBundle(VIEWS_BUNDLE_NAME, currentLocale);
            	return bundle;
			}        	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
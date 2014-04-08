package br.ufla.lemaf.tramitacao.graphviz;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class GraphVizProperties {

	private static final String			BUNDLE_NAME		= "graphviz";			//$NON-NLS-1$

	private static final ResourceBundle	RESOURCE_BUNDLE	= ResourceBundle.getBundle(BUNDLE_NAME);

	private GraphVizProperties() {
	}

	public static String getProperty(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		}
		catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}

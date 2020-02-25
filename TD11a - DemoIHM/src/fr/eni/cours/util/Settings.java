package fr.eni.cours.util;

import java.io.IOException;
import java.util.Properties;

public class Settings {
	private static  Properties properties;
	
	static {
		try {
			properties = new Properties();
			//properties.load(Settings.class.getResourceAsStream("connexion.properties"));
			properties.loadFromXML(Settings.class.getResourceAsStream("connexion.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		String parametre= properties.getProperty(key, null);
		return parametre;
	}
}

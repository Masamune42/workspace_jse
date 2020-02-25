/**
 * 
 */
package fr.eni_ecole.cours.util;

import java.util.Properties;


public class Settings {
	private static Properties properties;
	
	static {
		try {
			properties = new Properties();
			properties.load(Settings.class.getResourceAsStream("connexion.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key){
		String parametre = properties.getProperty(key,null);
		return parametre;
	}

}

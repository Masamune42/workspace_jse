package fr.eni.demoSwing.ihm;

import javax.swing.SwingUtilities;

public class DemoSwingApp {

	public static void main(String[] args) {
		// Lancer l'application
		SwingUtilities.invokeLater(new Runnable() {
			
			@SuppressWarnings("unused")
			@Override
			public void run() {
				EcranStagiaire frame = new EcranStagiaire();
			}
		});
	}

}

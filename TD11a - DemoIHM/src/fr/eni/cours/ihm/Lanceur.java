package fr.eni.cours.ihm;

import javax.swing.SwingUtilities;

public class Lanceur {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// instanciation de la fenetre
				FenetrePrincipale ecran = new FenetrePrincipale();
				//rendre ma fenetre visible
				ecran.setVisible(true);
			}
		});

	}

}

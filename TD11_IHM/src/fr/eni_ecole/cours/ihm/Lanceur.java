package fr.eni_ecole.cours.ihm;

import javax.swing.SwingUtilities;

public class Lanceur {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				//création de la fenetre
				FenetrePrincipale4 ecr = new FenetrePrincipale4();
				//ecr.pack();
				//affichage de la fenetre
				ecr.setVisible(true);
			}
		});
	}

}

package fr.eni_ecole.cours.ihm.catalogue;

import javax.swing.SwingUtilities;


public class Lanceur {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				//création de la fenetre
				EcranCatalogue ecr = new EcranCatalogue();
				//ecr.pack();
				//affichage de la fenetre
				ecr.setVisible(true);
			}
		});

	}

}

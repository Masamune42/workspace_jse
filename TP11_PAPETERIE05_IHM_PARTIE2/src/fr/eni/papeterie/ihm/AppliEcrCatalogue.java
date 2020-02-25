package fr.eni.papeterie.ihm;

import javax.swing.SwingUtilities;

import fr.eni.papeterie.ihm.ecrCatalogue.EcranCatalogue;

public class AppliEcrCatalogue {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				EcranCatalogue ecr = new EcranCatalogue();
				ecr.pack();
				ecr.setVisible(true);
				
			}
		});
		
	}

}

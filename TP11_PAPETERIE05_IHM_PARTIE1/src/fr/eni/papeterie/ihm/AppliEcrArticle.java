package fr.eni.papeterie.ihm;

import javax.swing.SwingUtilities;

import fr.eni.papeterie.ihm.ecrArticle.EcranArticle;

public class AppliEcrArticle {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				EcranArticle ecr = new EcranArticle();
				ecr.pack();
				ecr.setVisible(true);
				
			}
		});
		
	}

}

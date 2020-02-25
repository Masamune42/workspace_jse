package fr.eni.demoSwing.ihm;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class EcranStagiaire extends JFrame{
	
	public EcranStagiaire(){
		setTitle("Stagiaire");
		//taille de la fenetre
		setSize(new Dimension(300, 300));
		//positionne la framme au milieu de l'ecran
		setLocationRelativeTo(null);
		//affiche la frame
		setVisible(true);
		//EXIT_ON_CLOSE : termine l'application
		//DISPOSE_ON_CLOSE : ferme la fenetre et termine l'application
		// si la derniere fenetre est la derniere affichée
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

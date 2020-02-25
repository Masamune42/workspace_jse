package fr.eni_ecole.cours.ihm;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FenetrePrincipale extends JFrame{
	
	public FenetrePrincipale(){
		//modification du titre de la fenetre
		setTitle("fenetre principale");
		//modification de la position x, y et de la taille de la fenetre largeur, hauteur
		//setBounds(200, 200, 500, 400);
		//taille de la fenetre
		setSize(new Dimension(500, 400));
		//positionne la framme au milieu de l'ecran
		setLocationRelativeTo(null);
		//utilisation des comportements prédéfinis pour la fermeture de la fenetre
		//dispose_on_close : arrêt de l'application lors de la fermeture de la derniere fenetre
		//exit_on_close : arrêt de l'application même si d'autre fenetres sont visibles
		//hide_on_close : la fenetre est simplement masquée (setVisible(false))
		//do_nothing_on_close : rien ne se passe losque l'utilisateur veut fermer la fenetre
		//il faut gerer des evenements pour que l'action de l'utilisateur provoque une action
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}

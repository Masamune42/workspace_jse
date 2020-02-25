package fr.eni.demoSwing.ihm;

import javax.swing.SwingUtilities;

public class DemoSwingApp {

	public static void main(String[] args) {
		// Lancer l'application
/*		JFrame frame = new JFrame("Stagiaire");
		//taille de la fenetre
		frame.setSize(new Dimension(300, 300));
		//positionne la framme au milieu de l'ecran
		frame.setLocationRelativeTo(null);
		//affiche la frame
		frame.setVisible(true);
		//EXIT_ON_CLOSE : termine l'application
		//DISPOSE_ON_CLOSE : ferme la fenetre et termine l'application
		// si la derniere fenetre est la derniere affichée
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
*/
		//gerer l'ecran dans un processus (thread) séparé
		//pour une gestion correcte de la concurrence en swing
		//il est recommandé d'utiliser la classe SwingUtilities
		SwingUtilities.invokeLater(new Runnable() {
			
			@SuppressWarnings("unused")
			@Override
			public void run() {
				EcranStagiaire frame = new EcranStagiaire();
			}
		});
	}

}

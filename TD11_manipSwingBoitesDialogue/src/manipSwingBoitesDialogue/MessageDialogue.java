package manipSwingBoitesDialogue;

import javax.swing.JOptionPane;

public class MessageDialogue {

	public static void main(String[] args) {
		int n = 13;
		// boite de message
		//1er argument  : null car cette boite de message n'est pas rattach�e � une fenetre
		//2eme argument : le message � afficher (le nombre et son carre)
		//3eme argument : le titre de la boite de message
		//4eme argument : le type d'ic�ne
		JOptionPane.showMessageDialog(null, n+" a pour carre "+n*n,"CALCUL DU CARRE",JOptionPane.INFORMATION_MESSAGE);
	}

}

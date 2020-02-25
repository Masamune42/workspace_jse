package manipSwingBoitesDialogue;

import javax.swing.JOptionPane;

public class ConfirmDialogue {

	public static void main(String[] args) {
		int n = 1;
		int reponse;
		do {
			// boite de message
			JOptionPane.showMessageDialog(null, n+" a pour carre "+n*n,"CALCUL DU CARRE",JOptionPane.INFORMATION_MESSAGE);
			
			//nous demandons si l'utilisateur souhaite continuer et j'increment de 3 le nombre
			n+=3;
			
			//boite de confirmation
			//1er argument  : null car cette boite de message n'est pas rattachée à une fenetre
			//2eme argument : le message à afficher (le nombre et son carre)
			//3eme argument : le titre de la boite de message
			//4eme argument : la nature des boutons figurant dans la boite de confirmation (YES ou NO)
			reponse=JOptionPane.showConfirmDialog(null, "affichage du carré suivant ?", "CALCUL DU CARRE", JOptionPane.YES_NO_OPTION);
			
		}while (reponse== JOptionPane.YES_OPTION);

	}

}

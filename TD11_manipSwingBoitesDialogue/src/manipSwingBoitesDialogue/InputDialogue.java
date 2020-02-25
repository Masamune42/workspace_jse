package manipSwingBoitesDialogue;

import javax.swing.JOptionPane;

public class InputDialogue {
	
	public static final String TITRE= "CALCUL DE LA MOYENNE";

	public static void main(String[] args) {
		int n=0;
		double x=0,somme=0,moyenne=0;
		int continuer;
		String message="", fin;
		
		//lecture des différentes valeurs
		do {
			boolean ok;
			n++;
			//boucle de lecture d'une valeur jusqu'à ce qu'elle soit correcte
			do {
				ok=false;
				if (n==1){
					fin="er";
				}else {
					fin="eme";
				}
				message= "Choisir le "+ n+fin+ " nombre.";
				String reponse= JOptionPane.showInputDialog(null, message,TITRE, JOptionPane.QUESTION_MESSAGE);
				//si la reponse est CANCEL alors on continuera de choisir un nombre
				if (reponse == null) { continue; }
				try {
					//on parse la reponse en double
					x= Double.parseDouble(reponse);
					ok=true;
				} catch (NumberFormatException e){
					message= "reponse incorrecte";
					JOptionPane.showMessageDialog(null, message);
				}
			} while (!ok);
			
			somme += x;
			message= "Avez-vous encore des valeurs à saisir ?";
			continuer = JOptionPane.showConfirmDialog(null, message, TITRE, JOptionPane.YES_NO_OPTION);
		} while (continuer == JOptionPane.YES_OPTION);
		
		//calcul de la moyenne et affichage
		moyenne = somme/n;
		message = "moyenne des "+n+" valeurs entrées = "+moyenne;
		JOptionPane.showMessageDialog(null, message, TITRE, JOptionPane.INFORMATION_MESSAGE);
	}

}

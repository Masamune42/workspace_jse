package manipSwingBoitesDialogue;

import javax.swing.JFrame;

public class LanceurInputDialog {

	public static void main(String[] args) {
		String reponse;
		JFrame fenetre= new JFrame("Programmation InputDialog");
		fenetre.setVisible(true);
		fenetre.setSize(600, 400);
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//on interroge l'utilisateur jusqu'à ce qu'il reponde "fin"
		do {
			reponse= ProgInputDialog.afficheInput(fenetre, "Entrez un texte ?");
			if (reponse != null){
				System.out.println("reponse : "+reponse);
			}
			
		} while (reponse == null || !reponse.toUpperCase().equals("FIN"));
	}

}

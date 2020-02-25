package manipSwingBoitesDialogue;

import javax.swing.JFrame;

public class LanceurConfirmDialog {

	public static void main(String[] args) {
		JFrame fenetre= new JFrame("Programmation ConfirmDialog");
		fenetre.setVisible(true);
		fenetre.setSize(600, 400);
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int reponse= ProgConfirmDialog.afficheConfirm(fenetre, "Voulez-vous continuer ?");
		System.out.println("reponse : "+reponse);
	}

}

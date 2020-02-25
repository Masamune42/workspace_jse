package manipSwingBoitesDialogue;

import javax.swing.JFrame;

public class LanceurProgMessageDialog {

	public static void main(String[] args) {
		JFrame fenetre= new JFrame("Programmation MessageDialog");
		fenetre.setVisible(true);
		fenetre.setSize(400, 300);
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ProgMessageDialog.afficheMessage(fenetre, "Bonjour");
		ProgMessageDialog.afficheMessage(fenetre, "et au revoir");
	}

}

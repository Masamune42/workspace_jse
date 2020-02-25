package manipSwingBoitesDialogue;

import java.awt.Dimension;

import javax.swing.JFrame;

public class ProgInputDialog {

	public static String afficheInput(JFrame parent, String message){
		//creation de l'objet boite de dialogue
		BoiteInput boiteInput = new BoiteInput(parent, message);
		//affichage de la boite de dialogue
		boiteInput.setSize(new Dimension(400, 200));
		boiteInput.setLocationRelativeTo(null);
		boiteInput.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		boiteInput.setVisible(true);
		//fin du dialogue
		boiteInput.dispose();
		return boiteInput.getInfo();
	}

}

package manipSwingBoitesDialogue;

import java.awt.Dimension;
import javax.swing.JFrame;

public class ProgConfirmDialog {
	
	public static int afficheConfirm(JFrame parent, String message){
		//creation de l'objet boite de dialogue
		BoiteConfirm boiteConfirm = new BoiteConfirm(parent, message);
		//affichage de la boite de dialogue
		boiteConfirm.setSize(new Dimension(400, 200));
		boiteConfirm.setLocationRelativeTo(null);
		boiteConfirm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		boiteConfirm.setVisible(true);
		//fin du dialogue
		boiteConfirm.dispose();
		return boiteConfirm.getEtat();
	}
}



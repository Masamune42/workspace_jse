package manipControlesSwing;

import javax.swing.SwingUtilities;

public class Lanceur {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				//création de la fenetre
				FenetrePrincipale2 ecr = new FenetrePrincipale2();
				//affichage de la fenetre
				ecr.setVisible(true);
			}
		});
	}

}

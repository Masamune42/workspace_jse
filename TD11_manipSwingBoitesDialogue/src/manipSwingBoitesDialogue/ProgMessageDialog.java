package manipSwingBoitesDialogue;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ProgMessageDialog {

	public static void afficheMessage(JFrame parent, String message){
		//creation de l'objet boite de dialogue
		JDialog boiteMessage = new JDialog(parent, "MESSAGE", true);
		boiteMessage.setSize(new Dimension(200, 100));
		boiteMessage.setLocationRelativeTo(null);
		boiteMessage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//mise en place des composants
		Container contenu = boiteMessage.getContentPane();
		contenu.setLayout(new FlowLayout());
		JLabel texte = new JLabel(message);
		contenu.add(texte);
		JButton bouton = new JButton("OK");
		contenu.add(bouton);
		bouton.addActionListener(new EcouteBouton(boiteMessage));
		// afficher le dialoque
		boiteMessage.setVisible(true);
		//fin sur OK -rien à tester ici
		boiteMessage.dispose();
	}
	
	public static class EcouteBouton implements ActionListener {

		private JDialog jd;
		public EcouteBouton(JDialog jd){
			this.jd = jd;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			jd.setVisible(false);
		}
	
	}
}
package manipSwingMenus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

@SuppressWarnings("serial")
public class Menu22 extends JFrame implements ActionListener, MenuListener{

	private JMenuBar barreMenus;
	private JMenu fichier, edition;
	private JMenuItem ouvrir, sauvegarder, fermer, copier, coller;
	private boolean fichierOuvert, infos;
	private String nomFichier;
	
	public Menu22() {
		setTitle("Exemple de menus");
		setSize(500, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		/* création de la barre de menus */
		barreMenus = new JMenuBar();
		setJMenuBar(barreMenus);
		/* creation du menu fichier et ses options */
		fichier = new JMenu("Fichier");
		barreMenus.add(fichier);
/* ajout : traites par un ecouteur implementant l'interface MenuListener (comportant trois methodes menuSelected, menuDeselected, menuCancel*/
 		fichier.addMenuListener(this);
//***** 
		ouvrir= new JMenuItem("Ouvrir");
		fichier.add(ouvrir);
		ouvrir.addActionListener(this);
		sauvegarder= new JMenuItem("Sauvegarder");
		fichier.add(sauvegarder);
		sauvegarder.addActionListener(this);
		fermer=new JMenuItem("Fermer");
		fichier.add(fermer);
		fermer.addActionListener(this);
		/* creation du menu edition et ses options */
		edition = new JMenu("Edition");
		barreMenus.add(edition);
/* ajout */
		edition.addMenuListener(this);
//*****
		copier = new JMenuItem("Copier");
		edition.add(copier);
		copier.addActionListener(this);
		coller = new JMenuItem("Coller");
		edition.add(coller);
		coller.addActionListener(this);
		//etat initial : pas de fichier ouvert, pas d'infos à copier
		fichierOuvert = false;
		infos = false;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if(source== ouvrir){
			String nom = JOptionPane.showInputDialog(this, "Nom de fichier à ouvrir");
			//test du champ de saisie
			if(nom==null || (nom.equals(""))) return;
			//test si fichier ouvert : on ferme l'ancien fichier
			if (fichierOuvert) {
				JOptionPane.showMessageDialog(this, "On ferme "+nomFichier, "MESSAGES", JOptionPane.INFORMATION_MESSAGE);
			}
			//on ouvre le nouveau fichier
			nomFichier=nom;
			fichierOuvert=true;
			JOptionPane.showMessageDialog(this, "On ouvre "+nomFichier, "MESSAGES", JOptionPane.INFORMATION_MESSAGE);
		}
		if(source==fermer){
			JOptionPane.showMessageDialog(this, "On ferme "+nomFichier, "MESSAGES", JOptionPane.INFORMATION_MESSAGE);
			fichierOuvert=false;
		}
		if(source==sauvegarder){
			JOptionPane.showMessageDialog(this, "On sauvegarde "+nomFichier, "MESSAGES", JOptionPane.INFORMATION_MESSAGE);
		}
		if(source==copier){
			JOptionPane.showMessageDialog(this, "Copie d'information", "MESSAGES", JOptionPane.INFORMATION_MESSAGE);
			infos=true;
		}
		if(source==coller){
			JOptionPane.showMessageDialog(this, "Collage d'information", "MESSAGES", JOptionPane.INFORMATION_MESSAGE);
			infos=false;
		}
	}

/* ajout */
	@Override
	public void menuSelected (MenuEvent e){
		//activation - desactivation des options
		if (fichierOuvert) copier.setEnabled(true); 
		else copier.setEnabled(fichierOuvert);
		coller.setEnabled(infos);
		ouvrir.setEnabled(true); 
		sauvegarder.setEnabled(fichierOuvert);
		fermer.setEnabled(fichierOuvert);
	}
	
	@Override
	public void menuDeselected (MenuEvent e){
	}
	
	@Override
	public void menuCanceled(MenuEvent arg0) {
	}
//*****
}

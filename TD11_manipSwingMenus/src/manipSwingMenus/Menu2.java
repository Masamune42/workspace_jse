package manipSwingMenus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Menu2 extends JFrame implements ActionListener {

	private JMenuBar barreMenus;
	private JMenu fichier, edition;
	private JMenuItem ouvrir, sauvegarder, fermer, copier, coller;
	private boolean fichierOuvert, infos;
	private String nomFichier;
	
	public Menu2(){
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
		copier = new JMenuItem("Copier");
		edition.add(copier);
		copier.addActionListener(this);
		coller = new JMenuItem("Coller");
		edition.add(coller);
		coller.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if(source== ouvrir){
			String nom = JOptionPane.showInputDialog(this, "Nom de fichier à ouvrir");
			//test champs de saisie
			if(nom==null || (nom.equals(""))) return;
			//test fichier ouvert : on ferme l'ancien fichier
			if (fichierOuvert) {
				JOptionPane.showMessageDialog(this, "On ferme "+nomFichier, "MESSAGES", JOptionPane.INFORMATION_MESSAGE);
			}
			// on ouvre le nouveau fichier 
			nomFichier=nom;
			fichierOuvert=true;
			JOptionPane.showMessageDialog(this, "On ouvre "+nomFichier, "MESSAGES", JOptionPane.INFORMATION_MESSAGE);
		}
		if(source==fermer){
			//test si le fichier est ouvert ou pas
			if(fichierOuvert) JOptionPane.showMessageDialog(this, "On ferme "+nomFichier, "MESSAGES", JOptionPane.INFORMATION_MESSAGE);
			else JOptionPane.showMessageDialog(this, "Pas de fichier ouvert", "MESSAGES", JOptionPane.WARNING_MESSAGE);
			fichierOuvert=false;
		}
		if(source==sauvegarder){
			//test si le fichier est ouvet ou pas
			if(fichierOuvert) JOptionPane.showMessageDialog(this, "On sauvegarde "+nomFichier, "MESSAGES", JOptionPane.INFORMATION_MESSAGE);
			else JOptionPane.showMessageDialog(this, "Pas de fichier ouvert à sauvegarder", "MESSAGES", JOptionPane.WARNING_MESSAGE);
		}
		if(source==copier){
			//test si fichier ouvert : on peut copier les infos
			if (fichierOuvert) {
				JOptionPane.showMessageDialog(this, "Copie d'information", "MESSAGES", JOptionPane.INFORMATION_MESSAGE);
				infos=true;
			} else {
				JOptionPane.showMessageDialog(this, "Fichier fermé", "MESSAGES", JOptionPane.WARNING_MESSAGE);
				infos=false;
			}
		}
		if(source==coller){
			//test si des informations à coller
			if(infos) JOptionPane.showMessageDialog(this, "Collage d'information", "MESSAGES", JOptionPane.INFORMATION_MESSAGE);
			else JOptionPane.showMessageDialog(this, "Rien à coller", "MESSAGES", JOptionPane.WARNING_MESSAGE);
			infos=false;
		}
	}
	
}

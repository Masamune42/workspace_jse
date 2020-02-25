package manipSwingMenus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Menu21 extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JMenuBar barreMenus;
	private JMenu fichier, edition;
	private JMenuItem ouvrir, sauvegarder, fermer, copier, coller;
	private boolean fichierOuvert, infos;
	private String nomFichier;
	
	public Menu21(){
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
		//etat initial : pas de fichier ouvert, pas d'infos à copier
		fichierOuvert = false;
		infos = false;
		nomFichier=null;
		ouvrir.setEnabled(true);
		sauvegarder.setEnabled(false);
		fermer.setEnabled(false);
		copier.setEnabled(true);
		coller.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if(source== ouvrir){
			String nom = JOptionPane.showInputDialog(this, "Nom de fichier à ouvrir");
			if(nom==null || (nom.equals(""))) return;
			if (fichierOuvert) {
				JOptionPane.showMessageDialog(this, "On ferme "+nomFichier, "MESSAGES", JOptionPane.INFORMATION_MESSAGE);
			}
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
		//activation - desactivation des options
		copier.setEnabled(true); //par securite
		coller.setEnabled(infos);
		ouvrir.setEnabled(true); //par securite
		sauvegarder.setEnabled(fichierOuvert);
		fermer.setEnabled(fichierOuvert);
	}
	
}

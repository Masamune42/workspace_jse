package manipSwingMenus;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu1 extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JMenuBar barreMenus;
	private JMenu fichier, edition;
	@SuppressWarnings("unused")
	private JMenuItem ouvrir, sauvegarder, fermer, copier, coller;
	
	public Menu1(){
		setTitle("Exemple de menus");
		setSize(300, 150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		/* création de la barre de menus */
		barreMenus = new JMenuBar();
		setJMenuBar(barreMenus);
		/* creation du menu fichier et ses options */
		fichier = new JMenu("Fichier");
		barreMenus.add(fichier);
		ouvrir = ajoute("Ouvrir", fichier);
		sauvegarder =  ajoute("Sauvegarder",fichier);
		fermer = ajoute("Fermer",fichier);
		/* creation du menu edition et ses options */
		edition = new JMenu("Edition");
		barreMenus.add(edition);
		copier = ajoute("Copier", edition);
		coller= ajoute("Coller", edition);
	}
	
	private static JMenuItem ajoute (String libelle, JMenu menu){
		JMenuItem option = new JMenuItem(libelle);
		menu.add(option);
		return option;
	}
}

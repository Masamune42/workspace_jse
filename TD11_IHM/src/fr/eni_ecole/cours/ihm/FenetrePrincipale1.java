package fr.eni_ecole.cours.ihm;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

//*************** creer et placer les composants ******************
/* bonne façon dans la conception d'une interface graphique
1 - créer les différents composants
2 - les placer sur un conteneur intermédiaire
3 - placer le conteneur intermédiaire sur le ContentPane de la fenetre (JFrame)
*/

@SuppressWarnings("serial")
public class FenetrePrincipale1 extends JFrame {

	private JPanel conteneurBtn;
	private JButton btnInserer,btnModifier,btnSupprimer;
	private JMenuBar barreMenu;
	private JMenu menu;
	private JMenuItem mnuInserer,mnuModifier,mnuSupprimer;
	
	public FenetrePrincipale1(){
		setTitle("fenetre principale");
//		setBounds(200, 200, 500, 400);
		setSize(new Dimension(500, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//ajout de la barre de menu à la fenetre
		setJMenuBar(getBarreMenu());
		initComposants();
	}// fin du construteur
	
	private void initComposants(){		
		//ajout du conteneur intermediaire au ContentPane
		//il est accessible par la methode getContentPane de la classe JFrame
		getContentPane().add(getConteneurBtn());
	}

	
	//*** Nous choisissons de déléguer la création des instances des composants   ***
	//*** graphiques aux méthodes get. Cela permet de ne créer l’instance que si  ***
	//*** nécessaire, et permet aussi de bien ordonner le code. Cette méthode est ***
	//*** nommée Lazy instanciation												  ***

	//************** creation des conteneurs intermediaires *************
	public JPanel getConteneurBtn(){
		if (conteneurBtn == null){
			//creation du conteneur intermédiaire regroupant les boutons 164
			conteneurBtn = new JPanel();
			//ajout des boutons au conteneur intermediaire
			conteneurBtn.add(getBtnInserer());
			conteneurBtn.add(getBtnModifier());
			conteneurBtn.add(getBtnSupprimer());
		}
		return conteneurBtn;
	}

	//************** creation des boutons et modification des comportements *************
	
	//bouton Inserer
	public JButton getBtnInserer() {
		if(btnInserer == null){
			btnInserer = new JButton("Inserer");
			btnInserer.setToolTipText("insérer un élève");
			//placer le focus sur le composant insérer
			btnInserer.requestFocus();
			// couleur de fond
			btnInserer.setBackground(Color.LIGHT_GRAY);
			// couleur du texte
			btnInserer.setForeground(Color.BLUE);
			// police, style, taille
			btnInserer.setFont(new Font("Serif", Font.BOLD, 12));
			// apparence du curseur
			btnInserer.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		return btnInserer;
	}

	//bouton Modifier
	public JButton getBtnModifier() {
		if(btnModifier == null){
			btnModifier = new JButton("Modifier");
			btnModifier.setToolTipText("modifier l'adresse d'un élève");
			//desactivé
			btnModifier.setEnabled(false);
		}
		return btnModifier;
	}

	//bouton Supprimer
	public JButton getBtnSupprimer() {
		if(btnSupprimer == null){
			btnSupprimer = new JButton("Supprimer");
			btnSupprimer.setToolTipText("supprimer un élève");
			//masqué
			btnSupprimer.setVisible(false);
		}
		return btnSupprimer;
	}

//************** creation du menu et modification des comportements *************

	public JMenuBar getBarreMenu(){
		if(barreMenu == null){
			barreMenu = new JMenuBar();
			menu = new JMenu("Gestion des élèves");
			mnuInserer= new JMenuItem("Inserer");
			mnuModifier= new JMenuItem("Modifier");
			mnuSupprimer= new JMenuItem("Supprimer");
			mnuInserer.setCursor(new Cursor(Cursor.HAND_CURSOR));
			//ajoute les item au menu
			menu.add(mnuInserer);
			menu.add(mnuModifier);
			menu.add(mnuSupprimer);
			//ajout du menu à la barre de menu
			barreMenu.add(menu);
		}
		return barreMenu;
	}
	
	
}// fin de la classe

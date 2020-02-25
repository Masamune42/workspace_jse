package fr.eni_ecole.cours.ihm;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FenetrePrincipale2 extends JFrame{

	private PanelEleve2 panelEleve;
	private JPanel conteneurBtn;
	private JMenuBar barreMenu;
	private JMenu menu;
	private JButton btnInserer,btnModifier,btnSupprimer;
	private JMenuItem mnuPrecedent,mnuSuivant;
	
	public FenetrePrincipale2(){
		setTitle("fenetre principale");
		//setBounds(200, 200, 500, 400);
		setSize(new Dimension(500, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//ajout de la barre de menu à la fenetre
		setJMenuBar(getBarreMenu());
		initComposants();
	}// fin du construteur
	
	
	private void initComposants(){
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx =0;
		gbc.gridy =0;
		//ajout du conteneur intermediaire au ContentPane
		getContentPane().add(getConteneurEleve(),gbc);
		gbc.gridx=0;
		gbc.gridy =1;
		getContentPane().add(getConteneurBtn(),gbc);
		
	}

	
	//*** Nous choisissons de déléguer la création des instances des composants   ***
	//*** graphiques aux méthodes get. Cela permet de ne créer l’instance que si  ***
	//*** nécessaire, et permet aussi de bien ordonner le code. Cette méthode est ***
	//*** nommée Lazy instanciation												  ***

	//************** creation des conteneurs intermediaires *************
	
	public PanelEleve2 getConteneurEleve(){
		if (panelEleve == null){
			//creation du conteneur intermédiaire regroupant les données
			panelEleve = new PanelEleve2();
		}	
		return panelEleve;
	}
	
	public JPanel getConteneurBtn(){
		if (conteneurBtn == null){
			//creation du conteneur intermédiaire regroupant les boutons 164
			conteneurBtn = new JPanel(new FlowLayout());
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
			// couleur du texte
			btnInserer.setForeground(Color.BLUE);
			// police, style, taille
			btnInserer.setFont(new Font("Serif", Font.BOLD, 12));
			// apparence du curseur
			btnInserer.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnInserer.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					FenetrePrincipale ecr = new FenetrePrincipale();
					ecr.setTitle("Nouvelle Fenetre");
					ecr.setLocationRelativeTo(ecr);
					ecr.setLocation(900, 500);
					ecr.setSize(100, 100);
					//ecr.setBounds(900, 500, 100, 100);
					ecr.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					ecr.setVisible(true);
				}
			});
		}
		return btnInserer;
	}

	//bouton Modifier
	public JButton getBtnModifier() {
		if(btnModifier == null){
			btnModifier = new JButton("Modifier");
			btnModifier.setToolTipText("modifier l'adresse d'un élève");
			// couleur du texte
			btnModifier.setForeground(Color.BLUE);
			// police, style, taille
			btnModifier.setFont(new Font("Serif", Font.BOLD, 12));
			// apparence du curseur
			btnModifier.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		return btnModifier;
	}

	//bouton Supprimer
	public JButton getBtnSupprimer() {
		if(btnSupprimer == null){
			btnSupprimer = new JButton("Supprimer");
			btnSupprimer.setToolTipText("supprimer un élève");
			// couleur du texte
			btnSupprimer.setForeground(Color.BLUE);
			// police, style, taille
			btnSupprimer.setFont(new Font("Serif", Font.BOLD, 12));
			// apparence du curseur
			btnSupprimer.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		return btnSupprimer;
	}
	
	
//************** creation du menu et modification des comportements *************

	public JMenuBar getBarreMenu(){
		if(barreMenu == null){
			barreMenu = new JMenuBar();
			menu = new JMenu("Gestion des élèves");
			mnuPrecedent= new JMenuItem("Précédent");
			mnuSuivant= new JMenuItem("Suivant");
			//ajoute les item au menu
			menu.add(mnuPrecedent);
			menu.add(mnuSuivant);
			//ajout du menu à la barre de menu
			barreMenu.add(menu);
		}
		return barreMenu;
	}
}// fin de la classe

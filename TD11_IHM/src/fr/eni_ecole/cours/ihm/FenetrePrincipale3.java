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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.eni_ecole.cours.bll.EleveManager;
import fr.eni_ecole.cours.bo.Eleve;

@SuppressWarnings("serial")
public class FenetrePrincipale3 extends JFrame{

	private PanelEleve3 panelEleve;
	private JPanel conteneurBtn;
	private JMenuBar barreMenu;
	private JMenu menu;
	private JButton btnPrecedent,btnSuivant;
	private JMenuItem mnuPrecedent,mnuSuivant;
	
//***nouveau*** declaration du catalogue
	private EleveManager eleveManager;
	private int indexListeEleve = 0;
//************
	
	public FenetrePrincipale3(){
//***nouveau*** chargement du catalogue
		//chargement du catalogue EleveManager
		try {
			eleveManager= EleveManager.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showConfirmDialog(this, "une erreur survenue au chargement du catalogue");
			System.exit(1);
		}
//************
		setTitle("fenetre principale");
//		setBounds(200, 200, 500, 400);
		setSize(new Dimension(500, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		//ajout de la barre de menu à la fenetre
		setJMenuBar(getBarreMenu());
		initComposants();
//***nouveau*** si présence d'un éleve dans la liste je l'affiche, sinon champs vides
		Eleve eleveCourant;
		//affichage du premier eleve du catalogue
		//ou un ecran vide si aucun éléve
		if (eleveManager.getEleves().size()>0){
			eleveCourant=eleveManager.getEleve(0);
			btnPrecedent.setEnabled(false);
			mnuPrecedent.setEnabled(false);
			getConteneurEleve().affiche(eleveCourant);
		}else {
			getConteneurEleve().afficheNouveau();
		}
//************
 
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
	
	public PanelEleve3 getConteneurEleve(){
		if (panelEleve == null){
			//creation du conteneur intermédiaire regroupant les données
			panelEleve = new PanelEleve3();
		}	
		return panelEleve;
	}
	
	public JPanel getConteneurBtn(){
		if (conteneurBtn == null){
			//creation du conteneur intermédiaire regroupant les boutons 164
			conteneurBtn = new JPanel(new FlowLayout());
			//ajout des boutons au conteneur intermediaire
			conteneurBtn.add(getBtnPrecedent());
			conteneurBtn.add(getBtnSuivant());
		}
		return conteneurBtn;
	}
	
	//************** creation des boutons et modification des comportements *************
	
	//bouton Precedent
	public JButton getBtnPrecedent() {
		if(btnPrecedent == null){
			btnPrecedent = new JButton("Précédent");
			btnPrecedent.setToolTipText("Enregistrement precedent");
			// couleur du texte
			btnPrecedent.setForeground(Color.BLUE);
			// police, style, taille
			btnPrecedent.setFont(new Font("Serif", Font.BOLD, 12));
			// apparence du curseur
			btnPrecedent.setCursor(new Cursor(Cursor.HAND_CURSOR));
//*****nouveau****
			btnPrecedent.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					btnSuivant.setEnabled(true);
					mnuSuivant.setEnabled(true);
					// sur click du bouton precedent je veux afficher 
					// l'eleve precedent du catalogue
					if (indexListeEleve >0){
						indexListeEleve--;
						Eleve elevePrecedent = eleveManager.getEleve(indexListeEleve);
						getConteneurEleve().affiche(elevePrecedent);
						if (indexListeEleve == 0){
							btnPrecedent.setEnabled(false);
							mnuPrecedent.setEnabled(false);
						}
					} else {
						btnPrecedent.setEnabled(false);
						mnuPrecedent.setEnabled(false);
					}
				}
			});
//**********		
		}
		return btnPrecedent;
	}
	
	//bouton Suivant
	public JButton getBtnSuivant() {
		if(btnSuivant == null){
			btnSuivant = new JButton("Suivant");
			btnSuivant.setToolTipText("enregistrement suivant");
			// couleur du texte
			btnSuivant.setForeground(Color.BLUE);
			// police, style, taille
			btnSuivant.setFont(new Font("Serif", Font.BOLD, 12));
			// apparence du curseur
			btnSuivant.setCursor(new Cursor(Cursor.HAND_CURSOR));
//****phase3 ******
			btnSuivant.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					btnPrecedent.setEnabled(true);
					mnuPrecedent.setEnabled(true);
					// sur click du bouton suivant je veux afficher 
					// l'eleve suivant du catalogue
					if (indexListeEleve < eleveManager.getEleves().size()-1){
						indexListeEleve++;
						Eleve eleveSuivant = eleveManager.getEleve(indexListeEleve);
						getConteneurEleve().affiche(eleveSuivant);
						if (indexListeEleve == eleveManager.getEleves().size()-1){
							btnSuivant.setEnabled(false);
							mnuSuivant.setEnabled(false);
						}
					} else {
						btnSuivant.setEnabled(false);
						mnuSuivant.setEnabled(false);
					}
				}
			});
//*****************
		}
		return btnSuivant;
	}
	
	
//************** creation du menu et modification des comportements *************

	public JMenuBar getBarreMenu(){
		if(barreMenu == null){
			barreMenu = new JMenuBar();
			menu = new JMenu("Gestion des élèves");
			mnuPrecedent= new JMenuItem("Précédent");
			mnuSuivant= new JMenuItem("Suivant");
			//ajoute les item au menu
			menu.add(getMnuPrecedent());
			menu.add(getMnuSuivant());
			//ajout du menu à la barre de menu
			barreMenu.add(menu);
		}
		return barreMenu;
	}

	public JMenuItem getMnuPrecedent() {
		if(mnuPrecedent == null){
			mnuPrecedent = new JMenuItem("Precedent");
			mnuPrecedent.setToolTipText("Enregistrement precedent");
			mnuPrecedent.setForeground(Color.BLUE);
			mnuPrecedent.setFont(new Font("Serif", Font.BOLD, 12));
			mnuPrecedent.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		return mnuPrecedent;
	}
	
	public JMenuItem getMnuSuivant() {
		if(mnuSuivant == null){
			mnuSuivant = new JMenuItem("Suivant");
			mnuSuivant.setToolTipText("enregistrement suivant");
			mnuSuivant.setForeground(Color.BLUE);
			mnuSuivant.setFont(new Font("Serif", Font.BOLD, 12));
			mnuSuivant.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		return mnuSuivant;
	}
	
	
}// fin de la classe

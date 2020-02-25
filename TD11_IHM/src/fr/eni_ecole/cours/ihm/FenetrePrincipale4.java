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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import fr.eni_ecole.cours.bll.BLLException;
import fr.eni_ecole.cours.bll.EleveManager;
import fr.eni_ecole.cours.bo.Eleve;
import fr.eni_ecole.cours.dal.DALException;

@SuppressWarnings("serial")
public class FenetrePrincipale4 extends JFrame implements ActionListener, MenuListener{

	//declaration des variables et composants
	private PanelEleve4 panelEleve;
	private JPanel conteneurBtn;
	private JButton btnNouveau,btnMettreAJour,btnSupprimer,btnPrecedent,btnSuivant;
	private JButton btnAjouter, btnModifier, btnAnnuler;

	private JMenuBar barreMenus;
	private JMenu fichier, edition;
	private JMenuItem ouvrir, sauvegarder, fermer, copier, coller;
	private boolean fichierOuvert, infos;
	private String nomFichier;
	
	//variables de gestion
	private EleveManager eleveManager;
	private int indexListeEleve = 0, index=0;

	
	public FenetrePrincipale4(){
		//chargement du catalogue EleveManager
		try {
			eleveManager= EleveManager.getInstance();
		} catch (BLLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "une erreur survenue au chargement du catalogue");
			System.exit(1);
		}
		setTitle("fenetre principale");
		setSize(new Dimension(700, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 

		//ajout de la barre de menu à la fenetre
		setJMenuBar(getBarreMenu());
		//ajout des conteneurs au contentPane
		initComposants();
		
		//affichage du premier eleve du catalogue
		//ou un ecran vide si aucun éléve
		Eleve eleveCourant;
		if (eleveManager.getEleves().size()>0){
			eleveCourant=eleveManager.getEleve(0);
			getConteneurEleve().affiche(eleveCourant);
			//gestion de la visibilité des boutons : etat initial
			btnPrecedent.setVisible(true);
			btnNouveau.setVisible(true);
			btnAjouter.setVisible(false);
			btnMettreAJour.setVisible(true);
			btnModifier.setVisible(false);
			btnAnnuler.setVisible(false);
			btnSupprimer.setVisible(true);
			btnSuivant.setVisible(true);
			//activation / desactivation des boutons : etat initial
			btnPrecedent.setEnabled(true);
			btnNouveau.setEnabled(true);
			btnMettreAJour.setVisible(true);
			btnSupprimer.setEnabled(true);
			btnSuivant.setEnabled(true);
		}else {
			getConteneurEleve().afficheNouveau();
		}

		//création d'une instance d'une classe anonyme charger de gérer l'evenement 'fermeture fenetre'
		addWindowListener(new WindowAdapter()
		//debut de la definition de la classe
		{
			public void windowClosing(WindowEvent arg0){
				System.exit(0);
			}
		}//fin de la définition de la classe
		); //fin de l'appel à la méthode addWindowListener
		
	}// fin du construteur
	
	
	private void initComposants(){
		//ajout du conteneur intermediaire au ContentPane
		//getContentPane().add(getConteneurEleve(),BorderLayout.CENTER);
		//getContentPane().add(getConteneurBtn(),BorderLayout.SOUTH);
		//ou :
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx =0;
		gbc.gridy =0;
		//ajout du conteneur intermediaire ConteneurEleve au ContentPane
		getContentPane().add(getConteneurEleve(),gbc);
		gbc.gridx=0;
		gbc.gridy =1;
		//ajout du conteneur intermediaire ConteneurBouton au ContentPane
		getContentPane().add(getConteneurBtn(),gbc);
	}

	
	//*** Nous choisissons de déléguer la création des instances des composants   ***
	//*** graphiques aux méthodes get. Cela permet de ne créer l’instance que si  ***
	//*** nécessaire, et permet aussi de bien ordonner le code. Cette méthode est ***
	//*** nommée Lazy instanciation												  ***

	//************** creation des conteneurs intermediaires *************
	
	public PanelEleve4 getConteneurEleve(){
		if (panelEleve == null){
			//creation du conteneur intermédiaire regroupant les données
			panelEleve = new PanelEleve4();
		}	
		return panelEleve;
	}
	
	public JPanel getConteneurBtn(){
		if (conteneurBtn == null){
			//creation du conteneur intermédiaire regroupant les boutons (changement du type de layout)
			conteneurBtn = new JPanel(new FlowLayout());
			//ajout des boutons au conteneur intermediaire
			conteneurBtn.add(getBtnPrecedent());
			conteneurBtn.add(getBtnNouveau());
			conteneurBtn.add(getBtnAjouter());
			conteneurBtn.add(getBtnMettreAJour());
			conteneurBtn.add(getBtnModifier());
			conteneurBtn.add(getBtnAnnuler());
			conteneurBtn.add(getBtnSupprimer());
			conteneurBtn.add(getBtnSuivant());
		}
		return conteneurBtn;
	}
	
	//************** creation des boutons et modification des comportements *************
	//BOUTON PRECEDENT
	public JButton getBtnPrecedent() {
		if(btnPrecedent == null){
			btnPrecedent = new JButton("Précédent");
			btnPrecedent.setToolTipText("Enregistrement précédent");
			// couleur du texte
			btnPrecedent.setForeground(Color.BLUE);
			// police, style, taille
			btnPrecedent.setFont(new Font("Serif", Font.BOLD, 12));
			// apparence du curseur
			btnPrecedent.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnPrecedent.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni_ecole/cours/ihm/resources/precedent.png")));
			btnPrecedent.setRolloverIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni_ecole/cours/ihm/resources/prec.png")));

			btnPrecedent.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// sur click du bouton precedent affichage de l'eleve precedent 
					if (indexListeEleve > 0){
						indexListeEleve--;
						Eleve elevePrecedent = eleveManager.getEleve(indexListeEleve);
						getConteneurEleve().affiche(elevePrecedent);
					} else {
						//affichage du dernier eleve du catalogue
						indexListeEleve = eleveManager.getEleves().size()-1;
						Eleve elevePrecedent = eleveManager.getEleve(indexListeEleve);
						getConteneurEleve().affiche(elevePrecedent);
					}
					index= indexListeEleve;
				}
			});
		}
		return btnPrecedent;
	}

	//BOUTON SUIVANT
	public JButton getBtnSuivant() {
		if(btnSuivant == null){
			btnSuivant = new JButton("Suivant");
			btnSuivant.setToolTipText("enregistrement suivant");
			btnSuivant.setForeground(Color.BLUE);
			btnSuivant.setFont(new Font("Serif", Font.BOLD, 12));
			btnSuivant.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnSuivant.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni_ecole/cours/ihm/resources/suivant.png")));
			btnSuivant.setRolloverIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni_ecole/cours/ihm/resources/suiv.png")));
			btnSuivant.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// sur click du bouton suivant affichage de l'eleve suivant du catalogue
					if (indexListeEleve < eleveManager.getEleves().size()-1){
						indexListeEleve++;
						Eleve eleveSuivant = eleveManager.getEleve(indexListeEleve);
						getConteneurEleve().affiche(eleveSuivant);
					} else {
						indexListeEleve=0;
						Eleve eleveSuivant = eleveManager.getEleve(indexListeEleve);
						getConteneurEleve().affiche(eleveSuivant);
					}
					index= indexListeEleve;
				}
			});
		}
		return btnSuivant;
	}
	
	//BOUTON NOUVEAU
	public JButton getBtnNouveau() {
		if(btnNouveau == null){
			btnNouveau = new JButton("Nouveau");
			btnNouveau.setToolTipText("Ajouter un nouvel élève");
			btnNouveau.setForeground(Color.BLUE);
			btnNouveau.setFont(new Font("Serif", Font.BOLD, 12));
			btnNouveau.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnNouveau.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni_ecole/cours/ihm/resources/ajouter.png")));
			btnNouveau.setRolloverIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni_ecole/cours/ihm/resources/add.png")));
			btnNouveau.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//gestion de la visibilité des boutons 
					btnPrecedent.setVisible(false);
					btnNouveau.setVisible(false);
					btnAjouter.setVisible(true);
					btnMettreAJour.setVisible(false);
					btnModifier.setVisible(false);
					btnAnnuler.setVisible(true);
					btnSupprimer.setVisible(false);
					btnSuivant.setVisible(false);
					//affichage du formulaire
					indexListeEleve= eleveManager.getEleves().size();
					getConteneurEleve().afficheNouveau();
				}
			});
		}
		return btnNouveau;
	}

	//BOUTON AJOUTER
	public JButton getBtnAjouter(){
		if (btnAjouter== null){
			btnAjouter = new JButton("Ajouter");
			btnAjouter.setToolTipText("Valider l'ajout de l'élève");
			btnAjouter.setForeground(Color.BLUE);
			btnAjouter.setFont(new Font("Serif", Font.BOLD, 12));
			btnAjouter.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnAjouter.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni_ecole/cours/ihm/resources/valider.png")));
			btnAjouter.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//gestion de la visibilité des boutons : etat initial
					btnPrecedent.setVisible(true);
					btnNouveau.setVisible(true);
					btnAjouter.setVisible(false);
					btnMettreAJour.setVisible(true);
					btnModifier.setVisible(false);
					btnAnnuler.setVisible(false);
					btnSupprimer.setVisible(true);
					btnSuivant.setVisible(true);
					//ajout de l'élève
					Eleve eleveAjouter = getConteneurEleve().getEleve();
					try {
						eleveManager.addEleve(eleveAjouter);
						getConteneurEleve().affiche(eleveManager.getEleve(indexListeEleve));
					} catch (BLLException | DALException e){
						JOptionPane.showMessageDialog(FenetrePrincipale4.this, "une erreur est survenue lors de l'ajout" + e.getMessage(), "ERREUR INSERTION ELEVE", JOptionPane.ERROR_MESSAGE);
						getConteneurEleve().affiche(eleveManager.getEleve(indexListeEleve-1));
					}					
				}
			});
		}
		return btnAjouter;
	}

	//BOUTON ANNULER
	public JButton getBtnAnnuler(){
		if (btnAnnuler== null){
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.setToolTipText("Annuler l'opération");
			btnAnnuler.setForeground(Color.BLUE);
			btnAnnuler.setFont(new Font("Serif", Font.BOLD, 12));
			btnAnnuler.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnAnnuler.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni_ecole/cours/ihm/resources/annuler.png")));
			btnAnnuler.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//gestion de la visibilité des boutons 
					btnPrecedent.setVisible(true);
					btnNouveau.setVisible(true);
					btnAjouter.setVisible(false);
					btnMettreAJour.setVisible(true);
					btnModifier.setVisible(false);
					btnAnnuler.setVisible(false);
					btnSupprimer.setVisible(true);
					btnSuivant.setVisible(true);
					//activation des champs de saisie 
					getConteneurEleve().activeTexte();
					//affichage de l'élève courant avant ajout ou modification
					getConteneurEleve().affiche(eleveManager.getEleve(index));
				}
			});
		}
		return btnAnnuler;
	}
	
	
	
	//BOUTON METTRE A JOUR
	public JButton getBtnMettreAJour() {
		if(btnMettreAJour == null){
			btnMettreAJour = new JButton("Mettre à jour");
			btnMettreAJour.setToolTipText("modifier l'adresse d'un élève");
			btnMettreAJour.setForeground(Color.BLUE);
			btnMettreAJour.setFont(new Font("Serif", Font.BOLD, 12));
			btnMettreAJour.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnMettreAJour.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni_ecole/cours/ihm/resources/modifier.png")));
			btnMettreAJour.setRolloverIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni_ecole/cours/ihm/resources/modif.png")));
			btnMettreAJour.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//gestion de la visibilité des boutons 
					btnPrecedent.setVisible(false);
					btnNouveau.setVisible(false);
					btnAjouter.setVisible(false);
					btnMettreAJour.setVisible(false);
					btnModifier.setVisible(true);
					btnAnnuler.setVisible(true);
					btnSupprimer.setVisible(false);
					btnSuivant.setVisible(false);
					// desactivation des champs de saisie non modifiables
					getConteneurEleve().inactiveTexte();
				}
			});
		}
		return btnMettreAJour;
	}

	//BOUTON MODIFIER
	public JButton getBtnModifier() {
		if(btnModifier == null){
			btnModifier = new JButton("Modifier");
			btnModifier.setToolTipText("Valider la mise à jour de l'adresse d'un élève");
			btnModifier.setForeground(Color.BLUE);
			btnModifier.setFont(new Font("Serif", Font.BOLD, 12));
			btnModifier.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnModifier.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni_ecole/cours/ihm/resources/valider.png")));
			btnModifier.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//gestion de la visibilité des boutons 
					btnPrecedent.setVisible(true);
					btnNouveau.setVisible(true);
					btnAjouter.setVisible(false);
					btnMettreAJour.setVisible(true);
					btnModifier.setVisible(false);
					btnAnnuler.setVisible(false);
					btnSupprimer.setVisible(true);
					btnSuivant.setVisible(true);
					//activation des champs de saisie 
					getConteneurEleve().activeTexte();
					// modifier le champs adresse de l'élève uniquement
					Eleve eleveModif = getConteneurEleve().getEleve();
					try {
						eleveManager.majEleve(indexListeEleve, eleveModif);
						getConteneurEleve().affiche(eleveManager.getEleve(indexListeEleve));
					} catch (BLLException | DALException e){
						JOptionPane.showMessageDialog(FenetrePrincipale4.this, "une erreur est survenue lors de la modification"+e.getMessage(),"ERREUR MODIFICATION ELEVE", JOptionPane.ERROR_MESSAGE);
						getConteneurEleve().affiche(eleveManager.getEleve(indexListeEleve));
					}
				}
			});
		}
		return btnModifier;
	}
	
	//BOUTON SUPPRIMER
	public JButton getBtnSupprimer() {
		if(btnSupprimer == null){
			btnSupprimer = new JButton("Supprimer");
			btnSupprimer.setToolTipText("supprimer un élève");
			btnSupprimer.setForeground(Color.BLUE);
			btnSupprimer.setFont(new Font("Serif", Font.BOLD, 12));
			btnSupprimer.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnSupprimer.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni_ecole/cours/ihm/resources/supprimer.png")));
			btnSupprimer.setRolloverIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni_ecole/cours/ihm/resources/supp.png")));
			btnSupprimer.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						eleveManager.removeEleve(indexListeEleve);
						if(indexListeEleve == 0){
							getConteneurEleve().affiche(eleveManager.getEleve(0));
						} else {
							getConteneurEleve().affiche(eleveManager.getEleve(indexListeEleve-1));
						}
					} catch (DALException e) {
						JOptionPane.showMessageDialog(FenetrePrincipale4.this, "une erreur est survenue lors de la suppression"+e.getMessage(), "ERREUR SUPPRESSION ELEVE",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return btnSupprimer;
	}

	
//************** creation du menu et modification des comportements *************

	public JMenuBar getBarreMenu(){
		if(barreMenus == null){
			barreMenus = new JMenuBar();
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
		return barreMenus;
	}


@Override
public void menuCanceled(MenuEvent arg0) {
}


@Override
public void menuDeselected(MenuEvent arg0) {
}


@Override
public void menuSelected(MenuEvent arg0) {
	//activation - desactivation des options
	if (fichierOuvert) copier.setEnabled(true); 
	else copier.setEnabled(fichierOuvert);
	coller.setEnabled(infos);
	ouvrir.setEnabled(true); 
	sauvegarder.setEnabled(fichierOuvert);
	fermer.setEnabled(fichierOuvert);
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
	
	
}// fin de la classe

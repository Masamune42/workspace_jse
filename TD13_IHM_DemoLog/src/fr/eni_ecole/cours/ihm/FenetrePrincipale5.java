package fr.eni_ecole.cours.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import fr.eni_ecole.cours.bll.BLLException;
import fr.eni_ecole.cours.bll.EleveManager;
import fr.eni_ecole.cours.bo.Eleve;
import fr.eni_ecole.cours.dal.DALException;

@SuppressWarnings("serial")
public class FenetrePrincipale5 extends JFrame implements ActionListener, MenuListener{

	//declaration des variables et composants
	private PanelEleve56 panelEleve;
	private JPanel conteneurBtn, conteneurTitre;
	private JButton btnNouveau,btnMettreAJour,btnSupprimer,btnPrecedent,btnSuivant;
	private JButton btnAjouter, btnModifier, btnAnnuler;
	private JLabel lblTitre;

	private JMenuBar barreMenus;
	private JMenu fichier, edition;
	private JMenuItem ouvrir, sauvegarder, fermer, copier, coller;
	private boolean fichierOuvert, infos;
	private String nomFichier;
	
	//variables de gestion
	private EleveManager eleveManager;
	private int indexListeEleve = 0, index=0;

	
	public FenetrePrincipale5(){
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
		setResizable(false);
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
			initVisibiliteBtn();
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
		getContentPane().add(getConteneurTitre(), BorderLayout.NORTH);
		getContentPane().add(getConteneurEleve(),BorderLayout.CENTER);
		getContentPane().add(getConteneurBtn(),BorderLayout.SOUTH);
		//ou :
/*		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx =0;
		gbc.gridy =0;
		//ajout du conteneur intermediaire ConteneurEleve au ContentPane
		getContentPane().add(getConteneurEleve(),gbc);
		gbc.gridx=0;
		gbc.gridy =1;
		//ajout du conteneur intermediaire ConteneurBouton au ContentPane
		getContentPane().add(getConteneurBtn(),gbc);
*/	}

	
	//*** Nous choisissons de déléguer la création des instances des composants   ***
	//*** graphiques aux méthodes get. Cela permet de ne créer l’instance que si  ***
	//*** nécessaire, et permet aussi de bien ordonner le code. Cette méthode est ***
	//*** nommée Lazy instanciation												  ***

	//************** creation des conteneurs intermediaires *************
	
	public JPanel getConteneurTitre(){
		if (conteneurTitre == null){
			//creation du conteneur intermédiaire regroupant le titre
			conteneurTitre = new JPanel(new FlowLayout());
			//ajout du titre au conteneur intermediaire
			conteneurTitre.add(getTitre());
		}
		return conteneurTitre;
	}

	public PanelEleve56 getConteneurEleve(){
		if (panelEleve == null){
			//creation du conteneur intermédiaire regroupant les données
			panelEleve = new PanelEleve56();
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
	
	public JLabel getTitre() {
		if (lblTitre == null){
			lblTitre= new JLabel("GESTION DES ELEVES");
			lblTitre.setFont(new Font("Serif", Font.BOLD, 20));
			lblTitre.setForeground(new Color(80, 80, 80));
		}
		return lblTitre;
	}
	
	//au lieu de creer les xxx ecouteurs sur les cinq boutons, on crée un ecouteur unique
	EcouteurEleve ecouteur = new EcouteurEleve();
	
	//BOUTON PRECEDENT
	public JButton getBtnPrecedent() {
		if(btnPrecedent == null){
			btnPrecedent = implementationBtn(btnPrecedent,"Précédent", "Enregistrement précédent", "precedent.png", "prec.png","precedent");
		}
		return btnPrecedent;
	}

	//BOUTON SUIVANT
	public JButton getBtnSuivant() {
		if(btnSuivant == null){
			btnSuivant = implementationBtn(btnSuivant,"Suivant", "Enregistrement suivant", "suivant.png", "suiv.png","suivant");
		}
		return btnSuivant;
	}
	
	//BOUTON NOUVEAU
	public JButton getBtnNouveau() {
		if(btnNouveau == null){
			btnNouveau = implementationBtn(btnNouveau,"Nouveau", "Ajouter un(e) nouvel(le) élève", "ajouter.png", "add.png","nouveau");
		}
		return btnNouveau;
	}

	//BOUTON AJOUTER
	public JButton getBtnAjouter(){
		if (btnAjouter== null){
			btnAjouter = implementationBtn(btnAjouter,"Ajouter", "Valider l'ajout de l'élève", "valider.png", "","ajouter");
		}
		return btnAjouter;
	}

	//BOUTON ANNULER
	public JButton getBtnAnnuler(){
		if (btnAnnuler== null){
			btnAnnuler = implementationBtn(btnAnnuler,"Annuler", "Annuler l'opération", "annuler.png", "","annuler");
		}
		return btnAnnuler;
	}
	
	//BOUTON METTRE A JOUR
	public JButton getBtnMettreAJour() {
		if(btnMettreAJour == null){
			btnMettreAJour = implementationBtn(btnMettreAJour,"Mettre à jour", "Modifier l'adresse d'un élève", "modifier.png", "modif.png","mettreAJour");
		}
		return btnMettreAJour;
	}

	//BOUTON MODIFIER
	public JButton getBtnModifier() {
		if(btnModifier == null){
			btnModifier = implementationBtn(btnModifier,"Modifier", "Valider la mise à jour de l'adresse de l'élève", "valider.png", "","modifier");
		}
		return btnModifier;
	}
	
	//BOUTON SUPPRIMER
	public JButton getBtnSupprimer() {
		if(btnSupprimer == null){
			btnSupprimer = implementationBtn(btnSupprimer,"Supprimer", "supprimer un élève", "supprimer.png", "supp.png","supprimer");
		}
		return btnSupprimer;
	}

//************************ methodes diverses ****************	
	
	/**
	 * implementation des boutons
	 * @param nom : le nom de l'objet de type JButton
	 * @param libelle : le libelle du bouton
	 * @param info : le message dans l'info-bulle du bouton
	 * @param imgIcone : l'image sur le bouton (icone visible)
	 * @param imgOverIcone : l'image sur le bouton au passage de la souris (icone over)
	 * @param action : le nom de l'action
	 */
	public JButton implementationBtn(JButton nom, String libelle, String info, String imgIcone, String imgOverIcone, String action) {
		nom = new JButton(libelle);
		nom.setToolTipText(info);
		nom.setForeground(Color.BLUE);
		nom.setFont(new Font("Serif", Font.BOLD, 12));
		nom.setCursor(new Cursor(Cursor.HAND_CURSOR));
		nom.setIcon(new ImageIcon(FenetrePrincipale5.class.getResource("/fr/eni_ecole/cours/ihm/resources/"+imgIcone)));
		if (!imgOverIcone.equals(""))
			nom.setRolloverIcon(new ImageIcon(FenetrePrincipale5.class.getResource("/fr/eni_ecole/cours/ihm/resources/"+imgOverIcone)));
		nom.setActionCommand(action);
		nom.addActionListener(ecouteur);
		return nom;
	}
	
	/**
	 * methode permettant de mettre la barre de boutons à l'état initial
	 */
	public void initVisibiliteBtn(){
		btnPrecedent.setVisible(true);
		btnNouveau.setVisible(true);
		btnAjouter.setVisible(false);
		btnMettreAJour.setVisible(true);
		btnModifier.setVisible(false);
		btnAnnuler.setVisible(false);
		btnSupprimer.setVisible(true);
		btnSuivant.setVisible(true);
	}
	

//******************** ecouteur unique sur les tous les boutons ********************	
	
	/**
	 * Ecouteur pour les actions de clics sur les boutons de maj sur l'élève
	 * @author tlargeau
	 *
	 */
	public class EcouteurEleve implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			
			String commande;
			commande = arg0.getActionCommand();
			
			if (commande.equals("precedent")){
				conteneurBtn.setBackground(new Color(80, 80, 255));
				lblTitre.setForeground(new Color(80, 80, 255));
				// sur click du bouton precedent affichage de l'eleve precedent 
				if (indexListeEleve > 0){
					indexListeEleve--;
				} else {
					//affichage du dernier eleve du catalogue
					indexListeEleve = eleveManager.getEleves().size()-1;
				}
				Eleve elevePrecedent = eleveManager.getEleve(indexListeEleve);
				getConteneurEleve().affiche(elevePrecedent);
				index= indexListeEleve;

			}
			if (commande.equals("suivant")){
				conteneurBtn.setBackground(new Color(80, 80, 255));
				lblTitre.setForeground(new Color(80, 80, 255));
				// sur click du bouton suivant affichage de l'eleve suivant du catalogue
				if (indexListeEleve < eleveManager.getEleves().size()-1){
					indexListeEleve++;
				} else {
					indexListeEleve=0;
				}
				Eleve eleveSuivant = eleveManager.getEleve(indexListeEleve);
				getConteneurEleve().affiche(eleveSuivant);
				index= indexListeEleve;
			}
			if (commande.equals("nouveau")){
				conteneurBtn.setBackground(new Color(80, 255, 80));
				lblTitre.setForeground(new Color(80, 255, 80));
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
			if (commande.equals("ajouter")){
				conteneurBtn.setBackground(new Color(80, 255, 80));
				lblTitre.setForeground(new Color(80, 255, 80));
				//gestion de la visibilité des boutons : etat initial
				initVisibiliteBtn();
				//ajout de l'élève
				Eleve eleveAjouter = getConteneurEleve().getEleve();
				try {
					eleveManager.addEleve(eleveAjouter);
					getConteneurEleve().affiche(eleveManager.getEleve(indexListeEleve));
				} catch (BLLException | DALException e){
					JOptionPane.showMessageDialog(FenetrePrincipale5.this, "une erreur est survenue lors de l'ajout" + e.getMessage(), "ERREUR INSERTION ELEVE", JOptionPane.ERROR_MESSAGE);
					getConteneurEleve().affiche(eleveManager.getEleve(indexListeEleve-1));
				}
			}
			if (commande.equals("annuler")){
				conteneurBtn.setBackground(new Color(80, 80, 80));
				lblTitre.setForeground(new Color(80, 80, 80));
				//gestion de la visibilité des boutons : etat initial
				initVisibiliteBtn();
				//activation des champs de saisie 
				getConteneurEleve().activeTexte();
				//affichage de l'élève courant avant ajout ou modification
				getConteneurEleve().affiche(eleveManager.getEleve(index));
			}
			if (commande.equals("mettreAJour")){
				conteneurBtn.setBackground(new Color(80, 255, 80));
				lblTitre.setForeground(new Color(80, 255, 80));
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
			if (commande.equals("modifier")){
				conteneurBtn.setBackground(new Color(80, 255, 80));
				lblTitre.setForeground(new Color(80, 255, 80));
				//gestion de la visibilité des boutons : etat initial
				initVisibiliteBtn();
				//activation des champs de saisie 
				getConteneurEleve().activeTexte();
				// modifier le champs adresse de l'élève uniquement
				Eleve eleveModif = getConteneurEleve().getEleve();
				try {
					eleveManager.majEleve(indexListeEleve, eleveModif);
				} catch (BLLException | DALException e){
					JOptionPane.showMessageDialog(FenetrePrincipale5.this, "une erreur est survenue lors de la modification"+e.getMessage(),"ERREUR MODIFICATION ELEVE", JOptionPane.ERROR_MESSAGE);
				} finally {
					getConteneurEleve().affiche(eleveManager.getEleve(indexListeEleve));
				}
			}
			if (commande.equals("supprimer")){
				conteneurBtn.setBackground(new Color(255, 80, 80));
				lblTitre.setForeground(new Color(255, 80, 80));
				int reponse=JOptionPane.showConfirmDialog(FenetrePrincipale5.this, "Confirmer la suppression de l'élève ?", "SUPPRESSION DE L'ELEVE", JOptionPane.YES_NO_OPTION);
				if (reponse== JOptionPane.YES_OPTION){
					try {
						eleveManager.removeEleve(indexListeEleve);
						if(indexListeEleve == 0){
							getConteneurEleve().affiche(eleveManager.getEleve(0));
						} else {
							getConteneurEleve().affiche(eleveManager.getEleve(indexListeEleve-1));
						}
					} catch (DALException e) {
						JOptionPane.showMessageDialog(FenetrePrincipale5.this, "une erreur est survenue lors de la suppression"+e.getMessage(), "ERREUR SUPPRESSION ELEVE",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		}
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
			fichier.add(new JSeparator());
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
	
}

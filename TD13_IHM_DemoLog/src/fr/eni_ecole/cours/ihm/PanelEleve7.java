package fr.eni_ecole.cours.ihm;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.eni_ecole.cours.bll.BLLException;
import fr.eni_ecole.cours.bll.EleveManager;
import fr.eni_ecole.cours.bo.Eleve;
import fr.eni_ecole.cours.dal.DALException;
import fr.eni_ecole.cours.ihm.catalogue.TableCatalogue;

public class PanelEleve7 extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel lblNom, lblPrenom,lblAdresse;
	private JTextField txtNom, txtPrenom, txtAdresse;
	private JPanel conteneurBtn;
	private JButton btnNouveau,btnMettreAJour,btnSupprimer,btnPrecedent,btnSuivant;
	private JButton btnAjouter, btnModifier, btnAnnuler;
	//variables de gestion
	private EleveManager eleveManager;
	private int indexListeEleve = 0, index=0;
	private int aig = 0;

	protected static Logger logger;
	
	public PanelEleve7(){
		//creation du journal
		logger=Logger.getLogger("fr.eni_ecole.cours.ihm.FenetrePrincipale7");
		try {
			//fichier de log
			//- (String) le nom du fichier peut contenir des caractères spéciaux définissant un motif (pattern)
			//- (int) une taille limite pour le fichier (exprimée en octets, infini par défaut)
			//- (int) le nombre de fichier cyclique (1 par defaut)
			//- (boolean) un mode d'appel append (true ou false)
		
			// le journal est divisé en 5 fichier de 10000 octets. Leur nom sera en logAppEleve.log.i de 0 à 4
			// %t : repertoire temporaire du systeme
			// %g : le nombre genere automatiquement par la rotation cyclique des fichiers
			Handler fh = new FileHandler("logAppEleve.%g.log",100000,4, false);
			logger.addHandler(fh);
			//formattage en texte simple
			fh.setFormatter(new SimpleFormatter());
			//log debut d'application
			logger.log(Level.INFO, "lancement de l'application - logger - INFO.");
		
			//chargement du catalogue EleveManager
			eleveManager= EleveManager.getInstance();
			

/* ajout des onglets */
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
		onglets.setPreferredSize(new Dimension(700, 300));
		
/* onglet 1 */
		JPanel onglet1 =new JPanel();
		onglet1.setPreferredSize(new Dimension(700, 300));
		//changement du layout
		onglet1.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		//affichage du premier eleve du catalogue
		//ou un ecran vide si aucun éléve
		Eleve eleveCourant;
		if (eleveManager.getEleves().size()>0){
			eleveCourant=eleveManager.getEleve(0);
			affiche(eleveCourant);
			//gestion de la visibilité des boutons : etat initial
			//initVisibiliteBtn();
		}else {
			afficheNouveau();
		}
		//ligne du nom - position dans la case 0,0
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		onglet1.add(getLblNom(),gbc);
		gbc.gridx=1;
		gbc.gridy=1;
		//sur 1 ligne de hauteur
		gbc.gridheight=1;
		onglet1.add(getTxtNom(),gbc);
		//ligne du prénom
		gbc.gridx=0;
		gbc.gridy=2;
		//sur 1 ligne de hauteur
		gbc.gridheight=1;
		onglet1.add(getLblPrenom(),gbc);
		gbc.gridx=1;
		gbc.gridy=2;
		//sur 1 ligne de hauteur
		gbc.gridheight=1;
		onglet1.add(getTxtPrenom(),gbc);
		//ligne de l'adresse
		gbc.gridx=0;
		gbc.gridy=3;
		//sur 1 ligne de hauteur
		gbc.gridheight=1;
		onglet1.add(getLblAdresse(),gbc);
		gbc.gridx=1;
		gbc.gridy=3;
		//sur 1 ligne de hauteur
		gbc.gridheight=1;
		onglet1.add(getTxtAdresse(),gbc);
		//ligne de la date de naissance
		gbc.gridx=0;
		gbc.gridy=7;
		gbc.gridwidth=2;
		//sur 1 ligne de hauteur
		gbc.gridheight=4;
		onglet1.add(getConteneurBtn(),gbc);
		initVisibiliteBtn();
		
		//ajout de l'onglets 1 aux onglets
		onglets.addTab("Gestion des élèves", onglet1);

/* onglet 2 */
		JPanel onglet2 =new JPanel();
		onglet2.setPreferredSize(new Dimension(500, 300));
		//JLabel titreOnglet2 = new JLabel("Liste des Eleves");
		//onglet2.add(titreOnglet2);
		onglet2.setLayout(new GridLayout(1, 0));
		//chargement du tableau avec barre de defilement
		//EleveManager manager;
//		try {
//			manager = EleveManager.getInstance();
			TableCatalogue tableauCatalogue = new TableCatalogue(eleveManager.getEleves());
			JScrollPane scrollPane = new JScrollPane(tableauCatalogue);
			onglet2.add(scrollPane);
//		} catch (BLLException e) {
//			e.printStackTrace();
//		}
		//ajout de l'onglet 2 aux onglets
		onglets.addTab("Liste des élèves", onglet2);
	
		 //Tant que j'y suis, on peut passer ce nouvel onglet au premier plan
        onglets.setSelectedComponent(onglet2);		
		//ajout des onglets au panel
		add(onglets);

		} catch (BLLException e) {
			JOptionPane.showMessageDialog(this, "une erreur survenue au chargement du catalogue");
			//message à journaliser : niveau le plus élevé
			logger.log(Level.SEVERE, "une erreur est survenue au chargement du catalogue"+e.getMessage()+" - logger - ERROR.");
			System.exit(1);
		} catch (SecurityException e) {
			logger.log(Level.WARNING, "Probleme de securité sur le logger"+e.getMessage()+" - logger - WARNING.");
		} catch (IOException e) {
			logger.log(Level.WARNING, "Probleme d'ouverture du logger"+e.getMessage()+" - logger - WARNING.");
		}
		
		
/* fin ajout */
	}

//************** fonctions  *************

		public void affiche(Eleve unEleve){
			//chargement des données dans le formulaire
			getTxtNom().setText(unEleve.getNom());
			getTxtPrenom().setText(unEleve.getPrenom());
			getTxtAdresse().setText(unEleve.getAdresse());
		}
		
		public void afficheNouveau(){
			Eleve nouveau = new Eleve("", "", "");
			affiche(nouveau);
		}

		public Eleve getEleve(){
			Eleve eleve=new Eleve();
			try{
			eleve.setNom(getTxtNom().getText());
			eleve.setPrenom(getTxtPrenom().getText());
			eleve.setAdresse(getTxtAdresse().getText());
			}catch (Exception e){
				e.printStackTrace();
			}
			return eleve;
		}

		public void activeTexte(){
			getTxtNom().setEnabled(true);
			getTxtPrenom().setEnabled(true);
		}
		
		public void inactiveTexte(){
			getTxtNom().setEnabled(false);
			getTxtPrenom().setEnabled(false);
		}

		//************** creation des conteneurs intermediaires *************
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
		
		
//************** creation des composants et modification des comportements *************
	
		public JLabel getLblNom(){
		if(lblNom == null){
			lblNom = new JLabel("Nom : ");
			lblNom.setPreferredSize(new Dimension(150,30));
		}
		return lblNom;
	}
	
	public JTextField getTxtNom(){
		if(txtNom == null){
			txtNom = new JTextField(20);
			//placer le focus sur le composant 
			txtNom.requestFocus();
		}
		return txtNom;
	}
	
	public JLabel getLblPrenom(){
		if(lblPrenom == null){
			lblPrenom = new JLabel("Prenom : ");
			lblPrenom.setPreferredSize(new Dimension(150,30));
		}
		return lblPrenom;
	}
	
	public JTextField getTxtPrenom(){
		if(txtPrenom == null){
			txtPrenom = new JTextField(20);
		}
		return txtPrenom;
	}

	public JLabel getLblAdresse(){
		if(lblAdresse == null){
			lblAdresse = new JLabel("Adresse : ");
			lblAdresse.setPreferredSize(new Dimension(150,30));
		}
		return lblAdresse;
	}
	
	public JTextField getTxtAdresse(){
		if(txtAdresse == null){
			txtAdresse = new JTextField(20);
		}
		return txtAdresse;
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
		nom.setIcon(new ImageIcon(FenetrePrincipale7.class.getResource("/fr/eni_ecole/cours/ihm/resources/"+imgIcone)));
		if (!imgOverIcone.equals(""))
			nom.setRolloverIcon(new ImageIcon(FenetrePrincipale7.class.getResource("/fr/eni_ecole/cours/ihm/resources/"+imgOverIcone)));
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
//					conteneurBtn.setBackground(new Color(80, 80, 255));
//					lblTitre.setForeground(new Color(80, 80, 255));
					// sur click du bouton precedent affichage de l'eleve precedent 
					if (indexListeEleve > 0){
						indexListeEleve--;
					} else {
						//affichage du dernier eleve du catalogue
						indexListeEleve = eleveManager.getEleves().size()-1;
					}
					Eleve elevePrecedent = eleveManager.getEleve(indexListeEleve);
					affiche(elevePrecedent);
					index= indexListeEleve;
				}
				if (commande.equals("suivant")){
//					conteneurBtn.setBackground(new Color(80, 80, 255));
//					lblTitre.setForeground(new Color(80, 80, 255));
					// sur click du bouton suivant affichage de l'eleve suivant du catalogue
					if (indexListeEleve < eleveManager.getEleves().size()-1){
						indexListeEleve++;
					} else {
						indexListeEleve=0;
					}
					Eleve eleveSuivant = eleveManager.getEleve(indexListeEleve);
					affiche(eleveSuivant);
					index= indexListeEleve;
				}
				if (commande.equals("nouveau")){
					aig=1;
//					conteneurBtn.setBackground(new Color(80, 255, 80));
//					lblTitre.setForeground(new Color(80, 255, 80));
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
					afficheNouveau();
				}
				if (commande.equals("ajouter")){
//					conteneurBtn.setBackground(new Color(80, 255, 80));
//					lblTitre.setForeground(new Color(80, 255, 80));
					//gestion de la visibilité des boutons : etat initial
					initVisibiliteBtn();
					//ajout de l'élève
					Eleve eleveAjouter = getEleve();
					try {
						eleveManager.addEleve(eleveAjouter);
						affiche(eleveManager.getEleve(indexListeEleve));
						logger.log(Level.INFO, "Insertion d'un(e) élève - "+eleveManager.getEleve(indexListeEleve).toString() +" - logger - INFO.");
						index=indexListeEleve;
					} catch (BLLException | DALException e){
						JOptionPane.showMessageDialog(PanelEleve7.this, "une erreur est survenue lors de l'ajout - " + e.getMessage(), "ERREUR INSERTION ELEVE", JOptionPane.WARNING_MESSAGE);
						logger.log(Level.WARNING, "une erreur est survenue lors de l'ajout d'un(e) élève - "+e.getMessage()+" - logger - WARNING.");
						indexListeEleve--;
						affiche(eleveManager.getEleve(indexListeEleve));
					}
				}
				if (commande.equals("annuler")){
//					conteneurBtn.setBackground(new Color(80, 80, 80));
//					lblTitre.setForeground(new Color(80, 80, 80));
					//gestion de la visibilité des boutons : etat initial
					initVisibiliteBtn();
					//activation des champs de saisie 
					activeTexte();
					//affichage de l'élève courant avant ajout ou modification
					affiche(eleveManager.getEleve(index));
					if (aig==1) {
						if (index==0) indexListeEleve=0;
						else indexListeEleve--;
					}
				}
				if (commande.equals("mettreAJour")){
//					conteneurBtn.setBackground(new Color(80, 255, 80));
//					lblTitre.setForeground(new Color(80, 255, 80));
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
					inactiveTexte();
				}
				if (commande.equals("modifier")){
//					conteneurBtn.setBackground(new Color(80, 255, 80));
//					lblTitre.setForeground(new Color(80, 255, 80));
					//gestion de la visibilité des boutons : etat initial
					initVisibiliteBtn();
					//activation des champs de saisie 
					activeTexte();
					// modifier le champs adresse de l'élève uniquement
					Eleve eleveModif = getEleve();
					try {
						eleveManager.majEleve(indexListeEleve, eleveModif);
						logger.log(Level.INFO, "Modification d'un(e) élève - "+eleveManager.getEleve(indexListeEleve).toString() +" - logger - INFO.");
					} catch (BLLException | DALException e){
						JOptionPane.showMessageDialog(PanelEleve7.this, "une erreur est survenue lors de la modification - "+e.getMessage(),"ERREUR MODIFICATION ELEVE", JOptionPane.WARNING_MESSAGE);
						logger.log(Level.WARNING, "une erreur est survenue lors de la modification d'un(e) élève - "+e.getMessage()+" - logger - WARNING.");
					} finally {
						affiche(eleveManager.getEleve(indexListeEleve));
					}
				}
				if (commande.equals("supprimer")){
//					conteneurBtn.setBackground(new Color(255, 80, 80));
//					lblTitre.setForeground(new Color(255, 80, 80));
					int reponse=JOptionPane.showConfirmDialog(PanelEleve7.this, "Confirmer la suppression de l'élève ?", "SUPPRESSION DE L'ELEVE", JOptionPane.YES_NO_OPTION);
					if (reponse== JOptionPane.YES_OPTION){
						try {
							Eleve unEleve = eleveManager.getEleve(indexListeEleve);
							eleveManager.removeEleve(indexListeEleve);
							logger.log(Level.INFO, "Supprimer d'un(e) élève - "+unEleve.toString() +" - logger - INFO.");
							if(indexListeEleve == 0){
								affiche(eleveManager.getEleve(0));
							} else {
								index--;
								indexListeEleve--;
								affiche(eleveManager.getEleve(indexListeEleve));
							}
						} catch (DALException e) {
							JOptionPane.showMessageDialog(PanelEleve7.this, "une erreur est survenue lors de la suppression - "+e.getMessage(), "ERREUR SUPPRESSION ELEVE",JOptionPane.WARNING_MESSAGE);
							logger.log(Level.WARNING, "une erreur est survenue lors de la suppression d'un(e) élève - "+e.getMessage()+" - logger - WARNING.");
						}
					}
				}
				
			}
		}


}

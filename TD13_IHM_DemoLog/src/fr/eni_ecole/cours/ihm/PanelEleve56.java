package fr.eni_ecole.cours.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.eni_ecole.cours.bll.BLLException;
import fr.eni_ecole.cours.bll.EleveManager;
import fr.eni_ecole.cours.bo.Eleve;
import fr.eni_ecole.cours.ihm.catalogue.TableCatalogue;

@SuppressWarnings("serial")
public class PanelEleve56 extends JPanel{

	private JLabel lblNom, lblPrenom,lblAdresse;
	private JTextField txtNom, txtPrenom, txtAdresse;
	
	public PanelEleve56(){
/* ajout des onglets */
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
/* onglet 1 */
		JPanel onglet1 =new JPanel();
		onglet1.setPreferredSize(new Dimension(700, 300));
		//changement du layout
		onglet1.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
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

		//ajout de l'onglets 1 aux onglets
		onglets.addTab("Gestion des élèves", onglet1);

/* onglet 2 */
		JPanel onglet2 =new JPanel();
		onglet2.setPreferredSize(new Dimension(500, 300));
		//JLabel titreOnglet2 = new JLabel("Liste des Eleves");
		//onglet2.add(titreOnglet2);
		onglet2.setLayout(new GridLayout(1, 0));
//TODO conteneur de bouton enabled a faire
		//FenetrePrincipale5 ecran  = new  FenetrePrincipale5();
		//ecran.initVisibiliteBtn();
		//chargement du tableau avec barre de defilement
		EleveManager manager;
		try {
			manager = EleveManager.getInstance();
			TableCatalogue tableauCatalogue = new TableCatalogue(manager.getEleves());
			JScrollPane scrollPane = new JScrollPane(tableauCatalogue);
			onglet2.add(scrollPane);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		//ajout de l'onglet 2 aux onglets
		onglets.addTab("Liste des élèves", onglet2);
	
		 //Tant que j'y suis, on peut passer ce nouvel onglet au premier plan
        onglets.setSelectedComponent(onglet2);		
		//ajout des onglets au panel
		add(onglets);

		
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
			txtAdresse = new JTextField(30);
		}
		return txtAdresse;
	}


}


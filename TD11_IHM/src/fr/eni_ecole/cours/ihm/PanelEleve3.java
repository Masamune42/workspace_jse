package fr.eni_ecole.cours.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni_ecole.cours.bo.Eleve;

@SuppressWarnings("serial")
public class PanelEleve3 extends JPanel{

	private JLabel lblNom, lblPrenom,lblAdresse,lblNele;
	private JTextField txtNom, txtPrenom, txtAdresse, txtNele;
//	private int idCourant;
	
	public PanelEleve3(){
		
		//recommandé par Oracle
		setOpaque(true);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		//ligne du nom - position dans la case 0,0
		gbc.gridx=0;
		gbc.gridy=0;
		//sur 1 colonne de largeur
		gbc.gridwidth =1;
		//sur 1 ligne de hauteur
		gbc.gridheight=1;
		add(getLblNom(),gbc);
		gbc.gridx=1;
		gbc.gridy=0;
		//sur 2 colonnes de largeur
		gbc.gridwidth =2;
		//sur 1 ligne de hauteur
		gbc.gridheight=1;
		add(getTxtNom(),gbc);
		//ligne du prénom
		gbc.gridx=0;
		gbc.gridy=1;
		//sur 1 colonne de largeur
		gbc.gridwidth =1;
		//sur 1 ligne de hauteur
		gbc.gridheight=1;
		add(getLblPrenom(),gbc);
		gbc.gridx=1;
		gbc.gridy=1;
		//sur 2 colonnes de largeur
		gbc.gridwidth =2;
		//sur 1 ligne de hauteur
		gbc.gridheight=1;
		add(getTxtPrenom(),gbc);
		//ligne de l'adresse
		gbc.gridx=0;
		gbc.gridy=2;
		//sur 1 colonne de largeur
		gbc.gridwidth =1;
		//sur 1 ligne de hauteur
		gbc.gridheight=1;
		add(getLblAdresse(),gbc);
		gbc.gridx=1;
		gbc.gridy=2;
		//sur 2 colonnes de largeur
		gbc.gridwidth =2;
		//sur 1 ligne de hauteur
		gbc.gridheight=1;
//		gbc.fill = GridBagConstraints.BOTH;
		add(getTxtAdresse(),gbc);
	}

//***phase2***
//************** fonctions  *************

		public void affiche(Eleve unEleve){
//			idCourant = unEleve.getId();
			//chargement des données dans le formulaire
			getTxtNom().setText(unEleve.getNom());
			getTxtPrenom().setText(unEleve.getPrenom());
			getTxtAdresse().setText(unEleve.getAdresse());
		}
		
		public void afficheNouveau(){
			Eleve nouveau = new Eleve("", "", "");
//			nouveau.setId(null);
			affiche(nouveau);
		}
		

//************

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

	public JLabel getLblNele(){
		if(lblNele == null){
			lblNele = new JLabel("Date de naissance : ");
			lblNele.setPreferredSize(new Dimension(150,30));
		}
		return lblNele;
	}
	
	public JTextField getTxtNele(){
		if(txtNele == null){
			txtNele = new JTextField(20);
		}
		return txtNele;
	}

}

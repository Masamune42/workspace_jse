package fr.eni_ecole.cours.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni_ecole.cours.bo.Eleve;

@SuppressWarnings("serial")
public class PanelEleve4 extends JPanel{

	private JLabel lblNom, lblPrenom,lblAdresse;
	private JTextField txtNom, txtPrenom, txtAdresse;
//	private int idCourant;
	
	public PanelEleve4(){
		
		//recommandé par Oracle
		setOpaque(true);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill=GridBagConstraints.HORIZONTAL;
		/********** ligne du nom *********/
		gbc.gridx=1;
		gbc.gridy=3;
		add(getLblNom(),gbc);
		gbc.gridx=2;
		gbc.gridy=3;
		add(getTxtNom(),gbc);
		/************ ligne du prénom ************/
		gbc.gridx=1;
		gbc.gridy=4;
		add(getLblPrenom(),gbc);
		gbc.gridx=2;
		gbc.gridy=4;
		add(getTxtPrenom(),gbc);
		/********** ligne de l'adresse ************/
		gbc.gridx=1;
		gbc.gridy=5;
		add(getLblAdresse(),gbc);
		gbc.gridx=2;
		gbc.gridy=5;
		add(getTxtAdresse(),gbc);
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
			txtAdresse = new JTextField(25);
		}
		return txtAdresse;
	}


}


package fr.eni.cours.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni.cours.bo.Eleve;

@SuppressWarnings("serial")
public class PanelEleve extends JPanel{

	// variables des composants
	private JLabel lblNom, lblPrenom, lblAdresse;
	private JTextField txtNom, txtPrenom, txtAdresse;
	
	
	public PanelEleve() {
		setOpaque(true);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill= GridBagConstraints.HORIZONTAL;
		/** ligne nom **/
		gbc.gridx =0;
		gbc.gridy = 0;
		add(getLblNom(),gbc);
		gbc.gridx =1;
		add(getTxtNom(),gbc);
		
		/** ligne prenom **/
		gbc.gridx =0;
		gbc.gridy = 1;
		add(getLblPrenom(),gbc);
		gbc.gridx =1;
		add(getTxtPrenom(),gbc);

		/** ligne adresse **/
		gbc.gridx =0;
		gbc.gridy = 2;
		add(getLblAdresse(),gbc);
		gbc.gridx =1;
		add(getTxtAdresse(),gbc);

	}
	
	/** METHODES **/
	
	public void afficher(Eleve eleve) {
		//charger le formulaire
		getTxtNom().setText(eleve.getNom());
		getTxtPrenom().setText(eleve.getPrenom());
		getTxtAdresse().setText(eleve.getAdresse());
	}
	
	public void afficher() {
		Eleve eleve = new Eleve("","","");
		afficher(eleve);
	}
	
	public Eleve getEleve() {
		Eleve eleve = new Eleve();
		eleve.setNom(getTxtNom().getText());
		eleve.setPrenom(getTxtPrenom().getText());
		eleve.setAdresse(getTxtAdresse().getText());
		return eleve;
	}
	
	/** lazy instanciation **/
	public JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel("Nom :");
			lblNom.setPreferredSize(new Dimension(150, 30));
		}
		return lblNom;
	}

	public JLabel getLblPrenom() {
		if (lblPrenom == null) {
			lblPrenom = new JLabel("Prenom :");
			lblPrenom.setPreferredSize(new Dimension(150, 30));
		}
		return lblPrenom;
	}



	public JLabel getLblAdresse() {
		if (lblAdresse == null) {
			lblAdresse = new JLabel("Adresse :");
			lblAdresse.setPreferredSize(new Dimension(150, 30));
		}
		return lblAdresse;
	}



	public JTextField getTxtNom() {
		if (txtNom == null) {
			txtNom = new JTextField(20);
			txtNom.requestFocus();
		}
		return txtNom;
	}



	public JTextField getTxtPrenom() {
		if (txtPrenom == null) {
			txtPrenom = new JTextField(20);
		}
		return txtPrenom;
	}



	public JTextField getTxtAdresse() {
		if (txtAdresse == null) {
			txtAdresse = new JTextField(25);
		}
		return txtAdresse;
	}

	



}














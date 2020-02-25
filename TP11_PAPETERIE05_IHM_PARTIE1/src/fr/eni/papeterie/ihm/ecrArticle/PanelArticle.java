package fr.eni.papeterie.ihm.ecrArticle;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

@SuppressWarnings("serial")
public class PanelArticle extends JPanel {

	private JLabel lblReference, lblMarque, lblDesignation, lblPrix, lblQteStock, lblTypeArticle,lblGrammage, lblCouleur;
	private JTextField txtReference, txtMarque, txtDesignation, txtPrix, txtQteStock;
	private JRadioButton radioRamette, radioStylo;
	private JCheckBox ck80, ck100;
	private JComboBox<String> cboCouleurs;

	private ButtonGroup typesArticlesGroup;
	
	private Integer idCourant;
	
	public PanelArticle() {


		setLayout(new GridBagLayout());

		//Ligne 1
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.insets = new Insets(2,5,2,5);
		add(getLblReference(), gbc);

		gbc.gridy = 0;
		gbc.gridx = 1;
		add(getTxtReference(), gbc);

		//Ligne 2
		gbc.gridy = 1;
		gbc.gridx = 0;
		add(getLblDesignation(), gbc);

		gbc.gridy = 1;
		gbc.gridx = 1;
		add(getTxtDesignation(), gbc);

		//Ligne 3
		gbc.gridy = 2;
		gbc.gridx = 0;
		add(getLblPrix(), gbc);

		gbc.gridy = 2;
		gbc.gridx = 1;
		add(getTxtPrix(), gbc);

		//Ligne 4
		gbc.gridy = 3;
		gbc.gridx = 0;
		add(getLblMarque(), gbc);

		gbc.gridy = 3;
		gbc.gridx = 1;
		add(getTxtMarque(), gbc);

		//Ligne 5
		gbc.gridy = 4;
		gbc.gridx = 0;
		add(getLblQteStock(), gbc);

		gbc.gridy = 4;
		gbc.gridx = 1;
		add(getTxtQteStock(), gbc);

		//Ligne 6
		gbc.gridy = 5;
		gbc.gridx = 0;
		add(getLblPrix(), gbc);

		gbc.gridy = 5;
		gbc.gridx = 1;
		add(getTxtPrix(), gbc);

		//Ligne 7
		gbc.gridy = 6;
		gbc.gridx = 0;
		add(getLblTypeArticle(), gbc);

		gbc.gridy = 6;
		gbc.gridx = 1;
		JPanel  typesArticlePanel = new JPanel();
		typesArticlePanel.setLayout(new BoxLayout(typesArticlePanel, BoxLayout.Y_AXIS));
		typesArticlesGroup = new ButtonGroup();
		typesArticlesGroup.add(getRadioRamette());
		typesArticlesGroup.add(getRadioStylo());
		typesArticlePanel.add(getRadioRamette());
		typesArticlePanel.add(getRadioStylo());
		add(typesArticlePanel, gbc);

		//Ligne 8
		gbc.gridy = 7;
		gbc.gridx = 0;
		add(getLblGrammage(), gbc);

		gbc.gridy = 7;
		gbc.gridx = 1;
		JPanel panelRamette = new JPanel();
		panelRamette.setLayout(new BoxLayout(panelRamette,BoxLayout.Y_AXIS));
		panelRamette.add(getCk80());
		panelRamette.add(getCk100());
		ButtonGroup rametteGroup = new ButtonGroup();
		rametteGroup.add(getCk80());
		rametteGroup.add(getCk100());
		add(panelRamette, gbc);

		//Ligne 9
		gbc.gridy = 8;
		gbc.gridx = 0;
		add(getLblCouleur(), gbc);

		gbc.gridy = 8;
		gbc.gridx = 1;
		add(getCboCouleurs(), gbc);

	}
	
	public Article getArticle() {
		Article article=null;
		if(getRadioStylo().isSelected()){
			article = new Stylo();
		}else{
			article = new Ramette();
		}
		try {
			article.setReference( getTxtReference().getText());
			article.setMarque(getTxtMarque().getText());
			article.setDesignation( getTxtDesignation().getText());
			article.setPrixUnitaire(Float.parseFloat(getTxtPrix().getText()));
			article.setQteStock(Integer.parseInt(getTxtQteStock().getText()));
			if (getCboCouleurs().isEnabled()) {
				((Stylo)article).setCouleur( (String) getCboCouleurs().getSelectedItem());
			} else {
				((Ramette) article).setGrammage(getCk80().isSelected()?80:100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}



//	public Article getArticle(){
//		Article a = null;
//		String marque = getTxtMarque().getText();
//		String designation= getTxtDesignation().getText();
//		String ref = getTxtReference().getText();
//		float pu = Float.parseFloat(getTxtPrix().getText());
//		int qte = Integer.parseInt(getTxtQteStock().getText());
//		int grammage = -1;
//
//		if(radioStylo.isSelected()){
//			String couleur =(String) getCboCouleurs().getSelectedItem();
//			a = new Stylo(idCourant, marque, ref, designation, pu, qte, couleur);
//		}else{
//			if(getCk80().isSelected()){
//				grammage = 80;
//			}else{
//				grammage = 100;
//			}
//			a = new Ramette(idCourant, marque, ref, designation, pu, qte, grammage);
//		}
//		System.out.println(a);
//		return a;
//	}

	public void afficheNouveau(){
		//Par défaut un article est une rammette
		Ramette a = new Ramette( null, "", "", "", 0.0f, 0, 0);
		
		affiche(a);
	}

	public void affiche(Article a) {
		idCourant = a.getIdArticle();
		
		// Autres caractéristiques de l'article
		getTxtReference().setText(a.getReference()+"");
		getTxtMarque().setText(a.getMarque()+"");
		getTxtDesignation().setText(a.getDesignation()+"");
		getTxtPrix().setText(String.valueOf(a.getPrixUnitaire())+"");
		getTxtQteStock().setText(new Integer(a.getQteStock())+"");

		if (a.getClass().equals(Stylo.class)) {
			// Cas du stylo
			// sélectionner le bouton radio correspondant
			getRadioStylo().setSelected(true);
			// activer le choix des couleurs
			getCboCouleurs().setEnabled(true);
			// Sélectionner la bonne couleur
			getCboCouleurs().setSelectedItem(((Stylo) a).getCouleur());
			// Désactiver les cases à cocher
			getCk80().setEnabled(false);
			getCk100().setEnabled(false);
		} else {
			// Cas de la ramette
			// activer le bouton radio
			getRadioRamette().setSelected(true);
			// activer les cases à cocher
			getCk80().setEnabled(true);
			getCk100().setEnabled(true);
			// Papier de 80g par défaut
			getCk80().setSelected(((Ramette)a).getGrammage()==80);
			getCk100().setSelected(((Ramette)a).getGrammage()==100);
			// Désactiver les champs inutiles
			getCboCouleurs().setSelectedItem(null);
			getCboCouleurs().setEnabled(false);
		}
		getRadioStylo().setEnabled(idCourant==null);
		getRadioRamette().setEnabled(idCourant==null);

	}

	public JLabel getLblReference() {
		if(lblReference==null){
			lblReference = new JLabel("Référence");
		}
		return lblReference;
	}
	public JLabel getLblMarque() {
		if (lblMarque == null) {
			lblMarque = new JLabel("Marque");
		}
		return lblMarque;
	}
	public JLabel getLblDesignation() {
		if (lblDesignation == null) {
			lblDesignation = new JLabel("Designation");
		}
		return lblDesignation;
	}
	public JLabel getLblPrix() {
		if (lblPrix == null) {
			lblPrix = new JLabel("Prix");
		}
		return lblPrix;
	}
	public JLabel getLblQteStock() {
		if (lblQteStock == null) {
			lblQteStock = new JLabel("Stock");
		}
		return lblQteStock;
	}
	public JLabel getLblTypeArticle() {
		if (lblTypeArticle == null) {
			lblTypeArticle = new JLabel("Type");
		}
		return lblTypeArticle;
	}
	public JLabel getLblGrammage() {
		if (lblGrammage == null) {
			lblGrammage = new JLabel("Grammage");
		}
		return lblGrammage;
	}
	public JLabel getLblCouleur() {
		if (lblCouleur == null) {
			lblCouleur = new JLabel("Couleur");
		}
		return lblCouleur;
	}
	public JTextField getTxtReference() {
		if (txtReference == null) {
			txtReference = new JTextField(20);
		}
		return txtReference;
	}
	public JTextField getTxtMarque() {
		if (txtMarque == null) {
			txtMarque = new JTextField(20);
		}
		return txtMarque;
	}
	public JTextField getTxtDesignation() {
		if (txtDesignation == null) {
			txtDesignation = new JTextField(20);
		}
		return txtDesignation;
	}
	public JTextField getTxtPrix() {
		if (txtPrix == null) {
			txtPrix = new JTextField(20);
		}
		return txtPrix;
	}
	public JTextField getTxtQteStock() {
		if (txtQteStock == null) {
			txtQteStock = new JTextField(20);
		}
		return txtQteStock;
	}
	public JRadioButton getRadioRamette() {
		if(radioRamette ==null){
			radioRamette = new JRadioButton("Ramette", true);
			radioRamette.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					getCk100().setEnabled(true);
					getCk80().setEnabled(true);
					getCboCouleurs().setEnabled(false);
				}
			});
		}
		return radioRamette;
	}
	public JRadioButton getRadioStylo() {
		if (radioStylo == null) {
			radioStylo = new JRadioButton("Stylo");
			radioStylo.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					getCboCouleurs().setEnabled(true);
					getCk100().setEnabled(false);
					getCk80().setEnabled(false);
				}});
		}
		return radioStylo;
	}
	public JCheckBox getCk80() {
		if (ck80 == null) {
			ck80 = new JCheckBox("80 grammes", true);
		}
		return ck80;
	}
	public JCheckBox getCk100() {
		if (ck100 == null) {
			ck100 = new JCheckBox("100 grammes");
		}
		return ck100;
	}
	public JComboBox<String> getCboCouleurs() {
		if (cboCouleurs == null) {
			cboCouleurs = new JComboBox<String>(new String[]{"bleu", "vert", "rouge", "noir", "jaune"});
		}
		return cboCouleurs;
	}

	public ButtonGroup getTypesArticlesGroup() {
		return typesArticlesGroup;
	}
}

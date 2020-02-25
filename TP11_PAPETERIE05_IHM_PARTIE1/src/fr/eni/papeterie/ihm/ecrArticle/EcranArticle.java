package fr.eni.papeterie.ihm.ecrArticle;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;

public class EcranArticle extends JFrame {
	private static final long serialVersionUID = 1L;


	private PanelArticle panelArticle;

	private JPanel panelBoutons;

	private int indexCatalogue=0;

	private JButton btnPrecedent;
	private JButton btnNouveau;
	private JButton btnEnregistrer;
	private JButton btnSupprimer;
	private JButton btnSuivant;

	private CatalogueManager mger;

	public EcranArticle() {
		//Chargement du CatalogueManager
		try {
			mger = CatalogueManager.getInstance();
		} catch (BLLException e1) {
			e1.printStackTrace();
			JOptionPane.showConfirmDialog(this, "Une erreur est survenue");
			System.exit(1);
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initComposants();

		Article articleCourant;

		//Afficher le premier article du catalogue
		// ou un écran vide si aucun article au catalogue
		if(mger.getCatalogue().size()>0){
			articleCourant = mger.getArticle(0);
			getPanelArticle().affiche(articleCourant);

		}else{
			getPanelArticle().afficheNouveau();
		}
	}


	private void initComposants(){
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(getPanelArticle(), gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(getPanelBoutons(), gbc);
	}

	private PanelArticle getPanelArticle() {
		if(panelArticle==null){
			panelArticle = new PanelArticle();
		}
		return panelArticle;
	}



	public JPanel getPanelBoutons() {
		if(panelBoutons==null){
			panelBoutons = new JPanel(new FlowLayout());
			panelBoutons.add(getBtnPrecedent());
			panelBoutons.add(getBtnNouveau());
			panelBoutons.add(getBtnEnregistrer());
			panelBoutons.add(getBtnSupprimer());
			panelBoutons.add(getBtnSuivant());
		}
		return panelBoutons;
	}


	public JButton getBtnPrecedent() {
		if(btnPrecedent ==null){
			btnPrecedent = new JButton();
			ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../resources/Back24.gif")));
			btnPrecedent.setIcon(image);
			btnPrecedent.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					if(indexCatalogue > 0){
						indexCatalogue--;
						Article articleCourant = mger.getArticle(indexCatalogue);
						getPanelArticle().affiche(articleCourant);
					}

				}

			});
		}
		return btnPrecedent;
	}


	public JButton getBtnNouveau() {
		if(btnNouveau ==null){
			btnNouveau = new JButton();
			ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../resources/New24.gif")));
			btnNouveau.setIcon(image);
			btnNouveau.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					indexCatalogue = mger.getCatalogue().size();
					getPanelArticle().afficheNouveau();
				}

			});
		}

		return btnNouveau;
	}


	public JButton getBtnEnregistrer() {
		if(btnEnregistrer ==null){
			btnEnregistrer = new JButton();
			ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../resources/Save24.gif")));
			btnEnregistrer.setIcon(image);
			btnEnregistrer.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					Article articleAffiche = getPanelArticle().getArticle();
					
					try {
						mger.majArticle(indexCatalogue, articleAffiche);
						panelArticle.affiche(mger.getArticle(indexCatalogue));
					} catch (BLLException e1) {
						JOptionPane.showConfirmDialog(EcranArticle.this, "Une erreur est survenue");
						e1.printStackTrace();
					}
					
				}

			});
		}
		return btnEnregistrer;
	}


	public JButton getBtnSupprimer() {
		if(btnSupprimer ==null){
			btnSupprimer = new JButton();
			ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../resources/Delete24.gif")));
			btnSupprimer.setIcon(image);
			btnSupprimer.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
							try {
								mger.removeArticle(indexCatalogue);
							} catch (BLLException e1) {
								e1.printStackTrace();
								JOptionPane.showConfirmDialog(EcranArticle.this, "Une erreur est survenue");
							}
					if(indexCatalogue<mger.getCatalogue().size()-1){
						//Afficher l'article suivant
						indexCatalogue ++;
						panelArticle.affiche(mger.getArticle(indexCatalogue));
					}else if(indexCatalogue>0){
						indexCatalogue--;
						panelArticle.affiche(mger.getArticle(indexCatalogue));
					}else{
						panelArticle.afficheNouveau();
					}
				}

			});
		}
		return btnSupprimer;
	}


	public JButton getBtnSuivant() {
		if(btnSuivant ==null){
			btnSuivant = new JButton();
			ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../resources/Forward24.gif")));
			btnSuivant.setIcon(image);
			btnSuivant.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					if(indexCatalogue < mger.getCatalogue().size()-1){
						indexCatalogue++;
						Article articleCourant = mger.getArticle(indexCatalogue);
						getPanelArticle().affiche(articleCourant);
					}
				}

			});
		}
		return btnSuivant;
	}


}

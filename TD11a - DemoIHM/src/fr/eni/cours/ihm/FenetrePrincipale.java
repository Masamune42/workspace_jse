package fr.eni.cours.ihm;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.eni.cours.bll.BLLException;
import fr.eni.cours.bll.EleveManager;
import fr.eni.cours.bo.Eleve;
import fr.eni.cours.dal.DALException;

@SuppressWarnings("serial")
public class FenetrePrincipale  extends JFrame{

	//composants
	private JPanel conteneurBtn;
	private JButton btnSuivant, btnPrecedent, btnAjouter, btnModifier, btnSupprimer;
	private JButton btnAjout;
	private PanelEleve conteneurEleve;

	//variables de gestion
	private EleveManager eleveManager;
	private int indexListeEleve=0, index=0;

	public FenetrePrincipale() {
		//chargement du catalogue eleveManager
		try {
			eleveManager = EleveManager.getInstance();
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(this, "Probleme sur la BDD. Contacter l'administrateur");
		} catch (BLLException e) {
			JOptionPane.showInternalMessageDialog(this, "Une erreur est survenue au chargement du catalogue");
		}
		//modification du titre de la fenetre
		setTitle("fenetre principale");
		//modification de la position et de la taille de la fenetre
		//setBounds(200, 200, 500, 400);
		//definir la taille de l'ecran
		//setSize(new Dimension(500, 400));
		setSize(700, 400);
		//position au milieu de l'ecran
		setLocationRelativeTo(null);
		//comportements predefinis pour la fermeture de la fenetre
		// dispose_on_close : arret de l'application et fermeture de la derniere fenetre
		// exit_on_close : arret d l'application même si des fenetres sont visibles
		// hide_on_close : masque la fenetre = setVisible(false)
		// do_nothing_on_close : rien ne se passe lorsque l'utilisateur veut fermer la fenetre
		//            -> il faut gerer un evenement pour cette action

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		initComposant();
		//TODO tester si la liste ramenée par le manager est vide
		Eleve eleveCourant = eleveManager.getEleve(0);
		btnAjout.setVisible(false);
		btnAjouter.setVisible(true);
		//TODO creer la methode afiche() dans le PanelEleve.
		getConteneurEleve().afficher(eleveCourant);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
	}

	public void initComposant() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=0;
		getContentPane().add(getConteneurEleve(),gbc);
		gbc.gridy=1;
		getContentPane().add(getConteneurBtn(),gbc);
	}

	public PanelEleve getConteneurEleve() {
		if (conteneurEleve==null) {
			conteneurEleve = new PanelEleve();
		}
		return conteneurEleve;
	}

	public JPanel getConteneurBtn() {
		if (conteneurBtn==null) {
			conteneurBtn = new JPanel();
			conteneurBtn.add(getBtnPrecedent());
			conteneurBtn.add(getBtnAjouter());
			conteneurBtn.add(getBtnAjout());
			conteneurBtn.add(getBtnModifier());
			conteneurBtn.add(getBtnSupprimer());
			conteneurBtn.add(getBtnSuivant());
		}
		return conteneurBtn;
	}


	/************** Lazy Instanciation ************/
	public JButton getBtnSuivant() {
		if (btnSuivant==null) {
			btnSuivant = new JButton("Suivant");
			btnSuivant.setToolTipText("suivant");
			//couleur du fond
			btnSuivant.setBackground(Color.LIGHT_GRAY);
			//couleur du texte
			btnSuivant.setForeground(Color.BLUE);
			//police, style et taille
			btnSuivant.setFont(new Font("Serif", Font.BOLD, 12));
			//apparence du cuseur
			btnSuivant.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnSuivant.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni/cours/ihm/resources/suivant.png")));
			btnSuivant.setRolloverIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni/cours/ihm/resources/suiv.png")));

			//creation d'un Listener
			btnSuivant.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//sur click du bouton precedent
					if (indexListeEleve < eleveManager.getEleves().size()-1) {
						// affichage de l'eleve precedent
						indexListeEleve++;
					} else {
						//affichage du dernier element de la liste
						indexListeEleve = 0;
					}
					Eleve eleveSuivant = eleveManager.getEleve(indexListeEleve);
					getConteneurEleve().afficher(eleveSuivant);


					index = indexListeEleve;
				}
			});
		}
		return btnSuivant;
	}

	public JButton getBtnPrecedent() {
		if (btnPrecedent==null) {
			btnPrecedent = new JButton("Precedent");
			btnPrecedent.setToolTipText("Enregistrement precedent");
			//placer le focus sur le bouton
			btnPrecedent.setBackground(Color.LIGHT_GRAY);
			//couleur du texte
			btnPrecedent.setForeground(Color.BLUE);
			//police, style et taille
			btnPrecedent.setFont(new Font("Serif", Font.BOLD, 12));
			//apparence du cuseur
			btnPrecedent.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnPrecedent.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni/cours/ihm/resources/precedent.png")));
			btnPrecedent.setRolloverIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni/cours/ihm/resources/prec.png")));

			//creation d'un Listener
			btnPrecedent.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//sur click du bouton precedent
					if (indexListeEleve>0) {
						// affichage de l'eleve precedent
						indexListeEleve--;
					} else {
						//affichage du dernier element de la liste
						indexListeEleve = eleveManager.getEleves().size()-1;
					}
					Eleve elevePrecedent = eleveManager.getEleve(indexListeEleve);
					getConteneurEleve().afficher(elevePrecedent);


					index = indexListeEleve;
				}
			});
		}
		return btnPrecedent;
	}

	public JButton getBtnAjouter() {
		if (btnAjouter==null) {
			btnAjouter = new JButton("Ajouter");
			btnAjouter.setToolTipText("ajouter un(e) nouvel(le) élève");
			//placer le focus sur le bouton
			btnAjouter.setBackground(Color.LIGHT_GRAY);
			//couleur du texte
			btnAjouter.setForeground(Color.BLUE);
			//police, style et taille
			btnAjouter.setFont(new Font("Serif", Font.BOLD, 12));
			//apparence du cuseur
			btnAjouter.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnAjouter.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni/cours/ihm/resources/ajouter.png")));
			btnAjouter.setRolloverIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni/cours/ihm/resources/add.png")));

			//listener
			btnAjouter.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					btnAjouter.setVisible(false);
					btnAjout.setVisible(true);
					//affichage du formulaire avec reinitialisation des JTextFields
					indexListeEleve = eleveManager.getEleves().size();
					getConteneurEleve().afficher();
				}
			});
		}
		return btnAjouter;
	}

	public JButton getBtnAjout() {
		if (btnAjout==null) {
			btnAjout = new JButton("Confirmer");
			btnAjout.setToolTipText("Confirmer un(e) nouvel(le) élève");
			//placer le focus sur le bouton
			btnAjout.setBackground(Color.LIGHT_GRAY);
			//couleur du texte
			btnAjout.setForeground(Color.BLUE);
			//police, style et taille
			btnAjout.setFont(new Font("Serif", Font.BOLD, 12));
			//apparence du cuseur
			btnAjout.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnAjout.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni/cours/ihm/resources/valider.png")));
			//Listener
			btnAjout.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					btnAjouter.setVisible(true);
					btnAjout.setVisible(false);
					//ajout de l'eleve
					Eleve eleveAjout = getConteneurEleve().getEleve();
					try {
						eleveManager.addEleve(eleveAjout);
						getConteneurEleve().afficher(eleveManager.getEleve(indexListeEleve));
					} catch (DALException e1) {
					} catch (SQLException e1) {						
					} catch (BLLException e1) {
						JOptionPane.showMessageDialog(FenetrePrincipale.this, "Une erreur est survenue lors de l'ajout "+e1.getMessage(), "ERREUR INSERTION ELEVE", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return btnAjout;
	} 

	public JButton getBtnModifier() {
		if (btnModifier==null) {
			btnModifier = new JButton("Modifier");
			btnModifier.setToolTipText("modifier l'adresse de l'élève");
			//couleur du fond
			btnModifier.setBackground(Color.LIGHT_GRAY);
			//couleur du texte
			btnModifier.setForeground(Color.BLUE);
			//police, style et taille
			btnModifier.setFont(new Font("Serif", Font.BOLD, 12));
			//apparence du cuseur
			btnModifier.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnModifier.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni/cours/ihm/resources/modifier.png")));
			btnModifier.setRolloverIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni/cours/ihm/resources/modif.png")));
		}
		return btnModifier;
	}

	public JButton getBtnSupprimer() {
		if (btnSupprimer==null) {
			btnSupprimer = new JButton("Supprimer");
			btnSupprimer.setToolTipText("Supprimer un(e) élève");
			//couleur du fond
			btnSupprimer.setBackground(Color.LIGHT_GRAY);
			//couleur du texte
			btnSupprimer.setForeground(Color.BLUE);
			//police, style et taille
			btnSupprimer.setFont(new Font("Serif", Font.BOLD, 12));
			//apparence du cuseur
			btnSupprimer.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnSupprimer.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni/cours/ihm/resources/supprimer.png")));
			btnSupprimer.setRolloverIcon(new ImageIcon(FenetrePrincipale.class.getResource("/fr/eni/cours/ihm/resources/supp.png")));
		}
		return btnSupprimer;
	}


}
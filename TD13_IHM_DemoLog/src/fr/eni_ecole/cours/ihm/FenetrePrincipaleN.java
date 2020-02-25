package fr.eni_ecole.cours.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FenetrePrincipaleN extends JFrame{

	JPanel conteneur;
	JButton btnLister,btnRechercher,btnInserer,btnModifier,btnSupprimer;
	JMenuItem mnuLister,mnuRechercher,mnuInserer,mnuModifier,mnuSupprimer;
	
	public FenetrePrincipaleN(){
		setTitle("fenetre principale");
		setBounds(200, 200, 500, 400);
		// il ne se passe rien lorsque l'utilisateur demande la fermeture de la 
		//fenetre. Dans ce cas il faut gérer les evenements pour que l'action de
		//l'utilisateur provoque un effet sur la fenetre
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 

		// creation des boutons
		btnLister = new JButton("Lister");
		btnRechercher = new JButton("Rechercher");
		btnInserer = new JButton("Inserer");
		btnModifier = new JButton("Modifier");
		btnSupprimer = new JButton("Supprimer");

		btnLister.setActionCommand("lister");				//169
		btnRechercher.setActionCommand("rechercher");
		btnInserer.setActionCommand("inserer");
		btnModifier.setActionCommand("modifier");
		btnSupprimer.setActionCommand("supprimer");
		
		//au lieu de creer les cinq ecouteurs sur les cinq boutons
		//on crée un ecouteur unique
		EcouteurEleve ecE = new EcouteurEleve();
		//association de l'ecouteur à chaque bouton
		btnLister.addActionListener(ecE);
		btnRechercher.addActionListener(ecE);
		btnInserer.addActionListener(ecE);
		btnModifier.addActionListener(ecE);
		btnSupprimer.addActionListener(ecE);
		
		// creation du menu
		JMenuBar barreMenu = new JMenuBar();
		JMenu menu = new JMenu("Gestion des élèves");
		barreMenu.add(menu);
		mnuLister = new JMenuItem("Lister");
		mnuRechercher= new JMenuItem("Rechercher");
		mnuInserer= new JMenuItem("Inserer");
		mnuModifier= new JMenuItem("Modifier");
		mnuSupprimer= new JMenuItem("Supprimer");
		menu.add(mnuLister);
		menu.add(mnuRechercher);
		menu.add(mnuInserer);
		menu.add(mnuModifier);
		menu.add(mnuSupprimer);

		mnuLister.setActionCommand("lister");				
		mnuRechercher.setActionCommand("rechercher");
		mnuInserer.setActionCommand("inserer");
		mnuModifier.setActionCommand("modifier");
		mnuSupprimer.setActionCommand("supprimer");

		//association de l'ecouteur à chaque menu (les mêmes que pour les boutons)
		mnuLister.addActionListener(ecE);
		mnuRechercher.addActionListener(ecE);
		mnuInserer.addActionListener(ecE);
		mnuModifier.addActionListener(ecE);
		mnuSupprimer.addActionListener(ecE);
		//ajout du menu à la fenetre
		setJMenuBar(barreMenu);
		
		//creation du conteneur intermédiaire 164
		conteneur = new JPanel();
		//ajout des bouton au conteneur intermediaire
		conteneur.add(btnLister);
		conteneur.add(btnRechercher);
		conteneur.add(btnInserer);
		conteneur.add(btnModifier);
		conteneur.add(btnSupprimer);
		//positionne le conteneur
		//((FlowLayout) conteneur.getLayout()).setAlignment(FlowLayout.CENTER);
		//ajout du conteneur sur le ContentPane positionné en bas
		getContentPane().add(conteneur, BorderLayout.SOUTH);
		//ajout du conteneur sur le ContentPane
		//getContentPane().add(conteneur);
		
		//création d'une instance d'une classe anonyme charger de gérer
		//les evenements 152
		addWindowListener(new WindowAdapter()
		//debut de la definition de la classe
		{
			//153
			public void windowClosing(WindowEvent arg0){
				System.exit(0);
			}
		}//fin de la définition de la classe
		); //fin de l'appel à la méthode addWindowListener
		
	}// fin du construteur
	
	public class EcouteurEleve implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			
			String commande;
			commande = arg0.getActionCommand();
			
			if (commande.equals("lister")){
				conteneur.setBackground(Color.RED);
			}
			if (commande.equals("rechercher")){
				conteneur.setBackground(Color.BLUE);
			}
			if (commande.equals("inserer")){
				conteneur.setBackground(Color.YELLOW);
			}
			if (commande.equals("modifier")){
				conteneur.setBackground(Color.GREEN);
			}
			if (commande.equals("supprimer")){
				conteneur.setBackground(Color.GRAY);
			}
			
		}
	}
	
}// fin de la classe



package fr.eni_ecole.cours.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
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
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.eni_ecole.cours.bll.BLLException;
import fr.eni_ecole.cours.bll.EleveManager;
import fr.eni_ecole.cours.bo.Eleve;

public class FenetrePrincipale7  extends JFrame implements ActionListener, MenuListener{
	private static final long serialVersionUID = 1L;

	//declaration des variables et composants
	private PanelEleve7 panelEleve;
	private JPanel conteneurTitre;
	private JLabel lblTitre;

	private JMenuBar barreMenus;
	private JMenu fichier, edition;
	private JMenuItem ouvrir, sauvegarder, fermer, serialiser, deserialiser;
	private boolean fichierOuvert, infos;
	private String nomFichier;
	
	//variables de gestion
	private EleveManager eleveManager;
	
	//variable pour la deserialisation
	public static ArrayList<Eleve> eleves;
	public static boolean activeOnglet=false;
	
	public static ArrayList<Eleve> getEleves() {
		return eleves;
	}


	@SuppressWarnings("static-access")
	public void setEleves(ArrayList<Eleve> eleves) {
		this.eleves = eleves;
	}

	protected static Logger logger;

	public FenetrePrincipale7(){
		setTitle("fenetre principale");
		setSize(new Dimension(800, 500));
		setLocationRelativeTo(null);
		//setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 

		//ajout de la barre de menu � la fenetre
		setJMenuBar(getBarreMenu());
		//ajout des conteneurs au contentPane
		initComposants();
		
		//cr�ation d'une instance d'une classe anonyme charger de g�rer l'evenement 'fermeture fenetre'
		addWindowListener(new WindowAdapter()
		//debut de la definition de la classe
		{
			public void windowClosing(WindowEvent arg0){
				System.exit(0);
			}
		}//fin de la d�finition de la classe
		); //fin de l'appel � la m�thode addWindowListener
		
	}// fin du construteur
	
	
	private void initComposants(){
		//ajout du conteneur intermediaire au ContentPane
		getContentPane().add(getConteneurTitre(), BorderLayout.NORTH);
		getContentPane().add(getConteneurEleve(),BorderLayout.CENTER);
	}

	//*** Nous choisissons de d�l�guer la cr�ation des instances des composants   ***
	//*** graphiques aux m�thodes get. Cela permet de ne cr�er l�instance que si  ***
	//*** n�cessaire, et permet aussi de bien ordonner le code. Cette m�thode est ***
	//*** nomm�e Lazy instanciation												  ***

	//************** creation des conteneurs intermediaires *************
	
	public JPanel getConteneurTitre(){
		if (conteneurTitre == null){
			//creation du conteneur interm�diaire regroupant le titre
			conteneurTitre = new JPanel(new FlowLayout());
			//ajout du titre au conteneur intermediaire
			conteneurTitre.add(getTitre());
		}
		return conteneurTitre;
	}

	public PanelEleve7 getConteneurEleve(){
		if (panelEleve == null){
			//creation du conteneur interm�diaire regroupant les donn�es
			panelEleve = new PanelEleve7();
		}	
		return panelEleve;
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
			serialiser = new JMenuItem("Serialiser");
			edition.add(serialiser);
			serialiser.addActionListener(this);
			deserialiser = new JMenuItem("D�serialiser");
			edition.add(deserialiser);
			deserialiser.addActionListener(this);
			//etat initial : pas de fichier ouvert, pas d'infos � copier
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
		if (fichierOuvert) serialiser.setEnabled(true); 
		else serialiser.setEnabled(fichierOuvert);
		deserialiser.setEnabled(infos);
		ouvrir.setEnabled(true); 
		sauvegarder.setEnabled(fichierOuvert);
		fermer.setEnabled(fichierOuvert);
	}	


	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		logger=Logger.getLogger("fr.eni_ecole.cours.ihm.FenetrePrincipale7");
		if(source== ouvrir){
			// Fenetre de dialogue permettant de choisir le fichier � charger
            JFileChooser fileChoose = new JFileChooser();
            FileNameExtensionFilter filter = new  FileNameExtensionFilter("Fichier XML", "xml");
            fileChoose.setFileFilter(filter);
            fileChoose.setCurrentDirectory(new File("~"));
            String fichier = null;
            int resultatOuvrir = fileChoose.showDialog(fileChoose, new String("Ouvrir"));
 
            // Si l'utilisateur � cliquer sur "Ouvrir" et n'a pas annul� on r�cup�re le nom du fichier
            if (resultatOuvrir == JFileChooser.APPROVE_OPTION) {
                fichier = fileChoose.getSelectedFile().toString();
                //test si fichier ouvert : on ferme l'ancien fichier
                if (fichierOuvert) {
                	JOptionPane.showMessageDialog(this, "On ferme "+nomFichier, "MESSAGES", JOptionPane.INFORMATION_MESSAGE);
                }
                //on ouvre le nouveau fichier
                nomFichier=fichier;
                fichierOuvert=true;
                JOptionPane.showMessageDialog(this, "On ouvre "+nomFichier, "MESSAGES", JOptionPane.INFORMATION_MESSAGE);
            }
		}
		if(source==fermer){
			JOptionPane.showMessageDialog(this, "On ferme "+nomFichier, "MESSAGES", JOptionPane.INFORMATION_MESSAGE);
			fichierOuvert=false;
		}
		if(source==sauvegarder){
			JOptionPane.showMessageDialog(this, "On sauvegarde "+nomFichier, "MESSAGES", JOptionPane.INFORMATION_MESSAGE);
		}
		if(source==serialiser){
			try {
				eleveManager= EleveManager.getInstance();
				ArrayList<Eleve> eleves = (ArrayList<Eleve>) eleveManager.getEleves();
				String reponse= JOptionPane.showInputDialog(FenetrePrincipale7.this, "Choisir le nom du fichier � d�s�rialiser (ex: eleves.xml)","DESERIALISTAION DES ELEVES", JOptionPane.QUESTION_MESSAGE);
				//si la reponse est CANCEL alors on quitte sinon on d�s�rialise
				if (reponse != null) {
					reponse = "C:\\temp\\"+reponse;
					boolean ok = serialiser(eleves, reponse);
					if (ok){
						JOptionPane.showMessageDialog(this, "La s�rialisation des �l�ves (format XML) s'est faite correctement.\n Fichier disponible sous C:/temp/eleves.xml.", "MESSAGES", JOptionPane.INFORMATION_MESSAGE);
						logger.log(Level.INFO, "S�rialisation des �l�ves dans "+ reponse +" - logger - INFO.");
					} else {
						JOptionPane.showMessageDialog(this, "Probleme de s�rialisation des �l�ves (format XML)", "ERREUR", JOptionPane.WARNING_MESSAGE);
						logger.log(Level.WARNING, "Probleme de s�rialisation des �l�ves (format XML) - logger - WARNING.");
					}
					infos=true;
				}
			} catch (BLLException e) {
				JOptionPane.showMessageDialog(this, "Probleme de s�rialisation des �l�ves (format XML)", "ERREUR", JOptionPane.WARNING_MESSAGE);
				logger.log(Level.WARNING, "Probleme de s�rialisation des �l�ves (format XML) - logger - WARNING.");
			}

		}
		if(source==deserialiser){
			String reponse= JOptionPane.showInputDialog(FenetrePrincipale7.this, "Choisir le nom du fichier � d�s�rialiser (ex: eleves.xml)","DESERIALISTAION DES ELEVES", JOptionPane.QUESTION_MESSAGE);
			//si la reponse est CANCEL alors on quitte sinon on d�s�rialise
			if (reponse != null) {
				reponse = "C:\\temp\\"+reponse;
				eleves = deserialiser(reponse);
				logger.log(Level.INFO, "D�s�rialisation des �l�ves de - logger - INFO.");
				for (Eleve unEleve : eleves){
//					System.out.println(unEleve+ "\n");
					logger.log(Level.INFO, unEleve +" - logger - INFO.");
				}
				FenetrePrincipale6.activeOnglet=true;
				logger.log(Level.INFO, "D�s�rialisation des �l�ves de "+ reponse +" - logger - INFO.");
//				getConteneurEleve();
			}
			infos=false;
		}
	}
	
 	/**
	 * Classe en charge de s�rialiser un eleve au format XML
	 * @param eleves : liste des eleves
	 * @return un booleen 
	 * 				true, si la serialisation s'est bien pass�e
	 * 				false, dans le cas contraire
	 */
	public boolean serialiser(ArrayList<Eleve> lesEleves, String reponse)
	{
		boolean serialOk=false;
		FileOutputStream fos;
		try{
			fos=new FileOutputStream(reponse);
			XMLEncoder encoder = new XMLEncoder(fos);
			encoder.writeObject(lesEleves);
			encoder.flush();
			encoder.close();
			serialOk=true;
			fos.close();
		}catch (FileNotFoundException e){
			JOptionPane.showMessageDialog(FenetrePrincipale7.this, "Erreur de fichier : le fichier est introuvable", "ERREUR", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "une erreur est survenue, le fichier est introuvable - "+e.getMessage()+" - logger - SEVERE.");
		}catch(IOException io){
			JOptionPane.showMessageDialog(FenetrePrincipale7.this, "Erreur de fichier : le fichier n'est pas au format XML", "ERREUR", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "une erreur est survenue, le fichier n'est pas au format XML - "+io.getMessage()+" - logger - SEVERE.");
		}
		return serialOk;
	}

	 /**
	 * D�s�rialiser le fichier au format XML pour obtenir une liste d'eleves.
	 * @param fichier Chemin du fichier
	 * @return liste d'eleves
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Eleve> deserialiser(String fichier){
		ArrayList<Eleve> eleves=null;
		try{
			FileInputStream fis=new FileInputStream(fichier);
			XMLDecoder decoder= new XMLDecoder(fis);
			eleves=(ArrayList<Eleve>)decoder.readObject();
			decoder.close();
			fis.close();
		}catch (FileNotFoundException e){
			JOptionPane.showMessageDialog(FenetrePrincipale7.this, "Erreur de fichier : le fichier est introuvable", "ERREUR", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "une erreur est survenue, le fichier est introuvable - "+e.getMessage()+" - logger - error.");
		}catch(IOException io){
			JOptionPane.showMessageDialog(FenetrePrincipale7.this, "Erreur de fichier : le fichier n'est pas au format XML", "ERREUR", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "une erreur est survenue, le fichier n'est pas au format XML - "+io.getMessage()+" - logger - error.");
		}
		return eleves;
	}

}

package fr.eni_ecole.cours.dal;

import java.util.ArrayList;

import fr.eni_ecole.cours.bo.Eleve;
import fr.eni_ecole.cours.dal.DALException;

// cette interface déclare les methodes nécessaire pour gérer le cycle de vie du
// BO Eleve
public interface EleveDAO {

	//Rechercher un eleve par son nom et prenom
	public Eleve rechercher(String nom, String prenom) throws DALException;
	
	//lister tous les eleves 
	public ArrayList<Eleve> lister() throws DALException;
	
	//Modifier l'adresse d'un eleve
	public int modifier(String adresse,String nom, String prenom) throws DALException;
	
	//Ajouter un nouvel eleve
	public int ajouter(Eleve eleve) throws DALException;
	
	//Supprimer un eleve
	public int supprimer(String nom, String prenom) throws DALException;
	
}

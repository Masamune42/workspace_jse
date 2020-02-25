package fr.eni.cours.dal;

import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni.cours.bo.Eleve;

public interface DAOEleve {

	ArrayList<Eleve> lister() throws DALException,SQLException;

	Eleve rechercher(String nom, String prenom) throws DALException,SQLException;

	int ajouter(Eleve eleve) throws DALException,SQLException;
	
	int supprimer(String nom, String prenom) throws DALException,SQLException;
	
	int modifier(String adresse,String nom, String prenom) throws DALException,SQLException;

	int compter() throws DALException, SQLException;
}

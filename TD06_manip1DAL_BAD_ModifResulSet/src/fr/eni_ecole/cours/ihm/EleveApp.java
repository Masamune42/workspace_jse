package fr.eni_ecole.cours.ihm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni_ecole.cours.bo.Eleve;
import fr.eni_ecole.cours.dal.EleveDAO;

public class EleveApp {

	public static void main(String[] args) {
		// lister les élèves
		System.out.println("LISTE DES ELEVES");
		List<Eleve> listeEleves = new ArrayList<Eleve>();
		try {
			listeEleves = EleveDAO.lister();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		for(Eleve unEleve : listeEleves){
			System.out.println(unEleve.toString());
		}
		System.out.println("************************************************");
		System.out.println("INSERTION D'UN ELEVE VIA RESULTSET");
		listeEleves = new ArrayList<Eleve>();
		try {
			listeEleves = EleveDAO.insertionRS();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		for(Eleve unEleve : listeEleves){
			System.out.println(unEleve.toString());
		}
		System.out.println("************************************************");
		System.out.println("MODIFICATION ADRESSE VIA RESULTSET");
		try {
			String nom = "Durand";
			String prenom = "Sophie";
			Eleve eleveModif = EleveDAO.modificationRS(nom, prenom);
			if (eleveModif == null){
				System.out.println("Eleve non trouvé");
			}else{
				System.out.println(eleveModif.toString()+" modifié(e)");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("************************************************");
		System.out.println("SUPPRESSION D'UN ELEVE VIA RESULTSET");
		try {
			String nom = "Largeau";
			String prenom = "Thierry";
			Eleve eleveSupp = EleveDAO.suppressionRS(nom, prenom);
			if (eleveSupp == null){
				System.out.println("Eleve non trouvé");
			}else{
				System.err.println(eleveSupp.toString()+ " supprimé(e)");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	
	
	}

}

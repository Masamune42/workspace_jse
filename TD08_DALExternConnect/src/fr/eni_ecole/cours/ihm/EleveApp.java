package fr.eni_ecole.cours.ihm;

import java.util.ArrayList;
import java.util.List;

import fr.eni_ecole.cours.bo.Eleve;
import fr.eni_ecole.cours.dal.DALException;
import fr.eni_ecole.cours.dal.EleveDAOJdbcImpl;

public class EleveApp {

	public static void main(String[] args) {
		// lister les �l�ves
		System.out.println("LISTE DES ELEVES");
		List<Eleve> listeEleves = new ArrayList<Eleve>();
		try {
			listeEleves = EleveDAOJdbcImpl.lister();
		} catch (DALException e) {
			System.err.println("******ERREUR *******");
			System.err.println("message : "+e.getMessage());
			System.err.println("cause : "+e.getCause());
			System.err.println("----- trace -----");
			System.err.println("classe : "+e.getStackTrace()[0].getClassName());
			System.err.println("methode : "+e.getStackTrace()[0].getMethodName());
			System.err.println("ligne : "+e.getStackTrace()[0].getLineNumber());
		}
		for(Eleve unEleve : listeEleves){
			System.out.println(unEleve.toString());
		}
		System.out.println("************************************************");
		System.out.println("RECHERCHER UN ELEVE");
		try {
			String nom = "Durand";
			String prenom = "Sophie";
			Eleve eleveRech = EleveDAOJdbcImpl.rechercher(nom, prenom);
			if (eleveRech == null){
				System.out.println("Eleve non trouv�");
			}else{
				System.out.println(eleveRech.toString());
			}
		} catch (DALException e) {
			System.err.println("******ERREUR *******");
			System.err.println("message : "+e.getMessage());
			System.err.println("cause : "+e.getCause());
			System.err.println("----- trace -----");
			System.err.println("classe : "+e.getStackTrace()[0].getClassName());
			System.err.println("methode : "+e.getStackTrace()[0].getMethodName());
			System.err.println("ligne : "+e.getStackTrace()[0].getLineNumber());
		}
		System.out.println("************************************************");
		System.out.println("INSERER UN ELEVE");
		try {
			Eleve eleveIns= new Eleve("Holmes","Sherlock","221B Baker Street - 1234 LONDON");
			int nbIns =EleveDAOJdbcImpl.ajouter(eleveIns);
			System.out.println(nbIns+ " Eleve(s) inser�(e)(s)");
			System.out.println("/// controle ///");
			listeEleves = EleveDAOJdbcImpl.lister();
			for(Eleve unEleve : listeEleves){
				System.out.println(unEleve.toString());
			}
		} catch (DALException e) {
			System.err.println("******ERREUR *******");
			System.err.println("message : "+e.getMessage());
			System.err.println("cause : "+e.getCause());
			System.err.println("----- trace -----");
			System.err.println("classe : "+e.getStackTrace()[0].getClassName());
			System.err.println("methode : "+e.getStackTrace()[0].getMethodName());
			System.err.println("ligne : "+e.getStackTrace()[0].getLineNumber());
		}
		System.out.println("************************************************");
		System.out.println("MODIFIER UN ELEVE");
		try {
			String nom = "Holmes";
			String prenom = "Sherlock";
			String adresse = "221 Baker Street - 1111 LONDON";
			int nbMod=EleveDAOJdbcImpl.modifier(adresse, nom, prenom);
			System.out.println(nbMod+" Eleve(s) modifi�(e)(s)");
			System.out.println("/// controle ///");
			listeEleves = EleveDAOJdbcImpl.lister();
			for(Eleve unEleve : listeEleves){
				System.out.println(unEleve.toString());
			}
		} catch (DALException e) {
			System.err.println("******ERREUR *******");
			System.err.println("message : "+e.getMessage());
			System.err.println("cause : "+e.getCause());
			System.err.println("----- trace -----");
			System.err.println("classe : "+e.getStackTrace()[0].getClassName());
			System.err.println("methode : "+e.getStackTrace()[0].getMethodName());
			System.err.println("ligne : "+e.getStackTrace()[0].getLineNumber());
		}
		System.out.println("************************************************");
		System.out.println("SUPPRIMER UN ELEVE");
		try {
			String nom = "Holmes";
			String prenom = "Sherlock";
			int nbSup=EleveDAOJdbcImpl.supprimer(nom, prenom);
			System.out.println(nbSup+" Eleve(s) supprim�(e)(s)");
			System.out.println("/// controle ///");
			listeEleves = EleveDAOJdbcImpl.lister();
			for(Eleve unEleve : listeEleves){
				System.out.println(unEleve.toString());
			}
		} catch (DALException e) {
			System.err.println("******ERREUR *******");
			System.err.println("message : "+e.getMessage());
			System.err.println("cause : "+e.getCause());
			System.err.println("----- trace -----");
			System.err.println("classe : "+e.getStackTrace()[0].getClassName());
			System.err.println("methode : "+e.getStackTrace()[0].getMethodName());
			System.err.println("ligne : "+e.getStackTrace()[0].getLineNumber());
		}
	}

}

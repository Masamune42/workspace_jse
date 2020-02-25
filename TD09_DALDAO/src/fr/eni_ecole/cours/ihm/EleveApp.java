package fr.eni_ecole.cours.ihm;

import java.util.ArrayList;
import java.util.List;

import fr.eni_ecole.cours.bo.Eleve;
import fr.eni_ecole.cours.dal.DALException;
import fr.eni_ecole.cours.dal.DAOFactory;
import fr.eni_ecole.cours.dal.EleveDAO;

public class EleveApp {

	public static void main(String[] args) {
		
		EleveDAO eleveDAO = null;
		try {
			eleveDAO = DAOFactory.getEleveDAO();
		} catch (DALException e) {
			System.err.println("******ERREUR *******");
			System.err.println("message : "+e.getMessage());
			System.err.println("cause : "+e.getCause());
			System.err.println("----- trace -----");
			System.err.println("classe : "+e.getStackTrace()[0].getClassName());
			System.err.println("methode : "+e.getStackTrace()[0].getMethodName());
			System.err.println("ligne : "+e.getStackTrace()[0].getLineNumber());
		}
		
		// lister les élèves
		System.out.println("LISTE DES ELEVES");
		List<Eleve> listeEleves = new ArrayList<Eleve>();
		try {
			listeEleves = eleveDAO.lister();
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
			Eleve eleveRech = eleveDAO.rechercher(nom, prenom);
			if (eleveRech == null){
				System.out.println("Eleve non trouvé");
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
			int nbIns=eleveDAO.ajouter(eleveIns);
			System.out.println(nbIns+ " Eleve(s) inseré(e)(s)");
			System.out.println("/// controle ///");
			listeEleves = eleveDAO.lister();
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
			int nbMod=eleveDAO.modifier(adresse, nom, prenom);
			System.out.println(nbMod+ " Eleve(e) modifié(e)(s)");
			System.out.println("/// controle ///");
			listeEleves = eleveDAO.lister();
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
			int nbSup=eleveDAO.supprimer(nom, prenom);
			System.out.println(nbSup+ " Eleve(e) supprimé(e)(s)");
			System.out.println("/// controle ///");
			listeEleves = eleveDAO.lister();
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

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
			if (listeEleves.isEmpty()){
				System.err.println(" !!! aucun eleve dans la BDD");
			}else{
				for(Eleve unEleve : listeEleves){
					System.out.println(unEleve);
				}
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
		System.out.println("RECHERCHER UN ELEVE");
		try {
			String nom = "Durand";
			String prenom = "Sophie";
			Eleve eleveRecherchee = EleveDAOJdbcImpl.rechercher(nom, prenom);
			if (eleveRecherchee.getNom()==null){
				System.err.println("!!! Eleve "+prenom+" "+nom+" non trouv�(e)");
			}else{
				System.out.println(eleveRecherchee);
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
			int nbRow=EleveDAOJdbcImpl.ajouter(eleveIns);
			if (nbRow>1){
				System.out.println(nbRow+" �l�ves inser�s(es)");
			} else if (nbRow==1){
				System.out.println(nbRow+" �l�ve inser�(e)");
			}else {
				System.err.println(" !!! aucune insertion effectu�e");
			}
			System.out.println("///********* controle *********//");
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
			int nb = EleveDAOJdbcImpl.modifier(adresse, nom, prenom);
			if(nb>1){
				System.out.println(nb+" �l�ves modifi�s(es)");
			}else if(nb==1){
				System.out.println(nb+" �l�ve modifi�(e)");
			}else{
				System.err.println("!!! aucune modification effectu�e");
			}
			System.out.println("///********** controle **********///");
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
			int nbligne = EleveDAOJdbcImpl.supprimer(nom, prenom);
			if (nbligne>1){
				System.out.println(nbligne +" �l�ves supprim�s(es)");
			}else if(nbligne==1){
				System.out.println(nbligne+ " �l�ve supprim�(e)");
			}else{
				System.err.println("!!! aucune suppression effectu�e");
			}
			System.out.println("///*********** controle ***********///");
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

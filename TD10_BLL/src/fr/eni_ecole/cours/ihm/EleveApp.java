package fr.eni_ecole.cours.ihm;

import fr.eni_ecole.cours.bll.BLLException;
import fr.eni_ecole.cours.bll.EleveManager;
import fr.eni_ecole.cours.bo.Eleve;
import fr.eni_ecole.cours.dal.DALException;

public class EleveApp {

	public static void main(String[] args) {
		// creation d'une seule instance de EleveManager
		EleveManager manager = null;
		try {
			manager = EleveManager.getInstance();
			System.out.println("************************************************");
			System.out.println("LISTE DES ELEVES");
			System.out.println(manager.getEleves());

			System.out.println("************************************************");
			System.out.println("RECHERCHER UN ELEVE");
			System.out.println(manager.getEleve(1));

			System.out.println("************************************************");
			System.out.println("INSERER UN ELEVE");
			try {
				Eleve eleveIns= new Eleve("Holmes","Sherlock","221B Baker Street - 1234 LONDON");
				int indexIns= manager.addEleve(eleveIns);
				System.out.println("Eleve inseré(e) : \n"+manager.getEleve(indexIns));
				System.out.println("/// controle ///");
				System.out.println(manager.getEleves());
			} catch (DALException e) {
				System.err.println("******ERREUR *******");
				System.err.println("message : "+e.getMessage());
				System.err.println("cause : "+e.getCause());
				System.err.println("----- trace -----");
				System.err.println("classe : "+e.getStackTrace()[0].getClassName());
				System.err.println("methode : "+e.getStackTrace()[0].getMethodName());
				System.err.println("ligne : "+e.getStackTrace()[0].getLineNumber());
			} catch (BLLException e) {
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
				Eleve eleveAffiche = new Eleve();
				eleveAffiche.setNom("Holmes");
				eleveAffiche.setPrenom("Sherlock");
				//modification de l'adresse
				eleveAffiche.setAdresse("221 Baker Street - 1111 LONDON");
				manager.majEleve(2, eleveAffiche);
				System.out.println("Eleve modifié : \n"+eleveAffiche);
				System.out.println("/// controle ///");
				System.out.println(manager.getEleves());
			} catch (DALException e) {
				System.err.println("******ERREUR *******");
				System.err.println("message : "+e.getMessage());
				System.err.println("cause : "+e.getCause());
				System.err.println("----- trace -----");
				System.err.println("classe : "+e.getStackTrace()[0].getClassName());
				System.err.println("methode : "+e.getStackTrace()[0].getMethodName());
				System.err.println("ligne : "+e.getStackTrace()[0].getLineNumber());
			} catch (BLLException e) {
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
				manager.removeEleve(2);
				System.out.println("Eleve supprimé");
				System.out.println("/// controle ///");
				System.out.println(manager.getEleves());
			} catch (DALException e) {
				System.err.println("******ERREUR *******");
				System.err.println("message : "+e.getMessage());
				System.err.println("cause : "+e.getCause());
				System.err.println("----- trace -----");
				System.err.println("classe : "+e.getStackTrace()[0].getClassName());
				System.err.println("methode : "+e.getStackTrace()[0].getMethodName());
				System.err.println("ligne : "+e.getStackTrace()[0].getLineNumber());
			}

		} catch (BLLException e) {
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

package fr.eni.papeterie.ihm;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

public class AppliTestBLL {

	public static void main(String[] args) {
		//Instanciation du jeu d'essai 
		Article a1 = new Stylo( "Bic", "BBOrange","Bic bille Orange", 1.2f, 20, "bleu");
		Article a2 = new Ramette(  "Clairef", "CRA4S", "Ramette A4 Sup", 9f, 20, 80);
		Article a3 = new Stylo( "Stypen", "PlumeS", "Stylo Plume Stypen", 5.5f, 20, "jaune");

		CatalogueManager mger;
		try {
			mger = CatalogueManager.getInstance();
		} catch (BLLException e) {
			System.out.println("*****ERREUR*****");
			System.out.println("cause : "+e.getCause());
			System.out.println("message : "+e.getMessage());
			System.out.println("*****************************");
			System.out.println("Classe : " +e.getStackTrace()[0].getClassName());
			System.out.println("Methode : "+e.getStackTrace()[0].getMethodName());
			System.out.println("ligne : "+e.getStackTrace()[0].getLineNumber());
			return;
		}
		
		//Ajout d'un article au catalogue
		try {
			mger.addArticle(a1);
			
			mger.addArticle(a2);
			
			mger.addArticle(a3);
			
		} catch (BLLException e) {
			System.out.println("*****ERREUR*****");
			System.out.println("cause : "+e.getCause());
			System.out.println("message : "+e.getMessage());
			System.out.println("*****************************");
			System.out.println("Classe : " +e.getStackTrace()[0].getClassName());
			System.out.println("Methode : "+e.getStackTrace()[0].getMethodName());
			System.out.println("ligne : "+e.getStackTrace()[0].getLineNumber());
		}
		System.out.println(mger.getCatalogue());
		
		//Modification d'un article
		try {
			((Stylo) a1).setCouleur("noir");
			((Stylo) a1).setDesignation("Bic bille noir");
			((Stylo) a1).setReference("BBNoir");
			mger.updateArticle(a1);
			System.out.println("Article après modification  : " + a1.toString() );
		} catch (BLLException e) {
			System.out.println("*****ERREUR*****");
			System.out.println("cause : "+e.getCause());
			System.out.println("message : "+e.getMessage());
			System.out.println("*****************************");
			System.out.println("Classe : " +e.getStackTrace()[0].getClassName());
			System.out.println("Methode : "+e.getStackTrace()[0].getMethodName());
			System.out.println("ligne : "+e.getStackTrace()[0].getLineNumber());
		}

		//Suppression d'un article
		try {
			mger.removeArticle(0);
			System.out.println(mger.getCatalogue());
		} catch (BLLException e) {
			System.out.println("*****ERREUR*****");
			System.out.println("cause : "+e.getCause());
			System.out.println("message : "+e.getMessage());
			System.out.println("*****************************");
			System.out.println("Classe : " +e.getStackTrace()[0].getClassName());
			System.out.println("Methode : "+e.getStackTrace()[0].getMethodName());
			System.out.println("ligne : "+e.getStackTrace()[0].getLineNumber());
		}
	}

}

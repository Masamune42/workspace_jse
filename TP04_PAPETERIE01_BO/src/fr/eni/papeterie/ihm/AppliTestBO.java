/**
 * 
 */
package fr.eni.papeterie.ihm;

import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Panier;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;


/**
 * <strong><font color="red">Classe</font> AppliTestBO</strong> possedant les methodes suivantes :
 * <ul><li><strong><font color="green">main (point d'entree) </font></strong>: Test la couche BO pour les classes : <ul><li>Article</li>
 * <li>Stylo</li><li>Ramette</li><li>Panier</li><li>Ligne</li></ul></li></ul>
 * <ul><li><strong><font color="green">afficherCatalogue </font></strong>: Affiche la liste des articles</li></ul> 
 * @author Thierry
 * @version 1.0
 */
public class AppliTestBO {

	/**
	 * <strong><font color="green">Methode</font> main</strong> point d'entree de l'application. Consideree ici comme l'IHM.
	 * @param args
	 * @author Thierry
	 * @version 1.0
	 */
	public static void main(String[] args) {
		//definition d'une liste d'articles et initialisation à NULL
		List<Article> articles=null;
		try {
			//instanciation de la liste d'articles
			articles = new ArrayList<Article>();
			
	        //********************************
	        // tester la gestion des articles
	        //********************************
			
			//instanciation d'un objet de type stylo et affichage des caracteristiques
			Stylo unBic = new Stylo(1, "Bic", "BBOrange","Bic bille Orange", 1.2f, 20, "Bleu");
			System.out.println("\nREM : Affichage d'un article Stylo 'Bic'");
			System.out.println(unBic.toString());
			System.out.println("---------------------------------------------------------------");
			
			//instanciation d'un objet de type ramette et affichage des caractéristiques
			Ramette uneRamette = new Ramette(2, "Clairef", "CRA4S", "Ramette A4 Sup", 9f, 20, 80);
			System.out.println("\nREM : Affichage d'un article Ramette 'Clairefontaine'");
			System.out.println(uneRamette.toString());
			System.out.println("---------------------------------------------------------------");
			
			//ajout des articles à la liste
			articles.add(unBic);
			articles.add(uneRamette);
			
			articles.add(new Stylo(3, "Stypen", "PlumeS", "Stylo Plume Stypen", 5.5f, 20, "jaune"));
			articles.add(new Stylo(4, "Waterman", "WOBGreen", "Waterman Orion Bille vert",(float)5.5, 20, "vert"));
			articles.add(new Stylo(5, "Parker", "PlumeP", "Stylo Plume Parker", 5.5f, 5, "noir"));
			
			System.out.println("\nREM : Affichage du catalogue");
			
			//affichage de la liste des articles
			afficherCatalogue(articles);
			System.out.println("---------------------------------------------------------------");
			
		} catch (Exception e) {
			System.out.println("*****ERREUR*****");
			System.out.println("cause : "+e.getCause());
			System.out.println("message : "+e.getMessage());
			System.out.println("*****************************");
			System.out.println("Classe : " +e.getStackTrace()[0].getClassName());
			System.out.println("Methode : "+e.getStackTrace()[0].getMethodName());
			System.out.println("ligne : "+e.getStackTrace()[0].getLineNumber());
		}
		
		
		//******************
		//tester le Panier
		//******************
		
		//instanciation d'un objet de type Panier
		Panier panier = new Panier();
		
		try {
			panier.addLigne(articles.get(0), 2);
			System.out.println("\nREM : Affichage de l'article de la premiere ligne du panier");
			System.out.println(panier.getLigne(0).getArticle());
			System.out.println("---------------------------------------------------------------");
		} catch (Exception e) {
			System.out.println("*****ERREUR*****");
			System.out.println("cause : "+e.getCause());
			System.out.println("message : "+e.getMessage());
			System.out.println("*****************************");
			System.out.println("Classe : " +e.getStackTrace()[0].getClassName());
			System.out.println("Methode : "+e.getStackTrace()[0].getMethodName());
			System.out.println("ligne : "+e.getStackTrace()[0].getLineNumber());
		}


		try {
			panier.addLigne(articles.get(1), 13);
			panier.addLigne(articles.get(2), 12);
			panier.addLigne(articles.get(3), 5);
			
		} catch (Exception e) {
			System.out.println("*****ERREUR*****");
			System.out.println("cause : "+e.getCause());
			System.out.println("message : "+e.getMessage());
			System.out.println("*****************************");
			System.out.println("Classe : " +e.getStackTrace()[0].getClassName());
			System.out.println("Methode : "+e.getStackTrace()[0].getMethodName());
			System.out.println("ligne : "+e.getStackTrace()[0].getLineNumber());

		}

		try {
			System.out.println("\nREM : Affichage du panier - Ajout");
			System.out.println(panier.toString());
			System.out.println("---------------------------------------------------------------");
			
		}  catch (Exception e) {
			System.out.println("*****ERREUR*****");
			System.out.println("cause : "+e.getCause());
			System.out.println("message : "+e.getMessage());
			System.out.println("*****************************");
			System.out.println("Classe : " +e.getStackTrace()[0].getClassName());
			System.out.println("Methode : "+e.getStackTrace()[0].getMethodName());
			System.out.println("ligne : "+e.getStackTrace()[0].getLineNumber());

		}
		
		try {
			System.out.println("\nREM : Modification du panier");
			//modifier une ligne du panier
			panier.updateLigne(0, 3); // +1 de BBOrange
			panier.updateLigne(1, 12); // -1 de CRA4S
			//supprimer une ligne du panier
			panier.removeLigne(2); // suppression de PlumeS
			
		} catch (Exception e) {
			System.out.println("*****ERREUR*****");
			System.out.println("cause : "+e.getCause());
			System.out.println("message : "+e.getMessage());
			System.out.println("*****************************");
			System.out.println("Classe : " +e.getStackTrace()[0].getClassName());
			System.out.println("Methode : "+e.getStackTrace()[0].getMethodName());
			System.out.println("ligne : "+e.getStackTrace()[0].getLineNumber());

		}

		try {
			System.out.println("\nREM : Affichage du panier - Modification");
			System.out.println(panier.toString());
			System.out.println("---------------------------------------------------------------");
			
		}  catch (Exception e) {
			System.out.println("*****ERREUR*****");
			System.out.println("cause : "+e.getCause());
			System.out.println("message : "+e.getMessage());
			System.out.println("*****************************");
			System.out.println("Classe : " +e.getStackTrace()[0].getClassName());
			System.out.println("Methode : "+e.getStackTrace()[0].getMethodName());
			System.out.println("ligne : "+e.getStackTrace()[0].getLineNumber());

		}
		
		
	}

	
	/**
	 * <strong><font color="green">Methode</font> afficherCatalogue</strong> permettant d'afficher le catalogue des articles.
	 * @param articles : liste des articles
	 * @author Thierry
	 * @version 1.0
	 */
	private static void afficherCatalogue(List<Article> articles) {
		for (Article article : articles) {
			System.out.println(article.toString());
		}
		
	}
	

}

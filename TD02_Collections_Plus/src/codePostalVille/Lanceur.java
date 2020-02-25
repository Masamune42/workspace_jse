package codePostalVille;

import metier.EnsembleDeVilles;
//import metier.EnsembleDeVillesListArrayListNG;
//import metier.EnsembleDeVillesListHashtableNG;

public class Lanceur {

	public static void main(String[] args) {
		System.out.println("********** UTILISATION TABLEAU **********");
		EnsembleDeVilles villes = new EnsembleDeVilles();
 
		//System.out.println("********** UTILISATION ARRAYLIST **********");
		//EnsembleDeVillesListArrayListNG villes = new EnsembleDeVillesListArrayListNG();
		
		//System.out.println("********** UTILISATION HASHTABLE **********");
		//EnsembleDeVillesListHashtableNG villes = new EnsembleDeVillesListHashtableNG();

		villes.ajouter("Saint-Herblain");
		System.out.println("Affichage des villes : " + villes);
        villes.ajouter("Nantes");
		System.out.println("Affichage des villes : " + villes);
        villes.ajouter("Rezé");
		System.out.println("Affichage des villes : " + villes);
        villes.ajouter("Couéron");
		System.out.println("Affichage des villes : " + villes);
        villes.ajouter("Sautron");
		System.out.println("Affichage des villes : " + villes);
        villes.ajouter("Orvault");
		System.out.println("Affichage des villes : " + villes);
        villes.retirer("Rezé");
		System.out.println("Affichage des villes : " + villes);
        villes.retirer("Sautron");
		System.out.println("Affichage des villes : " + villes);
		
		System.out.println("********** UTILISATION TABLEAU **********");
		int position = villes.rechercherPosition("Nantes");
		System.out.println("position de Nantes : "+position);

		//System.out.println("********** UTILISATION ARRAYLIST **********");
		//int position = villes.rechercherPosition("Orvault");
		//System.out.println("position de Orvault : "+position);

		//System.out.println("********** UTILISATION HASHTABLE **********");
		//String ville = villes.rechercher(44400);
		//System.out.println("ville rechercher ayant cp 44400 : "+ville);

	}

}

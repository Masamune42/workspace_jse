package codePostalVille;

import java.util.ArrayList;
import java.util.List;

public class EnsembleDeVillesListArrayList {
	private List<String> mesVilles = new ArrayList<String>();
	
	public void ajouter(String nomVille) {
		mesVilles.add(nomVille);
	}
	
	public void retirer(String ville) {
			mesVilles.remove(ville);
	}
	
	public int rechercherPosition(String ville) {
		return mesVilles.indexOf(ville);
	}

	/** 
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.join("-", mesVilles);
	}

	public static void main(String[] args) {
		EnsembleDeVillesListArrayList villes = new EnsembleDeVillesListArrayList();
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
		
		int position = villes.rechercherPosition("Nantes");
		System.out.println("position de Nantes : " + position);
		
	}
}

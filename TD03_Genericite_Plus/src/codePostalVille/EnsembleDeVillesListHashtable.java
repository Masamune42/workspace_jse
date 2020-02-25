package codePostalVille;

import java.util.Hashtable;
import java.util.Map;

public class EnsembleDeVillesListHashtable {
	private Map<Integer, String> mesVilles = new Hashtable<Integer, String>();
	
	public void ajouter(String nomVille, int cp) {
		mesVilles.put(cp, nomVille);
	}
	
	public void retirer(int cp) {
			mesVilles.remove(cp);
	}
	
	public String rechercher(int cp) {
		return mesVilles.get(cp);
	}

	/** 
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.join("-", mesVilles.values());
	}

	public static void main(String[] args) {
		EnsembleDeVillesListHashtable villes = new EnsembleDeVillesListHashtable();
        villes.ajouter("Saint-Herblain", 44800);
		System.out.println("Affichage des villes : " + villes);
        villes.ajouter("Nantes", 44000);
		System.out.println("Affichage des villes : " + villes);
        villes.ajouter("Rezé", 44400);
		System.out.println("Affichage des villes : " + villes);
        villes.ajouter("Couéron", 44280);
		System.out.println("Affichage des villes : " + villes);
        villes.ajouter("Orvault", 44700);
		System.out.println("Affichage des villes : " + villes);
        villes.retirer(44220);
		System.out.println("Affichage des villes : " + villes);
        villes.retirer(44800);
		System.out.println("Affichage des villes : " + villes);
		
		String ville = villes.rechercher(44400);
		System.out.println("ville rechercher ayant cp 44400 : "+ville);
		
	}
}

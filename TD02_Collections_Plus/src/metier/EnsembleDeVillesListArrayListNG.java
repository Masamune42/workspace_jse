package metier;

import java.util.ArrayList;
import java.util.List;

public class EnsembleDeVillesListArrayListNG {
	@SuppressWarnings("rawtypes")
	private List mesVilles = new ArrayList();
	
	@SuppressWarnings("unchecked")
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
	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		return String.join("-", mesVilles);
	}

}

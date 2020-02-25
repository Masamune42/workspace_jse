package metier;

import java.util.Hashtable;
import java.util.Map;

public class EnsembleDeVillesListHashtableNG {
	@SuppressWarnings("rawtypes")
	private Map mesVilles = new Hashtable();
	
	@SuppressWarnings("unchecked")
	public void ajouter(String nomVille, int cp) {
		mesVilles.put(cp, nomVille);
	}
	
	public void retirer(int cp) {
			mesVilles.remove(cp);
	}
	
	public String rechercher(int cp) {
		return (String)mesVilles.get(cp);
	}

	/** 
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		return String.join("-", mesVilles.values());
	}

}

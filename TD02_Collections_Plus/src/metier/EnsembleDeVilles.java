package metier;

public class EnsembleDeVilles {
	private String[] mesVilles = new String[10];
	
	public void ajouter(String nomVille) {
		int position = rechercheCaseLibre();
		mesVilles[position] = nomVille;
	}
	
	private int rechercheCaseLibre() {
		int pos= 0;
		while(pos <mesVilles.length && mesVilles[pos]!=null)
			pos++;
		if(pos == mesVilles.length)
			throw new IndexOutOfBoundsException("Capacité dépassée");
		return pos;
	}
	
	public void retirer(String ville) {
		int position = rechercherPosition(ville);
		if(position>=0)
			mesVilles[position] = null;
	}
	
	public int rechercherPosition(String ville) {
		int pos= 0;
		while(pos <mesVilles.length && mesVilles[pos]!=ville)
			pos++;
		if(pos == mesVilles.length)
			pos = -1;
		return pos;
	}

	/** 
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.join("-", mesVilles);
	}

}

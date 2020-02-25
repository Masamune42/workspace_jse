package fr.eni.arborescence;

public abstract class Element {
	protected String nom;
	protected String chemin;
	
	public Element(String nom){
		//this(null, nom);
		this.nom = nom;
		this.chemin = "\\";
	}
	
//	public Element(Repertoire parent, String nom){
//		this.nom = nom;
//		this.chemin = parent!=null?(parent.chemin +parent.nom + "\\"):"\\";
//	}
	
	public abstract void recherche(String nomRecherche);
			
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}
}

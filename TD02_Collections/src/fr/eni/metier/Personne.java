package fr.eni.metier;

// une equipe de basket est compos�e de personnes
public class Personne {

	//Attributs
	private String nom;

	//getteur et setteur
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	//constructeur par defaut
	public Personne() {
		super();
	}

	// constructeur surcharg�
	public Personne(String nom) {
		super();
		this.nom = nom;
	}

	// methodes
	@Override
	public String toString() {
		return  "Personne [nom="+getNom()+"]";
	}
	
}

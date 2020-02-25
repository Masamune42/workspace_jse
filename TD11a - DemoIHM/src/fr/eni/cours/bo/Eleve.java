package fr.eni.cours.bo;

public class Eleve {

	//attributs  d'instance
	private String nom;
	private String prenom;
	private String adresse;
	
	//constructeurs
	public Eleve() {
	}
	
	public Eleve(String nom, String prenom, String adresse) {
		setNom(nom);
		setPrenom(prenom);
		setAdresse(adresse);
	}

	//autres methodes
	@Override
	public String toString() {
		//return "Eleve [nom=" + getNom() + ", prenom=" + getPrenom() + ", adresse=" + getAdresse() + "]\n";
		return String.format("Eleve >>> \n\t nom : %s \n\t prenom : %s \n\t adresse : %s \n", getNom(),getPrenom(),getAdresse());
	}

	//Methodes accesseurs et mutateurs
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
	
	
}

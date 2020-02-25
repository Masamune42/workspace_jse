package fr.eni_ecole.cours.bo;

/**
 * Classe metier Eleve
 * @author Thierry
 *
 */
public class Eleve {
	
	//les attributs
	private String nom;
	private String prenom;
	private String adresse;

	//constructeurs
	public Eleve(String nom, String prenom, String adresse) {
		setNom(nom);
		setPrenom(prenom);
		setAdresse(adresse);
	}
	public Eleve() {
	}
	
	//autres methodes
	@Override
	public String toString() {
		return String.format("Eleve -> \n\t nom : %s \n\t prenom : %s \n\t adresse : %s \n", getNom(),getPrenom(),getAdresse());
	}

	//methodes setters / getters
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

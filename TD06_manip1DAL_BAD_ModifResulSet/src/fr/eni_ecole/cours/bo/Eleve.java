package fr.eni_ecole.cours.bo;

public class Eleve {
	//les attributs
	private Integer id;
	private String nom;
	private String prenom;
	private String adresse;
	//methodes setters / getters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	
	public Eleve(String nom, String prenom, String adresse, String nele) {
		setNom(nom);
		setPrenom(prenom);
		setAdresse(adresse);
	}
	public Eleve() {
		super();
	}
	@Override
	public String toString() {
		return "Eleve [getNom()=" + getNom()
				+ ", getPrenom()=" + getPrenom() + ", getAdresse()="
				+ getAdresse() +  "]";
	}

}

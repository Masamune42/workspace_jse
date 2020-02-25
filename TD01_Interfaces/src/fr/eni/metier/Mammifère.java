package fr.eni.metier;

public class Mammifère {

	//Attributs
	private String nom;

	//getter et setter
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	// constructeur par defaut
	public Mammifère() {
		super();
	}

	// constructeur surchargé
	public Mammifère(String nom) {
		super();
		this.nom = nom;
	}
	
	// permet de factoriser le comportement communiquer dans la classe parente
	// car la communication est une capacité partagée par tous les mammifères
	//methodes
	public void communiquer(){
		System.out.println("mammifère " + getNom() + "communique.");
	}
	
}

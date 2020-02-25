package fr.eni.metier;

public class Mammif�re {

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
	public Mammif�re() {
		super();
	}

	// constructeur surcharg�
	public Mammif�re(String nom) {
		super();
		this.nom = nom;
	}
	
	// permet de factoriser le comportement communiquer dans la classe parente
	// car la communication est une capacit� partag�e par tous les mammif�res
	//methodes
	public void communiquer(){
		System.out.println("mammif�re " + getNom() + "communique.");
	}
	
}

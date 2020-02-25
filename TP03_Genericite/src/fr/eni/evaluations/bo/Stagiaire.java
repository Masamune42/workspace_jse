package fr.eni.evaluations.bo;


public class Stagiaire {
	
	private int noStagiaire;	
	private String prenom,  nom, email, motDePasse;
	
	//Constructeur vide
	public Stagiaire(){
		super();
	}
	
	//Constructeur avec paramètres
	public Stagiaire(
					 int noStagiaire,
					 String prenom, 
					 String nom, 
					 String email, 
					 String motDePasse
					 ) 
			{		
		this();
		this.setNoStagiaire(noStagiaire);
		this.setPrenom(prenom);
		this.setNom(nom);
		this.setEmail(email);
		this.setMotDePasse(motDePasse);
		

	}

	//Getters et Setters
	public int getNoStagiaire() {
		return noStagiaire;
	}
	
	

	public void setNoStagiaire(int noStagiaire) {
		this.noStagiaire = noStagiaire;
	}

	public String getEmail() {
		return email;
	}
	
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	@Override
	public String toString() {
		return "Stagiaire [noStagiaire=" + noStagiaire + ", prenom=" + prenom + ", nom=" + nom + ", email=" + email
				+ ", motDePasse=" + motDePasse + "]";
	}




	
	
}

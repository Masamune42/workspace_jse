package fr.eni.papeterie.bo;

/**
 * c'est une classe fille
 * @author Thierry
 *
 */
public class Stylo extends Article {
	
	//attribut d'instance
	private String couleur;


	//les constructeurs
	public Stylo() {
	}
	
	public Stylo(Integer idArticle, String marque, String ref, String designation, float pu, int qte, String couleur)  {
		
		setIdArticle(idArticle);
		setMarque(marque);
		setReference(ref);
		setDesignation(designation);
		setPrixUnitaire(pu);
		setQteStock(qte);
		
		setCouleur(couleur);
	}
	
	// surcharge de constructeur
	// this est le premier ordre à invoquer lors de son emploi dans la methode
	// ici on appelle le constructeur precedent, remarquez le null affecté pour idArticle
	public Stylo(String marque, String ref, String designation, float pu, int qte, String couleur)  {
		this(null, marque, ref, designation, pu, qte, couleur);
	}


	//autres methodes
	//affichage avec String.format
	//je fais appel à la methode toString() redefinie dans la classe mere
	@Override
	public String toString() {
		String s = String.format("%s Stylo [Couleur=%s]", super.toString(), getCouleur());
		return s;
	}

	//accesseurs et mutateurs
	public String getCouleur() {
		return this.couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

}

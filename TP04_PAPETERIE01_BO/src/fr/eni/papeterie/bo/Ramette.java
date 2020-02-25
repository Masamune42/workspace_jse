package fr.eni.papeterie.bo;

/**
 * ceci est une classe fille
 * @author Thierry
 *
 */
public class Ramette extends Article {

	//attribut d'instance
	private int grammage;


	//les constructeurs
	public Ramette() {
		
	}
	
	public Ramette(Integer idArticle, String marque, String ref,  String designation, float pu, int qte, int grammage) {
		setIdArticle(idArticle);
		setReference(ref);
		setMarque(marque);
		setDesignation(designation);
		setPrixUnitaire(pu);
		setQteStock(qte);
		setGrammage(grammage);
	}
	
	public Ramette( String marque, String ref,  String designation, float pu, int qte, int grammage) {
		this(null, marque, ref, designation, pu, qte, grammage);
	}

	//autres methodes
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append(" ");
		buffer.append("Ramette [grammage=");
		buffer.append(getGrammage());
		buffer.append("]");
		return buffer.toString();
	}

	//accesseurs et mutateurs
	public int getGrammage() {
		return grammage;
	}

	public void setGrammage(int grammage) {
		this.grammage = grammage;
	}

}

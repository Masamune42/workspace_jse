package fr.eni.papeterie.bo;

/**
 * Nous avons ici une classe mere abstraite, donc qui ne pourra pas être instanciée. Il est alors inutile de définir des constructeurs
 * @author thierry
 *
 */
public abstract class Article {
	
	//Attributs d'instance
	private Integer idArticle;
	private String reference;
	private String marque;
	private String designation;
	private float prixUnitaire;
	private int qteStock;

	//Methodes d'instance
	@Override
	public String toString() {
		return "Article [idArticle="+ getIdArticle() +", reference=" + getReference() + ", marque=" + getMarque() + ", designation=" + getDesignation()
				+ ", prixUnitaire=" + getPrixUnitaire() + ", qteStock=" + getQteStock() + "]";
	}

	//Getters et Setters
	public Integer getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(Integer idArticle) {
		this.idArticle = idArticle;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference)  {
		this.reference = reference;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque)  {
		this.marque = marque;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation)  {
		this.designation = designation;
	}

	public float getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(float prixUnitaire)  {
		this.prixUnitaire = prixUnitaire;
	}

	public int getQteStock() {
		return qteStock;
	}

	public void setQteStock(int qteStock) {
		this.qteStock = qteStock;
	}

}

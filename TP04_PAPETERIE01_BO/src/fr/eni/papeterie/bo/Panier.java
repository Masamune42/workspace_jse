package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * Le panier stocke les articles selectionnes par l'utilisateur au cours de 
 * sa navigation. Le panier n'est pas sauvegarde.
 * @author Thierry
 */
public class Panier {

	//Attributs d'instance
	private List<Ligne> lignesPanier;
	private float montant;

	//Constructeurs
	public Panier(){
		lignesPanier = new ArrayList<Ligne>();
	}

	
	//Getters et Setters
	/**
	 * @return les lignesPanier
	 */
	public final List<Ligne> getLignesPanier() {
		return lignesPanier;
	}


	/**
	 * Ajouter une ligne au panier. Le prix de la ligne est calcule (qte*prix)
	 * @param article
	 * @param qte
	 * 
	 */
	public void addLigne(Article article, int qte) {

		Ligne ligneAdding = new Ligne(article, qte);
		lignesPanier.add(ligneAdding);
	}


	/**
	 * Retourne la ligne selectionnee du Panier ou null si pas trouvee
	 * @param index
	 * @return
	 */
	public final Ligne getLigne(int index){
		return lignesPanier.get(index);
	}


	/**
	 * Presenter le detail du panier
	 */
	@Override
	public String toString() {
		setMontant(0f);
		StringBuffer bf =new StringBuffer();
		bf.append("Panier : \n\n");
		for (Ligne ligne : lignesPanier) {
			if (ligne != null){
				setMontant(getMontant()+(ligne.getPrix()*ligne.getQte()));
				
				bf.append("ligne " + lignesPanier.indexOf(ligne) + " :\t");
				bf.append(ligne.toString());
				bf.append("\n");
			} else break;
		}
		bf.append("\nValeur du panier : " + getMontant());
		bf.append("\n\n");
		return bf.toString();
	}


	/**
	 * Supprimer la ligne du panier.
	 * La quantite en stock augmente de la quantite associee a la ligne
	 * @param numero
	 */
	public void removeLigne(int index){	
			lignesPanier.remove(index);
	}

	/**
	 * Modifier la quantité placée dans le panier
	 * La quantité en stock augment ou diminue en fonction de cette nouvelle qté
	 * @param index
	 * @param newQte
	 * @throws PlusEnStockException 
	 */
	public void updateLigne(int index, int newQte)  {
		this.getLigne(index).setQte(newQte);
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

}

























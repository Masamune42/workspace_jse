package fr.eni.metier;

public class Voiture implements Comparable<Voiture> {
	private String marque;
	private String modele;
	private int vitesseMaximum;

	public Voiture(String marque, String modele, int vitesseMaximum) {
		setMarque(marque);
		setModele(modele);
		setVitesseMaximum(vitesseMaximum);
	}
	
	/** 
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getMarque() + " " + getModele() + " (" + getVitesseMaximum() + "km/h)";
	}

	/** 
	 * {@inheritDoc}
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Voiture autre) {
		return vitesseMaximum - autre.vitesseMaximum;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public int getVitesseMaximum() {
		return vitesseMaximum;
	}

	public void setVitesseMaximum(int vitesseMaximum) {
		this.vitesseMaximum = vitesseMaximum;
	}
	
	
}

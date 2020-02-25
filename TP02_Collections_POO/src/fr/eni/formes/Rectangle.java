package fr.eni.formes;

public class Rectangle extends Forme{


	private double longueur;
	private double largeur;
	
	
	public Rectangle(double longueur, double largeur) {
		this.longueur = longueur;
		this.largeur = largeur;
	}
	
	
	public double getLongueur() {
		return longueur;
	}

	public void setLongueur(double longueur) {
		this.longueur = longueur;
	}

	public double getLargeur() {
		return largeur;
	}

	public void setLargeur(float largeur) {
		this.largeur = largeur;
	}

	@Override
	public double calculerAire() {
		return longueur*largeur;
	}

	@Override
	public String toString() {
		return "Rectangle [longueur=" + longueur + ", largeur=" + largeur + "]";
	}
}

package fr.eni.formes;

public class Triangle extends Forme {

	private double base ;
	
	private double hauteur;

	public Triangle(double base, double hauteur) {
		this.base = base;
		this.hauteur = hauteur;
	}
	
	@Override
	public double calculerAire() {
		
		return base*hauteur/2;
	}
	
	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}

	public double getHauteur() {
		return hauteur;
	}

	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}

	@Override
	public String toString() {
		return "Triangle [base=" + base + ", hauteur=" + hauteur + "]";
	}


}

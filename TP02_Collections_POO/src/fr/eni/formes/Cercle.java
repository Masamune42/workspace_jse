package fr.eni.formes;

public class Cercle extends Forme {

	private double rayon;
	
	
	public Cercle(double rayon) {
		this.rayon = rayon;
	}
	
	@Override
	public double calculerAire() {
		// TODO Auto-generated method stub
		return Math.PI * rayon * rayon;
	}

	@Override
	public String toString() {
		return "Cercle [rayon=" + rayon + "]";
	}

	
}

package fr.eni.autreDemoInterfaces;

import fr.eni.metier.Appartement;
import fr.eni.metier.Maison;

public class Lanceur {

	public static void main(String[] args) {
		Appartement appart = new Appartement();
		Maison demeure = new Maison();
		
		appart.affiche();
		demeure.affiche();
	}

}

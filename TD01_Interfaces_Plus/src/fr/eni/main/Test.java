package fr.eni.main;

import java.util.Arrays;

import fr.eni.metier.Voiture;

public class Test {

	public static void main(String[] args) {
		Voiture[] voitures = {
				new Voiture("Mercedes", "SLR", 334),
				new Voiture("Bugatti", "Veyron Supersport", 434),
				new Voiture("Saleen", "S7 Twin-Turbo", 399),
				new Voiture("Lamborghini", "Murcielago", 334),
				new Voiture("Pagani", "Zonda", 340),
				new Voiture("Porsche", "Carrera GT", 330),
				new Voiture("Scheley Super Cars", "Ultimate Aero TT", 414),
				new Voiture("McLaren", "F1", 391)
		};
		for(Voiture v : voitures) {
			System.out.println(v);
		}
		System.out.println(" **** Tri du tableau ****");
		Arrays.sort(voitures);
		for(Voiture v : voitures) {
			System.out.println(v);
		}
	}

}

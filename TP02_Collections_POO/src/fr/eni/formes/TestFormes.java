package fr.eni.formes;

import java.util.ArrayList;
import java.util.List;

public class TestFormes {

	public static void main(String[] args) {
		List<Forme> formes = new ArrayList<Forme>();
		Forme f = new Rectangle(10f, 10f );
		formes.add(f);
		f = new Rectangle(25f, 11f );
		formes.add(f);
		f = new Cercle(10);
		formes.add(f);
		f = new Triangle(11, 45);
		formes.add(f);
		
		for(Forme forme: formes){
			System.out.println("Aire de : " + forme + ": " + forme.calculerAire());
		}
		
	}

}

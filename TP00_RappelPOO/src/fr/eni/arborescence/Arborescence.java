package fr.eni.arborescence;

public class Arborescence {

	public static void main(String[] args) {
		// r1 
		//  |--f1
		//  |--f2
		//  |--r2
		//      |--r4
		//	        |--f1
		//  |--r3
		//      |--f4
		Repertoire r1 = new Repertoire("r1");
		Repertoire r2 = new Repertoire("r2");
		Repertoire r3 = new Repertoire("r3");
		Repertoire r4 = new Repertoire("r4");
		Fichier f1 = new Fichier("f1");
		Fichier f2 = new Fichier("f2");
		Fichier f3 = new Fichier("f1");
		Fichier f4 = new Fichier("f4");
		
		r1.addElements(f1);
		r1.addElements(f2);
		r1.addElements(r2);
		r1.addElements(r3);
		
		r2.addElements(r4);
		r4.addElements(f3);
		r3.addElements(f4);
	
		//Recherche fichier f1
		System.out.println("Recherche f1");
		r1.recherche("f1");
		
		System.out.println("Recherche r4");
		r1.recherche("r4");
		
		
	}

}

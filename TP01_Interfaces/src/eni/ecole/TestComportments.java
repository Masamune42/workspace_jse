package eni.ecole;

public class TestComportments {

	public static void main(String[] args) {
		
		System.out.println("***** cheval *****");
		Cheval tornado = new Cheval();
		tornado.communiquer();
		tornado.dormir();
		tornado.galoper();
		
		System.out.println("***** humain *****");
		Humain zorro = new Humain();
		zorro.communiquer();
		zorro.dormir();
		zorro.siffler();
		
// DECOMMENTER POUR LA QUESTION 6 		
		System.out.println("***** oiseau *****");
		Oiseau titi = new Oiseau();
		titi.siffler();
		titi.communiquer();
		titi.dormir();		

		//l'interface est aussi un type
		System.out.println("***** animal communique comme un cheval *****");
		Animal anonyme1 = tornado;
		anonyme1.communiquer() ;
		System.out.println("***** animal communique comme un humain *****");
		Animal anonyme2 = zorro ;
		anonyme2.communiquer() ;
		
		//instance polymorphe, à la fois humain et animal
		System.out.println("***** l'humain/animal siffle comme un humain *****");
		Humain inconnu = (Humain)anonyme2 ;
		inconnu.siffler() ;
		
		//le centaure
		System.out.println("***** centaure *****");
		Centaure centaure = new Centaure();
		centaure.communiquer();
		centaure.dormir();
		centaure.siffler();
		centaure.galoper();
	}

}

package fr.eni.demoInterfaces;

import fr.eni.metier.Chat;
import fr.eni.metier.ChauveSouris;
import fr.eni.metier.Exocet;
import fr.eni.metier.Volant;

public class ZooApp {

	//voyons comment le concept d'interface nous permet de faire de l'héritage multiple
	//creons une application qui gere les animaux
	public static void main(String[] args) {
		//on crée un chat qui miaule
		Chat c = new Chat();
		//c.setNom("chat");
		c.communiquer();

		//on crée une chauve-souris qui communique par ultra-sons
		ChauveSouris cs = new ChauveSouris();
		cs.communiquer();
		
		cs.voler();
		
		// Une interface, comme une classe, représente un type et peut donc servir
		// à declarer une variable
		
		Volant cs2 = cs; //une instance de chauve souris a donc 2 types.
		//on dit que l'objet est polymorphe
		cs2.voler();
		
		// comme pour les classes les interfaces peuvent hériter d'autres interfaces
		Exocet e = new Exocet();
		e.nager();
		e.voler();
		
	}

}

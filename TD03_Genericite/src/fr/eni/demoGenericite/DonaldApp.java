package fr.eni.demoGenericite;

import java.util.ArrayList;
import java.util.List;

public class DonaldApp {

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public static void main(String[] args) {
		// ************** Avant les g�n�riques ********************
		List liste = new ArrayList();
		liste.add("riri");
		liste.add("fifi");
		liste.add("loulou");
		
		liste.add(234);
		
		String canard = (String)liste.get(1); //Obligation de faire un cast
		
		//String canard2 = (String)liste.get(3); // exception ClassCastException

		//************* solution apport�e par les Generics ***************
		List<String> listeG = new ArrayList<String>();
		listeG.add("riri");
		listeG.add("fifi");
		listeG.add("loulou");
	
		//listeG.add(234); //on ne peut pas entrer autre chose que du string (typ�)
							// ne compile pas => on evite les bugs
		
		String caneton = listeG.get(1); //pas besoin de caster
		
		
		List<String> listeGeneric = new ArrayList<String>();
		// depuis le JDK 7 il y a inf�rence de type : c'est � dire que le type
		// peut �tre omis de l'instanciation
		List<String> listeGInference = new ArrayList<>();
		
		
	}

}

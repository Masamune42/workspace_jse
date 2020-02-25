package codePostalVille;

import java.util.ArrayList;
import java.util.Hashtable;

public class TestNG {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
        System.out.println("*** ArrayList ***");
        ArrayList al = new ArrayList();

        al.add("mot");
        al.add(145);
        al.add(788);

        System.out.println("Affichage de l'ensemble des valeurs :");
        for(Object e : al) {
            System.out.println(e.toString());
        }
        
        System.out.println("*** Hashtable ***");
        Hashtable ht = new Hashtable();

        ht.put(1, "mot");
        ht.put('a', 145);
        ht.put("chaine", 788);
        ht.put("chaine", 128);
        //Remplace la valeur pr�c�dente car il y a d�j� une cl� "chaine"

        System.out.println("valeur associ� � la cl� '1' dans la hashtable : " + ht.get(1));
        System.out.println("Affichage de l'ensemble des valeurs :");
        for(Object e : ht.values()) {
            System.out.println(e.toString());
        }

	}

}

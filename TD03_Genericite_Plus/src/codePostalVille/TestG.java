package codePostalVille;

import java.util.ArrayList;
import java.util.Hashtable;

public class TestG {

	public static void main(String[] args) {
        System.out.println("*** ArrayList ***");
        ArrayList<String> al = new ArrayList<String>();

        al.add("mot");
        al.add("145");
        al.add("788");

        System.out.println("Affichage de l'ensemble des valeurs :");
        for(String e : al) {
            System.out.println(e);
        }
        
        System.out.println("*** Hashtable ***");
        Hashtable<Integer, String> ht = new Hashtable<Integer, String>();

        ht.put(1, "mot");
        ht.put(145, "145");
        ht.put(788, "788");
        ht.put(128, "chaine");
        //Remplace la valeur pr√©c√©dente car il y a d√©j√† une cl√© "chaine"

        System.out.println("valeur associÈ ‡ la clÈ '1' dans la hashtable : " + ht.get(1));
        System.out.println("Affichage de l'ensemble des valeurs :");
        for(String e : ht.values()) {
            System.out.println(e);
        }
	}

}

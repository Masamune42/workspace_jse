package manipChaineCaracteres;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConjugaisonVerbe {

	public static void main(String[] args) {
		String sujets[]={"je","tu","il/elle","nous","vous","ils/elles"};
		String terminaisons[]={"e","es","e","ons","ez","ent"};
		String verbe;
		int nbLettres;
	
		System.out.println("Donnez un verbe regulier du premier groupe : ");
		try {
			while (true){
				InputStreamReader lecteur = new InputStreamReader(System.in);
				BufferedReader entree = new BufferedReader(lecteur);
				verbe = entree.readLine();
				nbLettres = verbe.length();
				String fin = verbe.substring(nbLettres-2, nbLettres);
				if (fin.equals("er")){
					break;
				}
				System.err.println("*** il ne se termine pas par er - donnez en un autre : ");
			}
			String rad = verbe.substring(0,nbLettres-2);
			int n = terminaisons.length;
			for (int i=0; i<n; i++) {
				System.out.println(sujets[i] + " " + rad + terminaisons[i]);
			}
		}catch (IOException e){
			System.exit(0);
		}
		 
	}

}

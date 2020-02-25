package manipChaineCaracteres;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ComptageVoyelles {

	public static void main(String[] args) {
		char voy[]={'a','e','i','o','u','y'};
		int nbVoy[] = new int [voy.length];
		for (int i=0; i<nbVoy.length;i++){
			nbVoy[i] =0;
		}
		System.out.println("donner un mot :");
		String ligneLue = null;
		try {
			InputStreamReader lecteur = new InputStreamReader(System.in);
			BufferedReader entree = new BufferedReader(lecteur);
			ligneLue = entree.readLine();
			ligneLue = ligneLue.toLowerCase();
			for (int i=0; i<ligneLue.length(); i++){
				for (int j=0; j<voy.length; j++){
					if (ligneLue.charAt(i) ==voy[j]) {
						nbVoy[j]++;
					}
				}
			}
			System.out.println("il comporte : ");
			for (int i=0 ; i<voy.length; i++){
				System.out.println(nbVoy[i] + " fois la lettre " + voy[i]);
			}
		}catch (IOException e){
			System.exit(0);
		}
	}

}

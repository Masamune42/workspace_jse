package manipChaineCaracteres;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConversionEntierEnChaineCaracteres {

	public static void main(String[] args) {
		System.out.println("donner un nombre entier :");
		int n = 0;
		String ligneLue = null;
		try {
			InputStreamReader lecteur = new InputStreamReader(System.in);
			BufferedReader entree = new BufferedReader(lecteur);
			n = Integer.parseInt(entree.readLine());
			ligneLue = String.valueOf(n);
			for (int i=0; i<ligneLue.length(); i++)
				System.out.println(ligneLue.charAt(i));
		}catch (NumberFormatException e){
			System.err.println("erreur de donnée");
			System.exit(0);
		}catch (IOException e){
			System.exit(0);
		}

	}

}

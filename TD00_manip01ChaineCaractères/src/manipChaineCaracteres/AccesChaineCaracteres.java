package manipChaineCaracteres;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.Scanner;

public class AccesChaineCaracteres {

	public static void main(String[] args) {
		System.out.println("donner une chaine :");
		String ligneLue = null;
//		try {
//			InputStreamReader lecteur = new InputStreamReader(System.in);
//			BufferedReader entree = new BufferedReader(lecteur);
//			ligneLue = entree.readLine();
			Scanner entree = new Scanner(System.in);
			ligneLue =  entree.nextLine();
			System.out.println("Premier caract�re = "+ligneLue.charAt(0));
			System.out.println("Dernier caract�re = "+ligneLue.charAt(ligneLue.length()-1));
//		}catch (IOException e){
//			System.exit(0);
//		}
			entree.close();
	}

}

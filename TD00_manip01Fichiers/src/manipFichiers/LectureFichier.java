package manipFichiers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LectureFichier {

	public static void main(String[] args) {
		int longMaxNom = 20;
		int longMaxPrenom = 20;
		String nom, prenom;
		char[] cNom = new char[longMaxNom];
		char[] cPrenom = new char[longMaxPrenom];
		int annee;
		int i;		
		String nomFichier;
		System.out.println("Nom de fichier à lister : ");
		try {
			InputStreamReader lecteur = new InputStreamReader(System.in);
			BufferedReader saisie = new BufferedReader(lecteur);
			nomFichier = saisie.readLine();
			//on associe un objet de type DataInputStrean (nommé entree) à un fichier dont le nom
			//est fourni par l'utilisateur dans la chaine nomFichier (ici : c:\temp\essai)
			DataInputStream entree = new DataInputStream(new FileInputStream(nomFichier));
			System.out.println("*** liste du fichier ***");
			boolean eof = false; //sera mis à true par le gestionnaire d'exception EOFile
			while (!eof){  
				try{
					//******************* solution 1 *************************
					//lecture d'infos à l'aide des methodes readChar ou readInt de la classe DataInputStream
					for (i=0; i<longMaxNom; i++){
						cNom[i]= entree.readChar();
					}
					for (i=0; i<longMaxPrenom; i++){
						cPrenom[i]=entree.readChar();
					}
					annee=entree.readInt();
					//affichage infos
					for (i=0; i<longMaxNom; i++){
						System.out.print(cNom[i]);
					}
					System.out.print(" ");
					for (i=0; i<longMaxPrenom; i++){
						System.out.print(cPrenom[i]);
					}
					System.out.print(" ");
					System.out.println(annee);
					//**********************************************************
					//******************** solution 2 ****************************
					// lecture des infos
					nom=entree.readUTF();
					prenom=entree.readUTF();
					annee=entree.readInt();
					//affichage infos
					System.out.print(nom + " ");
					System.out.print(prenom + " ");
					System.out.println(annee);
					//************************************************************
				}catch(EOFException e){ //la gestion de la fin de fichier est réalisée en interceptant l'exception EOFException
					eof = true;
				}
			}
			entree.close();
			System.out.println("*** fin liste fichier ***");
		
		}catch (IOException e){
			System.exit(0);
		}catch (NumberFormatException e){
			System.err.println("*** erreur de donnees ***");
			System.exit(0);
		}

	}

}

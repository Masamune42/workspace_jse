package manipFichiers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreationFichier {

	public static void main(String[] args) {
		int longMaxNom = 20;
		int longMaxPrenom = 20;
		String nom, prenom;
		char[] cNom = new char[longMaxNom];
		char[] cPrenom = new char[longMaxPrenom];
		int annee;
		
		String nomFichier;
		System.out.println("Nom de fichier à créer : ");
		try {
			InputStreamReader lecteur = new InputStreamReader(System.in);
			BufferedReader entree = new BufferedReader(lecteur);
			nomFichier = entree.readLine();
			//on associe un objet de type DataOutputStrean (nommé sortie) à un fichier dont le nom
			//est fourni par l'utilisateur dans la chaine nomFichier (ici : c:\temp\essai)
			DataOutputStream sortie = new DataOutputStream(new FileOutputStream(nomFichier));
			int i;
			int num=0; //pour compter les différents enregistrements
			while (true){ //on s'arretera sur un nom vide
				num++;
				System.out.println("Nom" + num + " : ");
				entree = new BufferedReader(lecteur);
				nom = entree.readLine();
				if (nom.length() == 0){
					break;
				}
				System.out.println("Prenom : ");
				entree = new BufferedReader(lecteur);
				prenom = entree.readLine();
				System.out.println("Année de naissance : ");
				entree = new BufferedReader(lecteur);
				String dateNaiss = entree.readLine();
				annee = Integer.parseInt(dateNaiss);
				//**********solution 1 *************
				//on remplit les tab préalablement d'espaces
				for (i=0; i<longMaxNom; i++){
					cNom[i]= ' ';
				}
				for (i=0; i<longMaxPrenom; i++){
					cPrenom[i]= ' ';
				}
				//transfert nom et prenom dans tab de char (20 caractères maxi - terminé par des espaces)
				for (i=0; (i<nom.length())&&(i<longMaxNom); i++){
					cNom[i]=nom.charAt(i);
				}
				for (i=0; (i<prenom.length())&&(i<longMaxPrenom); i++){
					cPrenom[i]=prenom.charAt(i);
				}
				//ecriture fichier avec les methodes writeChar ou writeInt de la classe DataOutputStream
				for (i=0; i<longMaxNom; i++){
					sortie.writeChar(cNom[i]);
				}
				for (i=0; i<longMaxPrenom; i++){
					sortie.writeChar(cPrenom[i]);
				}
				sortie.writeInt(annee);
				//************************************
				//******** solution 2 ****************
				sortie.writeUTF(nom);
				sortie.writeUTF(prenom);
				sortie.writeInt(annee);
				//************************************
			}
			sortie.close();
			System.out.println("*** fin de creation fichier ***");
		
		}catch (IOException e){
			System.exit(0);
		}catch (NumberFormatException e){
			System.err.println("*** erreur de donnees ***");
			System.exit(0);
		}

	}

}

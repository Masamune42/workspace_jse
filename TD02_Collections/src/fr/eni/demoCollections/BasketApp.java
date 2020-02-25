package fr.eni.demoCollections;

import java.util.ArrayList;
import java.util.List;

import fr.eni.metier.Personne;

public class BasketApp {

	public static void main(String[] args) {
		// nous voulons organiser des personnes en équipe
		// 1ere solution : utiliser un tableau
		System.out.println("************* utiliser un tableau ************");
		// declarer le tableau
		Personne[] equipe1 = new Personne[10];
		
		//renseigner les valeurs
		equipe1[0] = new Personne("Parker");
		equipe1[1] = new Personne("Pietrus");
		
		//consulter les valeurs
		for (int i=0; i<equipe1.length;i++){
			if (equipe1[i] != null){
				System.out.println(equipe1[i].toString());
			}
		}
		//supprimer un element
		equipe1[0]= null;
		
		// contrainte 1ere solution : le tableau a une taille limité. il faut gerer
		// les cases vides
		
		// 2eme solution : utiliser une collection
		// c'est un ensemble d'éléments n'ayant pas de taille limitée
		// l'espace memoire est ajusté dynamiquement en fonction du nombre d'élements
		// nous allons utiliser une List qui permet de regrouper et d'indexer les élements
		System.out.println("************* utiliser une List ************");

		// declarer une collection
		List<Personne> equipe2 = new ArrayList<Personne>();
		//List<Personne> equipe2 = new LinkedList<Personne>();
		
		// renseigner les valeurs
		equipe2.add(new Personne("Parker"));
		equipe2.add(new Personne("Pietrus"));
		
		//Afficher le 2eme joueur
		System.out.println("le 2eme joueur de la liste est : "+equipe2.get(1));
		
		//boucle for classique
		System.out.println("affichage via une boucle classique");
		for (int i=0;i<equipe2.size();i++){
			System.out.println(equipe2.get(i).toString());
		}
		
		//boucle foreach
		System.out.println("affichage via une boucle foreach");
		for (Personne p : equipe2){
			System.out.println(p.toString());
		}
		
		//modifier un élément
		System.out.println("modification d'un element de la liste");
		System.out.println("avant : "+equipe2.get(1));
		equipe2.get(1).setNom("Diaw");
		System.out.println("après : "+equipe2.get(1));
		
		// supprimer un element
		System.out.println("suppression du 1er element de la liste");
		equipe2.remove(0); //attention la suppression d'un element en debut de liste peut 
		// etre tres lent car il y a remplacement de tous les elements suivants
		// si beaucoup de suppressions, il vaut mieux utiliser une LinkedList.
		// Il suffit de changer la declaration(remplacer ArrayList par LinkedList)
		System.out.println("affichage de la liste ");
		for (Personne p : equipe2){
			System.out.println(p);
		}
		
	}

}

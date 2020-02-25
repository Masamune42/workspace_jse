package fr.eni.arborescence;

public class Fichier extends Element{

	public Fichier(String nom){
		super(nom);
	}
	
//	public Fichier(Repertoire parent, String nom) {
//		super(parent, nom);
//		// TODO Auto-generated constructor stub
//	}

	@Override
	public void recherche(String nomRecherche) {
		if( this.nom.equals(nomRecherche)){
			System.out.println( " trouvé : " + chemin +  nomRecherche);
		}
		
	}



}

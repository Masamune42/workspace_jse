package coucheMetier;

public class Repertoire {
	private int nbMax;
	private int nbAbonne;
	private Abonne[] rep;
	
	//le constructeur recevant un argument de type entier pr�cisant le nombre maximum
	//d'abonn�s que pourra contenir le repertoire (pas � se soucier de la gestion
	//dynamique du repertoire
	public Repertoire(int nbMax) {
		this.nbMax = nbMax;
		rep = new Abonne[nbMax];
		nbAbonne = 0;
	}
	
	//permet d'ajouter un nouvel abonn� (renverra la valeur false si le repertoire est plein
	//sinon vraie
	public boolean addAbonne (Abonne a){
		if (nbAbonne >= nbMax){
			return false;
		}
		rep[nbAbonne]=a;
		nbAbonne++;
		return true;
	}
	
	//fourni le nombre d'abonn�s figurant dans le repertoire
	public int getNbAbonne(){
		return nbAbonne;
	}
	
	//fourni l'abonn� dont le rang est fourni en argument
	public Abonne getAbonne(int num){
		if(num<nbAbonne){
			return rep[num];
		}
		return null;
	}
	
	//fourni le num�ro associ� � un nom d'abonn� fourni en argument
	public String getNumero(String nom){
		for (int i=0; i<=nbAbonne; i++){
			if (nom.equals(rep[i].getNom())){
				return rep[i].getNumero();
			}
		}
		return null;
	}
	
	//fourni un tableau des r�f�rences des differents abonn�s, rang�s
	//par ordre alphab�tique
	public Abonne[] getAbonnesTries(){
		Abonne[] repTrie = new Abonne[nbAbonne];
		for (int i=0; i<nbAbonne; i++){
			repTrie[i]=rep[i];
		}
		for (int i=0; i<nbAbonne-1; i++){
			for (int j=i+1; j<nbAbonne; j++){
				if (repTrie[i].getNom().compareTo(repTrie[j].getNom())>0){
					Abonne temp = repTrie[i];
					repTrie[i]=repTrie[j];
					repTrie[j]=temp;
				}
			}
		}
		return repTrie;
	}
}

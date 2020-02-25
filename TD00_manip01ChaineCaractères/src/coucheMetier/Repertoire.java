package coucheMetier;

public class Repertoire {
	private int nbMax;
	private int nbAbonne;
	private Abonne[] rep;
	
	//le constructeur recevant un argument de type entier précisant le nombre maximum
	//d'abonnés que pourra contenir le repertoire (pas à se soucier de la gestion
	//dynamique du repertoire
	public Repertoire(int nbMax) {
		this.nbMax = nbMax;
		rep = new Abonne[nbMax];
		nbAbonne = 0;
	}
	
	//permet d'ajouter un nouvel abonné (renverra la valeur false si le repertoire est plein
	//sinon vraie
	public boolean addAbonne (Abonne a){
		if (nbAbonne >= nbMax){
			return false;
		}
		rep[nbAbonne]=a;
		nbAbonne++;
		return true;
	}
	
	//fourni le nombre d'abonnés figurant dans le repertoire
	public int getNbAbonne(){
		return nbAbonne;
	}
	
	//fourni l'abonné dont le rang est fourni en argument
	public Abonne getAbonne(int num){
		if(num<nbAbonne){
			return rep[num];
		}
		return null;
	}
	
	//fourni le numéro associé à un nom d'abonné fourni en argument
	public String getNumero(String nom){
		for (int i=0; i<=nbAbonne; i++){
			if (nom.equals(rep[i].getNom())){
				return rep[i].getNumero();
			}
		}
		return null;
	}
	
	//fourni un tableau des références des differents abonnés, rangés
	//par ordre alphabétique
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

package manipChaineCaracteres;

import coucheMetier.Abonne;
import coucheMetier.Repertoire;

public class TestRep {

	public static void main(String[] args) {
		Repertoire rep = new Repertoire(10);
		System.out.println("il y a "+rep.getNbAbonne()+ " abonnés.");
		Abonne a = new Abonne("Dupont", "0610251034");
		Abonne b = new Abonne("Durant", "0546887391");
		rep.addAbonne(a);
		rep.addAbonne(b);
		rep.addAbonne(new Abonne("Duchene", "0125305642"));
		rep.addAbonne(new Abonne("Dubois", "0778796210"));
		System.out.println("il y a "+rep.getNbAbonne()+ " abonnés.");
		System.out.println("qui sont : ");
		for (int i=0; i<rep.getNbAbonne(); i++){
			System.out.println(rep.getAbonne(i).getNom() + " - "+rep.getAbonne(i).getNumero());
		}
		System.out.println("*** par ordre alphabetique ***");
		Abonne[] abonnes = rep.getAbonnesTries();
		for (int i=0; i<abonnes.length; i++){
			System.out.println(abonnes[i].getNom() + " - "+abonnes[i].getNumero());
		}
	}

}

package annuaire;

@SuppressWarnings("rawtypes")
public class Contact  implements Comparable{

	private String nom;
	private String noTelephone;
	
	

	public Contact(String nom, String noTelephone) {
		super();
		this.nom = nom;
		this.noTelephone = noTelephone;
	}

	public String getNoTelephone() {
		return noTelephone;
	}

	public void setNoTelephone(String noTelephone) {
		this.noTelephone = noTelephone;
	}

	public String getNom() {
		return ""+nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "nom=" + nom + ", noTelephone=" + noTelephone ;
	}

	@Override
	public int compareTo(Object o) {
		int ret = 0;
		if(o instanceof Contact){
			ret = getNom().compareTo(((Contact)o).getNom());
		}
		return ret;
	}
	
}

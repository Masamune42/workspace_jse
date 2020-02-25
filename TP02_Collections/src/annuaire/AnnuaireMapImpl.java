package annuaire;

import java.util.HashMap;
import java.util.Map;

public class AnnuaireMapImpl implements IAnnuaire{

	private Map<String, Contact> contacts;
	
	public AnnuaireMapImpl() {
		contacts = new HashMap<String, Contact>();
	}
	
	@Override
	public void ajouterContact(Contact c) {
		contacts.put(c.getNom(), c);
		
	}

	@Override
	public Contact modifierContact(String nomRecherche, String noTel) {
		Contact c = contacts.get(nomRecherche);
		if(c!= null){
			c.setNoTelephone(noTel);
		}
		return c;
	}

	@Override
	public Contact rechercherContact(String nomRecherche) {
		Contact c = contacts.get(nomRecherche);
		return c;

	}

	@Override
	public void supprimerContact(String nomRecherche) {
		contacts.remove(nomRecherche);
	}

	

}

package annuaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnnuaireListImpl implements IAnnuaire{

	private List<Contact> contacts;
	
	private boolean trie;
	
	public AnnuaireListImpl() {
		contacts = new ArrayList<Contact>();
		trie = false;
	}
	
	@Override
	public void ajouterContact(Contact c) {
		contacts.add(c);
		trie = false;
		
	}

	@Override
	public Contact modifierContact(String nomRecherche, String noTel) {
		Contact c = rechercherContact( 0, contacts.size()-1, nomRecherche);
		if(c!=null){
			c.setNoTelephone(noTel);
		}
		return c;
	}

	@Override
	public Contact rechercherContact(String nomRecherche) {
		if(!trie){
			this.trier();
		}
		return rechercherContact( 0, contacts.size()-1, nomRecherche);
		
	}

	private Contact rechercherContact(int indexMin, int indexMax, String nomRecherche){
		
		if(indexMin> indexMax){
			return null;
		}
		int milieu = (indexMax + indexMin) / 2;
		if(contacts.get(milieu).getNom().equals(nomRecherche)){
			return contacts.get(milieu);
		}
		if(contacts.get(milieu).getNom().compareTo(nomRecherche)<0){
			return rechercherContact(milieu+1, indexMax, nomRecherche);
		}else{
			return rechercherContact( indexMin, milieu-1, nomRecherche);
		}
		
	}
	
	
	public void supprimerContact(String nomRecherche) {
		supprimerContact( 0, contacts.size()-1, nomRecherche);
	}
	
	private void supprimerContact(int indexMin, int indexMax, String nomRecherche) {
		if(indexMin> indexMax){
			return;
		}
		int milieu = (indexMax + indexMin) / 2;
		if(contacts.get(milieu).getNom().equals(nomRecherche)){
			contacts.remove(milieu);
		}
		if(contacts.get(milieu).getNom().compareTo(nomRecherche)<0){
			supprimerContact(milieu+1, indexMax, nomRecherche);
		}else{
			supprimerContact( indexMin, milieu-1, nomRecherche);
		}
		
	}

	
	@SuppressWarnings("unchecked")
	public void trier(){
		Collections.sort(contacts);
		trie = true;
	}
	

}

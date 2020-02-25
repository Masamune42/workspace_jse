package annuaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Annuaire {

	private List<Contact> contacts;
	
	public static void main(String[] args) {
		
		Annuaire annuaire = new Annuaire();
		annuaire.ajouterContact(new Contact("Bob", "02003040506"));
		annuaire.ajouterContact(new Contact("Charles", "06006060606"));
		annuaire.ajouterContact(new Contact("David", "0707070707"));
		annuaire.ajouterContact(new Contact("Eric", "0707070707"));
		annuaire.ajouterContact(new Contact("Fred", "02003040506"));
		annuaire.ajouterContact(new Contact("Geraldine", "06006060606"));
		annuaire.ajouterContact(new Contact("Henri", "0707070707"));
		annuaire.ajouterContact(new Contact("Yves", "0707070707"));		
		annuaire.trier();
		
		System.out.println("\nRechercher: " );
		Contact contact = annuaire.rechercherContact(0, annuaire.contacts.size()-1, "Bob");
		System.out.println("Contact: " + contact);

		contact = annuaire.rechercherContact(0, annuaire.contacts.size()-1, "Eric");
		System.out.println("Contact: " + contact);
		
		contact = annuaire.rechercherContact(0, annuaire.contacts.size()-1, "Charlie");
		System.out.println("Contact: " + contact);
		
		System.out.println("\nModifier: " );
		contact = annuaire.modifierContact(0, annuaire.contacts.size()-1, "Charles", "0102030405");
		System.out.println("Contact: " + contact);

		System.out.println("\nSupprimer: " );
		annuaire.supprimerContact(0, annuaire.contacts.size()-1, "Bob");
		
		contact = annuaire.rechercherContact(0, annuaire.contacts.size()-1, "Bob");
		System.out.println("Contact: " + contact);

	}
	
	
	public Annuaire(){
		contacts = new  ArrayList<Contact>();
	}
	
	@SuppressWarnings("unchecked")
	public void trier(){
		Collections.sort(contacts);
	}
	
	
	public void ajouterContact(Contact c){
		contacts.add(c);
		contacts.sort(new Comparator<Contact>(){

			@Override
			public int compare(Contact o1, Contact o2) {
				return o1.compareTo(o2);
			}
			
		});
	}
	
		
	private Contact modifierContact(int indexMin, int indexMax, String nomRecherche, String noTel){
		Contact c = rechercherContact( indexMin, indexMax, nomRecherche);
		if(c!=null){
			c.setNoTelephone(noTel);
		}
		return c;
		
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
	

	private void supprimerContact(int indexMin, int indexMax, String nomRecherche){
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
	
	
}

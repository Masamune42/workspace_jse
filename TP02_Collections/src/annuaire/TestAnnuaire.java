package annuaire;

public class TestAnnuaire {

	public static void main(String[] args) {
		IAnnuaire annuaire = AnnuaireFactory.getAnnuaireImpl();
		
		//AnnuaireListImpl annuaire = new AnnuaireListImpl();
		
		
		annuaire.ajouterContact(new Contact("Bob", "0202020202"));
		annuaire.ajouterContact(new Contact("Charles", "0303030303"));
		annuaire.ajouterContact(new Contact("David", "4444444444"));
		annuaire.ajouterContact(new Contact("Eric", "5555555555"));
		annuaire.ajouterContact(new Contact("Fred", "6666666666"));
		annuaire.ajouterContact(new Contact("Geraldine", "7777777777"));
		annuaire.ajouterContact(new Contact("Henri", "8888888888"));
		annuaire.ajouterContact(new Contact("Yves", "9999999999"));		
		
		System.out.println("\nRechercher Bob: " );
		Contact contact = annuaire.rechercherContact( "Bob");
		System.out.println("Contact: " + contact);

		System.out.println("\nRechercher Eric: " );
		contact = annuaire.rechercherContact( "Eric");
		System.out.println("Contact: " + contact);
		
		System.out.println("\nRechercher Charlie: " );
		contact = annuaire.rechercherContact( "Charlie");
		System.out.println("Contact: " + contact);
		
		System.out.println("\nModifier Charles: " );
		contact = annuaire.modifierContact( "Charles", "0102030405");
		System.out.println("Contact: " + contact);

		System.out.println("\nSupprimer Bob: " );
		annuaire.supprimerContact( "Bob");
		System.out.println("\nRechercher Bob: " );
		contact = annuaire.rechercherContact( "Bob");
		System.out.println("Contact: " + contact);

	}
	
}

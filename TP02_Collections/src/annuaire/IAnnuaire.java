package annuaire;

public interface IAnnuaire {
	
	void ajouterContact(Contact c);
	
	Contact modifierContact(String nomRecherche, String noTel);
	
	Contact rechercherContact( String nomRecherche);

	void supprimerContact(String nomRecherche);
	
}

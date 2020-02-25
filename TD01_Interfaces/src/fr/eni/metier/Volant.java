package fr.eni.metier;

public interface Volant {

	// certains mammifères peuvent voler, mais pas tous.
	// nous voudrions factoriser ce comportement, mais nous ne pouvons pas faire
	// heriter une classe de plusieurs classes. Il faut créer une interface
	
	// toutes les methodes d'une interface sont abstraites
	// c'est à dire sans code. Les classes implémentant cette
	// interface sont dans l'obligation d'implémenter toutes les
	// methodes de l'interface

	// on définit des constantes (par defaut elles sont public static final
	public static final int NB_AILES = 2;
	// int NB_AILES = 2;
	
	//  on definit les signatures de methodes (par defaut public abstract)
	public abstract void voler(); 
	
}

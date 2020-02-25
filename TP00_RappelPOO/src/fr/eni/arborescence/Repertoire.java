package fr.eni.arborescence;

import java.util.ArrayList;
import java.util.List;

public class Repertoire extends Element {


	private List<Element> elements;

	public Repertoire(String nom){
		super(nom);
		elements = new ArrayList<Element>();
	}
	

	public List<Element> getElements() {
		return elements;
	}

	public void addElements(Element element) {
		element.setChemin(this.chemin +  this.nom + "\\");
		this.elements.add(element);
	}

	@Override
	public void recherche(String nomRecherche) {
		if(this.nom.equals(nomRecherche)){
				System.out.println( " trouvé : " + chemin +  nomRecherche);
		}
		for(Element e: elements){
			e.recherche(nomRecherche);
		}
	}
	
}

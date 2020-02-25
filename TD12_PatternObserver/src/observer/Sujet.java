package observer;

import java.util.ArrayList;
import java.util.List;

public class Sujet {
	
	private List<Observateur> listeObservateurs;
	
	public Sujet() {
		listeObservateurs = new ArrayList<Observateur>();
	}
	
	public void addObserver(Observateur observer){
		listeObservateurs.add(observer);
	}
	
	public void evenement(){
		System.out.println("Emission evenement...");
		for(Observateur o: listeObservateurs){
			o.notifier();
		}
	}

}

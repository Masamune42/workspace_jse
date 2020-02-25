package fr.eni.metier;

public class ChauveSouris extends Mammifère implements Volant{

	public void communiquer(){
		System.out.println("ulta-sons");
	}

	@Override
	public void voler() {
		System.out.println("chauve souris volante");
		
	}
	
	
}

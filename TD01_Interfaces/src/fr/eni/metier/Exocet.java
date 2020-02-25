package fr.eni.metier;

public class Exocet implements Volant, Nageant{

	@Override
	public void nager() {
		System.out.println("poisson volant qui nage");
	}

	@Override
	public void voler() {
		System.out.println("poisson volant qui vole");
	}

}

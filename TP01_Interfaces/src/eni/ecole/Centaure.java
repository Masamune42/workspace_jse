package eni.ecole;

public class Centaure extends Cheval implements Siffleur  {

	@Override
	public void siffler() {
		System.out.println("siffler");
	}

	@Override
	public void dormir() {
		System.out.println("dormir");	}

	@Override
	public void communiquer() {
		System.out.println("communiquer");	}

	@Override
	public void galoper() {
		super.galoper();
	}


	
	
}

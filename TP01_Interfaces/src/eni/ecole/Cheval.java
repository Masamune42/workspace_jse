package eni.ecole;

public class Cheval implements Animal{

	@Override
	public void dormir() {
		System.out.println("dormir comme un cheval");
	}

	@Override
	public void communiquer() {
		System.out.println("communiquer comme un cheval");
	}

	public void galoper() {
		System.out.println("galoper comme un cheval");
	}
}

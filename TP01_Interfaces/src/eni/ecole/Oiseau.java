package eni.ecole;

public class Oiseau implements Siffleur{

	@Override
	public void dormir() {
		System.out.println("dormir comme un oiseau");	}

	@Override
	public void communiquer() {
		siffler();
	}

	@Override
	public void siffler() {
		System.out.println("siffler comme un oiseau");	}

}

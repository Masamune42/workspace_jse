package eni.ecole;

public class Humain implements Siffleur{

	@Override
	public void dormir() {
		System.out.println("dormir comme un humain");
	}

	@Override
	public void communiquer() {
		System.out.println("communiquer comme un humain");
	}

	@Override
	public void siffler() {
		System.out.println("siffler comme un humain");
	}
	
	
}

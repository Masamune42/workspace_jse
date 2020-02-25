package fr.eni.serialisation;

import java.io.Serializable;

public class Personne implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String nom;
	private int age;
	//private transient Adresse adresse;
	private  Adresse adresse;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", age=" + age + ", adresse=" + adresse
				+ "]";
	}

}

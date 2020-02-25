package fr.eni.serialisation;

import java.io.Serializable;

//public class Adresse {
	public class Adresse implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	
	private String adresse;

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Adresse(String adresse) {
		super();
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "Adresse [adresse=" + adresse + "]";
	}
}

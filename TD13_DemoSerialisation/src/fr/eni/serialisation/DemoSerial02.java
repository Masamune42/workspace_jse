package fr.eni.serialisation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DemoSerial02 {
	/*
	 * Description : 
	 * 	-1 Montrer la désérialisation d'un objet
	 *  -2 Illustrer le fonctionnement du serialVersionUID : 
	 *  	2.1 - Sérialiser avec serialVersionUID de Personne à 1
	 *      2.2 - Changer le serialVersionUID de Personne à 2
	 *      2.3 - Désérialiser : provoque java.io.InvalidClassException: fr.eni.serialisation.Personne;...
	 */
	public static void main(String[] args) {
		Personne personne=null;


		try (	FileInputStream	fis = new FileInputStream("C:\\temp\\flux\\personne.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				){
			personne = (Personne) ois.readObject();
			System.out.println(personne);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


	}
}

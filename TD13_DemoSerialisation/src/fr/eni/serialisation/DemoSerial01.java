package fr.eni.serialisation;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class DemoSerial01  {

	/**
	 * Description : 
	 * 	Sérialiser un objet de type Personne
	 * @param args
	 */
	public static void main(String[] args) {
		Personne personne = new Personne();
		personne.setNom("Bob");
		personne.setAge(82);
		System.out.println(personne);

		try{

			FileOutputStream out = new FileOutputStream("C:\\temp\\flux\\personne.ser");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(personne);
			oos.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}

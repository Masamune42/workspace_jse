package fr.eni.serialisation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DemoSerial03 {
	/*
	 * Description: Serialisation d'un attribut de type objet
	 * 1- Ajouter la classe Adresse et l'attribut adresse dans Personne
	 * 2- S�rialiser et d�siarialiser pour produire  l'exception : java.io.NotSerializableException: fr.eni.serialisation.Adresse
	 * 3 - Eviter l'exception par le mot cl� transient
	 * 3 - Eviter l'exceptin en rendant la classe Adresse Serializable
	 */
	public static void main(String[] args) {
		Personne personne = new Personne();
		personne.setNom("Bob");
		personne.setAge(82);
		personne.setAdresse(new Adresse("8 rue des Go�lands 44400 REZE"));
		System.out.println(personne);

		//S�rialisation
		try{

			FileOutputStream out = new FileOutputStream("C:\\temp\\flux\\personne.ser");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(personne);
			oos.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		//D�s�rialisation
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

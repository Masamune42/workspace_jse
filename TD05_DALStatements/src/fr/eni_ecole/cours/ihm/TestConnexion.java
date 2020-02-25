package fr.eni_ecole.cours.ihm;

import java.sql.Connection;

import fr.eni_ecole.cours.dal.DALException;
import fr.eni_ecole.cours.util.DBConnection;

public class TestConnexion {

	/**
	 * Methode temporaire permettant de tester une connexion à la BDD
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Connection cnx =DBConnection.seConnecter();
			System.out.println("connexion OK");
			DBConnection.seDeconnecter(cnx);
		} catch (DALException e) {
			System.out.println("******ERREUR *******");
			System.out.println("message : "+e.getMessage());
			System.out.println("cause : "+e.getCause());
			System.out.println("----- trace -----");
			System.out.println("classe : "+e.getStackTrace()[0].getClassName());
			System.out.println("methode : "+e.getStackTrace()[0].getMethodName());
			System.out.println("ligne : "+e.getStackTrace()[0].getLineNumber());
		}

	}

}

package fr.eni.cours.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.cours.dal.DALException;

/**
 * Classe gerant la connexion et la deconnexion à la BDD
 * @author Thierry
 *
 */
public class DBAcces {

	private static final String URL="jdbc:sqlserver://127.0.0.1:1433;instanceName=SQLEXPRESS;databasename=Ecole";
	private static final String USER="sa";
	private static final String PWD="Pa$$w0rd";
	/**
	 * Methode permettant d'obtenir une connexion
	 * @return un objet de type Connection
	 * @throws DALException 
	 */
	public static Connection seConnecter() throws DALException {
		Connection cnx=null;
		
		//Etape 2 - Obtenir une connexion
		try {
			cnx = DriverManager.getConnection(URL, USER, PWD);
		} catch (SQLException e) {
			throw new DALException("Probleme sur la chaine de connexion", e);
		}
		
		return cnx;
	}
	
	/**
	 * Methode permettant de fermer une connexion ouverte
	 * @param cnx : objet de type Connection
	 * @throws DALException
	 */
	public static void seDeconnecter(Connection cnx) throws DALException {
		
		try {
			if (cnx!=null) {
				cnx.close();
			}
		} catch (SQLException e) {
			throw new DALException("connexion déjà fermée", e);
		}
		
	}

	
	/**
	 * Methode permettant de fermer le callableStatement et la connexion ouverte
	 * <br><strong> surcharge de methode </strong>
	 * @param cnx : objet de type Connection
	 * @param callstmt : objet de type CallableStatement
	 * @throws DALException
	 */

	public static void seDeconnecter(CallableStatement callstmt,Connection cnx) throws DALException {
		
		try {
			if (callstmt!=null) {
				callstmt.close();
			}
		} catch (SQLException e) {
			throw new DALException("probleme fermeture CallableStatement", e);
		}

		try {
			if (cnx!=null) {
				cnx.close();
			}
		} catch (SQLException e) {
			throw new DALException("connexion déjà fermée", e);
		}
		
	}

}





package fr.eni.cours.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.eni.cours.dal.DALException;

public class DBConnexion {
	private static String url;
	private static String user;
	private static String pwd;

	/**
	 * Methode permettant d'obtenir une connexion
	 * @return un objet de type Connection
	 * @throws DALException 
	 */
	public static Connection seConnecter() throws DALException {
		
		Connection cnx = null;
		
		try {
			url = Settings.getProperty("url");
			user= Settings.getProperty("user");
			pwd= Settings.getProperty("pwd");
			cnx= DriverManager.getConnection(url, user, pwd);
			cnx.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DALException("Impossible d'obtenir une connection", e);
		}
		return cnx;
		
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

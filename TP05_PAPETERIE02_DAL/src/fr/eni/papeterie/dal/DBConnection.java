package fr.eni.papeterie.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe gerant la connexion et la deconnexion à la BDD
 * @author Thierry
 *
 */
public class DBConnection {
	private static final String URL="jdbc:sqlserver://127.0.0.1:1433;instanceName=SQLexpress;databasename=PAPETERIE_DB";
	private static final String USER="sa";
	private static final String PASSWORD="Pa$$w0rd";
	
	/**
	 * Methode permettant d'obtenir une connexion
	 * @return un objet de type Connection
	 * @throws DALException
	 */
	public static Connection seConnecter()throws DALException {
		Connection cnx = null;
		try {
			cnx = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			throw new DALException("Problème sur la chaine de connexion",e);
		}
		return cnx;
	}
	
	/**
	 * Methode permettant de fermer la connexion ouverte
	 * @param cnx : objet de type Connection
	 * @throws DALException
	 */
	public static void seDeconnecter(Connection cnx) throws DALException{
		try {
			if(cnx!=null){
				cnx.close();
			}
		} catch (SQLException e) {
			throw new DALException("Probleme sur la fermeture de la connexion : ",e);
		}

	}

	/**
	 * Methode permettant de fermer le statement et la connexion 
	 * @param stmt : objet de type Statement
	 * @param cnx : objet de type Connection
	 * @throws DALException
	 */
	public static void seDeconnecter(Statement stmt, Connection cnx) throws DALException{
		try {
			if(stmt!=null){
				stmt.close();
			}
		} catch (SQLException e) {
			throw new DALException("Probleme sur la fermeture du Statement : ",e);
		}

		try {
			if(cnx!=null){
				cnx.close();
			}
		} catch (SQLException e) {
			throw new DALException("Probleme sur la fermeture de la connexion : ",e);
		}

	}

	/**
	 * Methode permettant de fermer le preparedStatement et la connexion 
	 * @param pstmt : objet de type PreparedStatement
	 * @param cnx : objet de type Connection
	 * @throws DALException
	 */
	public static void seDeconnecter(PreparedStatement pstmt, Connection cnx) throws DALException{
		try {
			if(pstmt!=null){
				pstmt.close();
			}
		} catch (SQLException e) {
			throw new DALException("Probleme sur la fermeture du PreparedStatement : ",e);
		}

		try {
			if(cnx!=null){
				cnx.close();
			}
		} catch (SQLException e) {
			throw new DALException("Probleme sur la fermeture de la connexion : ",e);
		}

	}
}

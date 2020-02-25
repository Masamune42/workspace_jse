package fr.eni_ecole.cours.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import fr.eni_ecole.cours.dal.DALException;

public class DBConnection {
	private static String url;
	private static String user;
	private static String password;
	
	public static Connection seConnecter()throws SQLException {
		Connection cnx = null;
		try{
			DriverManager.registerDriver(new SQLServerDriver());
			url = Settings.getProperty("url");
			user = Settings.getProperty("user");
			password = Settings.getProperty("password");
			cnx = DriverManager.getConnection(url, user, password);
		}
		catch(SQLException e){
			throw new SQLException("Impossible d'obtenir une connexion:"+e.getMessage());
		}
		return cnx;
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

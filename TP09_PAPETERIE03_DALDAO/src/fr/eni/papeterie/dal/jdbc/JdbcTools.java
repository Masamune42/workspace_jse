package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.Settings;

public class JdbcTools {

	
	private static  String urldb;
	private static String userdb;
	private static String passworddb;
	
	static {
		
		try {
			Class.forName(Settings.getProperty("driverdb"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		urldb = Settings.getProperty("urldb");
		userdb = Settings.getProperty("userdb");
		passworddb = Settings.getProperty("passworddb");
		System.out.println("urldb="+urldb+";userdb="+userdb+";passworddb="+passworddb);
	}
	
	
	
	public static Connection getConnection() throws SQLException{
		//Connection connection = DriverManager.getConnection(urldb);
		Connection connection = DriverManager.getConnection(urldb, userdb, passworddb);
		
		return connection;
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



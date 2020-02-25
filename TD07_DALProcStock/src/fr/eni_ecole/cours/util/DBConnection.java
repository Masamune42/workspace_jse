package fr.eni_ecole.cours.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.eni_ecole.cours.dal.DALException;

//import com.microsoft.sqlserver.jdbc.SQLServerDriver;

/**
 * Classe gerant la connexion et la deconnexion à la BDD
 * @author Thierry
 *
 */
public class DBConnection {
	private static final String URL="jdbc:sqlserver://127.0.0.1:1433;instanceName=SQLexpress;databasename=Ecole";
	private static final String USER="sa";
	private static final String PASSWORD="Pa$$w0rd";
	
	/**
	 * Methode permettant d'obtenir une connexion
	 * @return un objet de type Connection
	 * @throws DALException
	 */
	public static Connection seConnecter()throws DALException {
		Connection cnx = null;
		try{
//			DriverManager.registerDriver(new SQLServerDriver());
			cnx = DriverManager.getConnection(URL, USER, PASSWORD);
		}
		catch(SQLException e){
			throw new DALException("Probleme sur la chaine de connexion",e);
		}
		return cnx;
	}
	
	/**
	 * Methode permettant de fermer le callableStatement et la connexion 
	 * @param callstmt : objet de type CallableStatement
	 * @param cnx : objet de type Connection
	 * @throws DALException
	 */
	public static void seDeconnecter(CallableStatement callstmt, Connection cnx) throws DALException{
		try {
			if(callstmt!=null){
				callstmt.close();
			}
		} catch (SQLException e) {
			throw new DALException("Probleme sur la fermeture du CallableStatement : ",e);
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

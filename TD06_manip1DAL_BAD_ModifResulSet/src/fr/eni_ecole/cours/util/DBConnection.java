package fr.eni_ecole.cours.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

//import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class DBConnection {
	private static String url;
	private static String user;
	private static String password;
	private static Connection cnx = null;

	
	public static Connection seConnecter() {
		//DriverManager.registerDriver(new SQLServerDriver());
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Erreur pendant le chargement du pilote : "+e.getMessage());
		}
		//en version superieur ou egal à 4, le bloc precedent est inutile
		try{
			url = Settings.getProperty("url");
			user = Settings.getProperty("user");
			password = Settings.getProperty("password");
			cnx = DriverManager.getConnection(url, user, password);
		}
		catch(SQLException e){
			System.out.println("Impossible d'obtenir une connexion: "+e.getMessage());
		}
		return cnx;
	}
	
	public static void closeConnexions(Statement stmt, Connection cnx) {
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println("Impossible de fermer le statement: "+e.getMessage());
			}
			stmt=null;
		}	
		if(cnx!=null){
			try {
				cnx.close();
			} catch (SQLException e) {
				System.out.println("Impossible de fermer la connexion: "+e.getMessage());
			}
			cnx=null;
		}	
	}

	public static void closeConnexions(PreparedStatement pstmt, Connection cnx) {
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("Impossible de fermer le preparedStatement: "+e.getMessage());
			}
			pstmt=null;
		}	
		if(cnx!=null){
			try {
				cnx.close();
			} catch (SQLException e) {
				System.out.println("Impossible de fermer la connexion: "+e.getMessage());
			}
			cnx=null;
		}	
	}

}

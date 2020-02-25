package fr.eni_ecole.cours.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni_ecole.cours.bo.Eleve;
import fr.eni_ecole.cours.util.DBConnection;

public class EleveDAOJdbcImpl {

	//declaration des constantes
	private final static String SELECT_ALL = "SELECT nom, prenom, adresse FROM eleves;";
	private final static String RECHERCHER= "SELECT nom, prenom, adresse FROM eleves WHERE nom = ? and prenom = ?;";
	private final static String INSERER = "INSERT INTO eleves(nom, prenom, adresse) VALUES (?,?,?);";
	private final static String SUPPRIMER = "DELETE FROM eleves WHERE nom = ? and prenom = ?;";
	private final static String MODIFIER = "UPDATE eleves SET adresse = ? WHERE nom = ? AND prenom = ?;";

	/**
	 * Methode qui permet de lister tous les élèves present en BDD
	 * @return La liste peut être vide mais jamais <code>null</code>
	 * @throws DALException : propage l'exception en DALException
	 * @finally ferme les connexions ouvertes
	 */
	public static ArrayList<Eleve> lister() throws DALException {
		//declaration des variables et objets et initialisation
		Connection cnx=null;
		Statement stmt=null;//on l'utilise lorsqu'il n'y a pas de parametre dans la requete
		ResultSet rs=null;
		ArrayList<Eleve> listeEleves = new ArrayList<Eleve>(); //on instancie la liste, donc elle ne sera pas null
		Eleve eleve= null;

		cnx = DBConnection.seConnecter();
		try{
			stmt=cnx.createStatement();		//creation du statement	
			rs=stmt.executeQuery(SELECT_ALL); //execution de la requete. Ici la methode executeQuery nous ramène un resultat
			//qui est integré à l'objet de type ResultSet
			while (rs.next()){
				eleve = new Eleve();
				eleve.setNom(rs.getString("nom"));
				eleve.setPrenom(rs.getString("prenom"));
				rs.getString("adresse");
				if (rs.wasNull()){
					eleve.setAdresse("<<inconnue>>");
				}else {
					eleve.setAdresse(rs.getString("adresse"));
				}
				listeEleves.add(eleve);				
			}
		}catch (SQLException e){
			throw new DALException ("Probleme methode lister()",e);
		}finally{
			DBConnection.seDeconnecter(stmt, cnx);
		}
		return listeEleves;
	}
	
	/**
	 * Methode rechercher un élève
	 * @param nom
	 * @param prenom
	 * @return un eleve
	 * @throws DALException : propage l'exception en DALException
	 * @finally ferme les connexions ouvertes
	 */
	public static Eleve rechercher(String nom, String prenom) throws DALException{
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Eleve eleve = new Eleve();

		cnx = DBConnection.seConnecter();
		try{
			pstmt=cnx.prepareStatement(RECHERCHER);
			pstmt.setString(1, nom);
			pstmt.setString(2, prenom);
			rs=pstmt.executeQuery();
			while (rs.next()){
				eleve.setNom(rs.getString("nom"));				
				eleve.setPrenom(rs.getString("prenom"));
				eleve.setAdresse(rs.getString("adresse"));
			}
		}catch (SQLException e){
			throw new DALException ("Probleme methode rechercher()",e);
		}finally{
			DBConnection.seDeconnecter(pstmt, cnx);
		}
		return eleve;
	}
	
	/**
	 * Methode ajouter un élève
	 * @param un eleve
	 * @throws DALException propage l'exception en DALException
	 */
	public static int ajouter(Eleve eleve) throws DALException{
		Connection cnx=null;
		PreparedStatement pstmt=null;
		int nbLignes = 0;

		cnx = DBConnection.seConnecter();
		try{
			pstmt=cnx.prepareStatement(INSERER);
			pstmt.setString(1, eleve.getNom());
			pstmt.setString(2, eleve.getPrenom());
			pstmt.setString(3, eleve.getAdresse());
			nbLignes=pstmt.executeUpdate();
		} catch (SQLException e){
			throw new DALException ("Probleme methode ajouter()",e);
		}finally{
			DBConnection.seDeconnecter(pstmt, cnx);
		}
		return nbLignes;
	}
	
	/**
	 * Methode supprimer un élève. On utilise une procédure stockée.
	 * @param nom
	 * @param prenom
	 * @throws DALException : propage l'exception en DALException
	 */
	public static int supprimer(String nom, String prenom) throws DALException{
		Connection cnx=null;
		PreparedStatement pstmt = null;
		int nbLigne=0;

		cnx = DBConnection.seConnecter();
		try{
			pstmt = cnx.prepareStatement(SUPPRIMER);
			pstmt.setString(1, nom);
			pstmt.setString(2, prenom);
			nbLigne=pstmt.executeUpdate();
		}catch(SQLException e){
			throw new DALException ("Probleme methode supprimer()",e);
		}finally{
			DBConnection.seDeconnecter(pstmt, cnx);
		}
		return nbLigne;
	}
	
	/**
	 * Methode modifier un élève
	 * @param adresse
	 * @param nom
	 * @param prenom
	 * @throws DALException : propage l'exception en DALException
	 */
	public static int modifier(String adresse,String nom, String prenom) throws DALException{
		Connection cnx=null;
		PreparedStatement pstmt=null;
		int nbLigne=0;

		cnx = DBConnection.seConnecter();
		try{
			pstmt=cnx.prepareStatement(MODIFIER);
			pstmt.setString(1,adresse);
			pstmt.setString(2,nom);
			pstmt.setString(3,prenom);
			nbLigne=pstmt.executeUpdate();
		}catch (SQLException e){
			throw new DALException ("Probleme methode modifier()",e);
		}finally{
			DBConnection.seDeconnecter(pstmt, cnx);
		}
		return nbLigne;
	}
	
}

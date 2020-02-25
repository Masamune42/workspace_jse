package fr.eni_ecole.cours.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni_ecole.cours.bo.Eleve;
import fr.eni_ecole.cours.util.DBConnection;
import fr.eni_ecole.cours.dal.DALException;

public class EleveDAOJdbcImpl {
	//declaration des constantes
//	private final static String SELECT_ALL = "EXEC dbo.listerEleve";
//	private final static String RECHERCHER= "EXEC dbo.rechercherEleve @nom=?, @prenom=?";
//	private final static String INSERER = "EXEC dbo.insererEleve @nom=?, @prenom=?, @adresse=?";
//	private final static String MODIFIER = "EXEC dbo.modifierEleve @nom=?, @prenom=?, @adresse=?";
//	private final static String SUPPRIMER = "EXEC dbo.supprimerEleve @nom=?, @prenom=?";

	
	/**
	 * Methode qui permet de lister les élèves
	 * @return La liste peut être vide mais jamais <code>null</code>
	 * @throws DALException : propage l'exception en DALException
	 * @finally ferme les connexions ouvertes
	 */
	public static ArrayList<Eleve> lister() throws DALException{
		Connection cnx=null;
		CallableStatement callstmt=null;
		ResultSet rs=null;
		ArrayList<Eleve> listeEleves = new ArrayList<Eleve>();
		Eleve eleve;

		cnx = DBConnection.seConnecter();

		try{
			callstmt = cnx.prepareCall("{ call listerEleve }");
			rs=callstmt.executeQuery();
			while (rs.next()){
				eleve = new Eleve();
				eleve.setNom(rs.getString("nom"));
				eleve.setPrenom(rs.getString("prenom"));
				eleve.setAdresse(rs.getString("adresse"));
				listeEleves.add(eleve);				
			}
		}catch (SQLException e){
			throw new DALException("Probleme methode lister()",e);
		}finally{
			DBConnection.seDeconnecter(callstmt, cnx);		
		}
		return listeEleves;
	}

	/**
	 * Methode rechercher un élève
	 * @param nom
	 * @param prenom
	 * @return un eleve
	 * @throws DALException : propage l'exception en DAL Exception
	 * @finally ferme les connexions ouvertes
	 */
	public static Eleve rechercher(String nom, String prenom) throws DALException{
		Connection cnx=null;
		CallableStatement callstmt = null;
		ResultSet rs=null;
		Eleve eleve = new Eleve();
		
		cnx = DBConnection.seConnecter();

		try{
			callstmt=cnx.prepareCall("{ call rechercherEleve (?,?) }");
			callstmt.setString(1, nom);
			callstmt.setString(2, prenom);
			rs=callstmt.executeQuery();
			while (rs.next()){
				eleve.setNom(rs.getString("nom"));				
				eleve.setPrenom(rs.getString("prenom"));
				eleve.setAdresse(rs.getString("adresse"));
			}
		}catch (SQLException e){
			throw new DALException("Probleme methode rechercher()",e);
		}finally{
			DBConnection.seDeconnecter(callstmt, cnx);
		}
		return eleve;
	}
	
	/**
	 * Methode ajouter un élève. On utilise une procedure stockée
	 * @param eleve
	 * @throws DALException propage l'exception en DALException
	 */
	public static int ajouter(Eleve eleve) throws DALException{
		Connection cnx=null;
		CallableStatement callstmt = null;
		int nbre = 0;
		
		cnx = DBConnection.seConnecter();

		try{
			callstmt = cnx.prepareCall("{ call insererEleve (?,?,?)}");
			callstmt.setString(1, eleve.getNom());
			callstmt.setString(2, eleve.getPrenom());
			callstmt.setString(3, eleve.getAdresse());
			nbre=callstmt.executeUpdate();
		}catch (SQLException e){
			throw new DALException("Probleme methode ajouter()",e);
		}finally{
			DBConnection.seDeconnecter(callstmt, cnx);
		}
		return nbre;
	}
	
	/**
	 * Methode supprimer un élève. On utilise une procédure stockée.
	 * @param nom
	 * @param prenom
	 * @throws DALException : propage l'exception en DALException
	 */
	public static int supprimer(String nom, String prenom) throws DALException{
		Connection cnx=null;
		CallableStatement callstmt = null;
		int nbre = 0;
		
		cnx = DBConnection.seConnecter();

		try{
			callstmt = cnx.prepareCall("{ call supprimerEleve (?,?)}");
			callstmt.setString(1, nom);
			callstmt.setString(2, prenom);
			nbre=callstmt.executeUpdate();
		}catch (SQLException e){
			throw new DALException("Probleme methode supprimer()",e);
		}finally{
			DBConnection.seDeconnecter(callstmt, cnx);
		}
		return nbre;
	}
	
	/**
	 * Methode modifier un élève. On utilise un procédure stockée
	 * @param adresse
	 * @param nom
	 * @param prenom
	 * @return une valeur boolean true ou false
	 * @throws DALException : propage l'exception en DALException
	 */
	public static int modifier(String adresse,String nom, String prenom) throws DALException{
		Connection cnx=null;
		CallableStatement callstmt = null;
		int nbre=0;
		
		cnx = DBConnection.seConnecter();

		try{
			callstmt = cnx.prepareCall("{ call modifierEleve (?,?,?)}");
			callstmt.setString(1,nom);
			callstmt.setString(2,prenom);
			callstmt.setString(3,adresse);
			nbre=callstmt.executeUpdate();
		}catch (SQLException e){
			throw new DALException("Probleme methode modifier()",e);
		}finally{
			DBConnection.seDeconnecter(callstmt, cnx);
		}
		return nbre;
	}
	
	/**
	 * Methode qui permet de compter le nombre d'élèves
	 * @return Le nombre d'eleves
	 * @throws DALException : propage l'exception en DALException
	 * @finally ferme les connexions ouvertes
	 */
	public static int compter() throws DALException{
		Connection cnx=null;
		CallableStatement callstmt=null;
		int nbEleves = 0;
		
		cnx = DBConnection.seConnecter();

		try{
			callstmt = cnx.prepareCall("{ ?=call compterEleve }");
			callstmt.registerOutParameter(1,java.sql.Types.INTEGER);
			callstmt.execute();
			nbEleves = callstmt.getInt(1);
		}catch (SQLException e){
			throw new DALException("Probleme methode compter()",e);
		}finally{
			DBConnection.seDeconnecter(callstmt, cnx);
		}
		return nbEleves;
	}

	
}

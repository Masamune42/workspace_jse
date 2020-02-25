package fr.eni_ecole.cours.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni_ecole.cours.bo.Eleve;
import fr.eni_ecole.cours.dal.DALException;
import fr.eni_ecole.cours.dal.EleveDAO;
import fr.eni_ecole.cours.util.DBConnection;

public class EleveDAOJdbcImpl implements EleveDAO{

	//declaration des constantes
	private final static String SELECT_ALL = "select * from eleves;";
	private final static String RECHERCHER= "select * from eleves where nom = ? and prenom = ?;";
	private final static String INSERER = "insert into eleves(nom, prenom, adresse) values (?,?,?);";
	private final static String SUPPRIMER = "delete from eleves where nom = ? and prenom = ?;";
	private final static String MODIFIER = "update eleves set adresse = ? where nom = ? and prenom = ?;";

	/**
	 * Methode qui permet de <font color="red">lister les élèves</font>
	 * @return La liste peut être vide mais jamais <code>null</code>
	 * @throws DALException : propage l'exception en <strong>DALException</strong>
	 * @finally <em>ferme les connexions ouvertes</em>
	 */
	@Override
	public ArrayList<Eleve> lister() throws DALException {
		Connection cnx=null;
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Eleve> listeEleves = new ArrayList<Eleve>();
		try{
			//ouvrir une connexion
			cnx = DBConnection.seConnecter();
			//creation d'un statement (requete select sans argument)
			stmt=cnx.createStatement();	
			//execute la requete coté base de données (executeQuery pour requete select)
			rs=stmt.executeQuery(SELECT_ALL);
			Eleve eleve;
			//retourne plus d'un enregistrement donc while
			while (rs.next()){
				eleve = new Eleve();
				eleve.setNom(rs.getString("nom"));
				eleve.setPrenom(rs.getString("prenom"));
				eleve.setAdresse(rs.getString("adresse"));
				//ajout de l'enregistrement retourné par le resultset dans la liste
				listeEleves.add(eleve);				
			}
		}catch (SQLException e){
			//en cas d'une erreur de type SQLException, on leve une exception DALException
			throw new DALException ("probleme methode lister()",e);
		}finally{
			// ne pas oublier de fermer le statement et la connexion
			DBConnection.seDeconnecter(stmt, cnx);
		}
		return listeEleves;
	}
	
	/**
	 * Methode permettant de <font color="red">rechercher un élève</font>
	 * @param nom
	 * @param prenom
	 * @return un eleve
	 * @throws DALException : propage l'exception en <strong>DALException</strong>
	 * @finally <em>ferme les connexions ouvertes</em>
	 */
	@Override
	public Eleve rechercher(String nom, String prenom) throws DALException{
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Eleve eleve = new Eleve();
		try{
			cnx = DBConnection.seConnecter();
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
			throw new DALException ("probleme methode rechercher()", e);
		}finally{
			DBConnection.seDeconnecter(pstmt, cnx);
		}
		return eleve;
	}
	
	/**
	 * Methode qui permet d'<font color="red">ajouter un élève</font>
	 * @param un eleve
	 * @return le nombre de lignes ajoutées
	 * @throws DALException propage l'exception en <strong>DALException</strong>
	 * @finally <em>ferme les connexions ouvertes</em>
	 */
	@Override
	public int ajouter(Eleve eleve) throws DALException{
		Connection cnx=null;
		PreparedStatement pstmt=null;
		int nbLignes=0;

		try{
			cnx = DBConnection.seConnecter();
			pstmt=cnx.prepareStatement(INSERER);
			pstmt.setString(1, eleve.getNom());
			pstmt.setString(2, eleve.getPrenom());
			pstmt.setString(3, eleve.getAdresse());
			nbLignes=pstmt.executeUpdate();
		} catch (SQLException e){
			throw new DALException ("probleme methode ajouter()",e);
		}finally{
			DBConnection.seDeconnecter(pstmt, cnx);
		}
		return nbLignes;
	}
	
	/**
	 * Methode qui permet de <font color="red">supprimer un élève.</font>
	 * @param nom
	 * @param prenom
	 * @return le nombre de lignes supprimées
	 * @throws DALException : propage l'exception en <strong>DALException</strong>
	 * @finally <em>ferme les connexions ouvertes</em>
	 */
	@Override
	public int supprimer(String nom, String prenom) throws DALException{
		Connection cnx=null;
		PreparedStatement pstmt = null;
		int nbLignes=0;
		try{
			cnx = DBConnection.seConnecter();
			pstmt = cnx.prepareStatement(SUPPRIMER);
			pstmt.setString(1, nom);
			pstmt.setString(2, prenom);
			nbLignes=pstmt.executeUpdate();
		}catch(SQLException e){
			throw new DALException ("probleme methode supprimer()",e);
		}finally{
			DBConnection.seDeconnecter(pstmt, cnx);
		}
		return nbLignes;
	}
	
	/**
	 * Methode qui permet de <font color="red">modifier un élève</font>
	 * @param adresse
	 * @param nom
	 * @param prenom
	 * @throws DALException : propage l'exception en <strong>DALException</strong>
	 * @finally <em>ferme les connexions ouvertes</em>
	 */
	@Override
	public int modifier(String adresse,String nom, String prenom) throws DALException{
		Connection cnx=null;
		PreparedStatement pstmt=null;
		int nbLignes=0;
		
		try{
			cnx = DBConnection.seConnecter();
			pstmt=cnx.prepareStatement(MODIFIER);
			pstmt.setString(1,adresse);
			pstmt.setString(2,nom);
			pstmt.setString(3,prenom);
			nbLignes=pstmt.executeUpdate();
		}catch (SQLException e){
			throw new DALException ("probleme methode modifier()",e);
		}finally{
			DBConnection.seDeconnecter(pstmt, cnx);
		}
		return nbLignes;
	}
	
}

package fr.eni.cours.dal.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni.cours.bo.Eleve;
import fr.eni.cours.dal.DALException;
import fr.eni.cours.dal.DAOEleve;
import fr.eni.cours.util.DBConnexion;

public class DAOEleveJdbcImpl implements DAOEleve{

	//declaration des constantes
/*	
    private final static String SELECT_ALL = "EXEC dbo.listerEleve";
	private final static String RECHERCHER= "EXEC dbo.rechercherEleve @nom=?, @prenom=?";
	private final static String INSERER = "EXEC dbo.insererEleve @nom=?, @prenom=? @adresse=?";
	private final static String SUPPRIMER = "EXEC dbo.supprimerEleve @nom=?, @prenom=?";
	private final static String MODIFIER = "EXEC dbo.modifierEleve @nom=?, @prenom=? @adresse=?";
	private final static String COMPTER = "@nbre=? EXEC dbo.compterEleve";
*/
	/**
	 * Methode qui permet de lister tous les élèves present en BDD
	 * @return La liste peut être vide mais jamais <code>null</code>
	 * @throws DALException : propage l'exception en DALException
	 * @throws SQLException 
	 * @finally ferme les connexions ouvertes
	 */
	@Override
	public ArrayList<Eleve> lister() throws DALException, SQLException {
		//declaration des variables et objets et initialisation
		Connection cnx=null;
		CallableStatement callstmt=null;//on l'utilise lorsqu'il n'y a pas de parametre dans la requete
		ResultSet rs=null;
		ArrayList<Eleve> listeEleves = new ArrayList<Eleve>(); //on instancie la liste, donc elle ne sera pas null
		Eleve eleve= null;

		cnx = DBConnexion.seConnecter();
		try{
			callstmt=cnx.prepareCall("{call listerEleve }");		//creation du statement	
			rs=callstmt.executeQuery(); //execution de la requete. Ici la methode executeQuery nous ramène un resultat
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
			cnx.commit();
		}catch (SQLException e){
			cnx.rollback();
			throw new DALException ("Probleme methode lister()",e);
		}finally{
			DBConnexion.seDeconnecter(callstmt, cnx);
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
	@Override
	public Eleve rechercher(String nom, String prenom) throws DALException{
		Connection cnx=null;
		CallableStatement callstmt=null;
		ResultSet rs=null;
		Eleve eleve = new Eleve();

		cnx = DBConnexion.seConnecter();
		try{
			callstmt=cnx.prepareCall("{call rechercherEleve(?,?) }");
			callstmt.setString(1, nom);
			callstmt.setString(2, prenom);
			rs=callstmt.executeQuery();
			while (rs.next()){
				eleve.setNom(rs.getString("nom"));				
				eleve.setPrenom(rs.getString("prenom"));
				eleve.setAdresse(rs.getString("adresse"));
			}
		}catch (SQLException e){
			throw new DALException ("Probleme methode rechercher()",e);
		}finally{
			DBConnexion.seDeconnecter(callstmt, cnx);
		}
		return eleve;
	}
	
	/**
	 * Methode ajouter un élève
	 * @param un eleve
	 * @throws DALException propage l'exception en DALException
	 */
	@Override
	public int ajouter(Eleve eleve) throws DALException{
		Connection cnx=null;
		CallableStatement callstmt=null;
		int nbLignes = 0;

		cnx = DBConnexion.seConnecter();
		try{
			callstmt=cnx.prepareCall("{call insererEleve(?,?,?) }");
			callstmt.setString(1, eleve.getNom());
			callstmt.setString(2, eleve.getPrenom());
			callstmt.setString(3, eleve.getAdresse());
			nbLignes=callstmt.executeUpdate();
		} catch (SQLException e){
			throw new DALException ("Probleme methode ajouter()",e);
		}finally{
			DBConnexion.seDeconnecter(callstmt, cnx);
		}
		return nbLignes;
	}
	
	/**
	 * Methode supprimer un élève. On utilise une procédure stockée.
	 * @param nom
	 * @param prenom
	 * @throws DALException : propage l'exception en DALException
	 */
	@Override
	public int supprimer(String nom, String prenom) throws DALException{
		Connection cnx=null;
		CallableStatement callstmt = null;
		int nbLigne=0;

		cnx = DBConnexion.seConnecter();
		try{
			callstmt = cnx.prepareCall("{call supprimerEleve(?,?) }");
			callstmt.setString(1, nom);
			callstmt.setString(2, prenom);
			nbLigne=callstmt.executeUpdate();
		}catch(SQLException e){
			throw new DALException ("Probleme methode supprimer()",e);
		}finally{
			DBConnexion.seDeconnecter(callstmt, cnx);
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
	@Override
	public int modifier(String adresse,String nom, String prenom) throws DALException{
		Connection cnx=null;
		CallableStatement callstmt=null;
		int nbLigne=0;

		cnx = DBConnexion.seConnecter();
		try{
			callstmt=cnx.prepareCall("{call modifierEleve(?,?,?)} ");
			callstmt.setString(3,adresse);
			callstmt.setString(1,nom);
			callstmt.setString(2,prenom);
			nbLigne=callstmt.executeUpdate();
		}catch (SQLException e){
			throw new DALException ("Probleme methode modifier()",e);
		}finally{
			DBConnexion.seDeconnecter(callstmt, cnx);
		}
		return nbLigne;
	}
	/**
	 * @return
	 */
	@Override	
	public int compter() throws DALException {
		Connection cnx = null;
		CallableStatement callstmt = null;
		
		int nbEleves =0;
		
		cnx = DBConnexion.seConnecter();
		
		try {
			callstmt = cnx.prepareCall("{?= call compterEleve }");
			callstmt.registerOutParameter(1, java.sql.Types.INTEGER);
			callstmt.execute();
			nbEleves = callstmt.getInt(1);
			
		} catch (SQLException e) {
			throw new DALException ("Probleme methode compter()",e);
		}finally{
			DBConnexion.seDeconnecter(callstmt, cnx);
		}
		
		return nbEleves;
	}
	
}

















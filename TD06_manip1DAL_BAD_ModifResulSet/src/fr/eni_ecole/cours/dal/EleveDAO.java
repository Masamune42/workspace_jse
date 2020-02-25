package fr.eni_ecole.cours.dal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni_ecole.cours.bo.Eleve;
import fr.eni_ecole.cours.util.DBConnection;

public class EleveDAO {

	//declaration des constantes
	private final static String SELECT_ALL = "select * from eleves;";
	private final static String RECHERCHER= "select * from eleves where nom = ? and prenom = ?;";

	/**
	 * LECTURE DES DONNEES DANS LE RESULTSET
	 * Methode qui permet de lister les élèves en testant les champs de la BDD qui
	 * acceptent du NULL
	 * @return La liste peut être vide mais jamais <code>null</code>
	 * @throws SQLException : propage l'exception
	 * @finally ferme les connexions ouvertes
	 */
	public static ArrayList<Eleve> lister() throws SQLException{
		Connection cnx=null;
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Eleve> listeEleves = new ArrayList<Eleve>();
		try{
			cnx = DBConnection.seConnecter();
			stmt=cnx.createStatement();			
			rs=stmt.executeQuery(SELECT_ALL);
			Eleve eleve;
			while (rs.next()){
				eleve = new Eleve();
				eleve.setId(rs.getInt("id")); //la chaine de caractère represente le nom de colonne dans la BDD
				eleve.setNom(rs.getString("nom"));
				eleve.setPrenom(rs.getString("prenom"));
				//on teste si l'adresse ramené par la BDD est NULL
				rs.getString("adresse");
				//wasNull retourne un boolean à true
				//si le champ de la dernière opération du rs contient une valeur null
				if (rs.wasNull()){
					eleve.setAdresse("inconnu");
				}else{
					eleve.setAdresse(rs.getString("adresse"));
				}
				listeEleves.add(eleve);				
			}
		}finally{
			if (stmt!=null) stmt.close();
			//if (cnx!=null) cnx.close();
			DBConnection.closeConnexions(stmt, cnx);
		}
		
		return listeEleves;
	}

	/**
	 * AJOUT DES DONNEES DANS LE RESULTSET
	 * Methode constitue la liste des élèves via le rs et ajoute un nouvel eleve au rs 
	 * @param nom
	 * @param prenom
	 * @return un eleve
	 * @throws SQLException : propage l'exception
	 * @finally ferme les connexions ouvertes
	 */
	public static ArrayList<Eleve> insertionRS() throws SQLException{
		Connection cnx=null;
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Eleve> listeEleves = new ArrayList<Eleve>();
		BufferedReader br = null;
		String reponse = null;
		try{
			cnx = DBConnection.seConnecter();
			//le ResultSet doit obligatoirement être de type CONCUR_UPDATABLE pour
			//pouvoir être modifié
			stmt=cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=stmt.executeQuery(SELECT_ALL);
			Eleve eleve;
			while (rs.next()){
				eleve = new Eleve();
				eleve.setNom(rs.getString("nom"));				
				eleve.setPrenom(rs.getString("prenom"));
				eleve.setAdresse(rs.getString("adresse"));
				listeEleves.add(eleve);				
			}
			// declaration du champ de saisie dans la console et conservation de la saisie dans un buffer
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("saisir les valeurs du nouvel enregistrement");
			// le pointeur d'enregistrement doit être positionné sur une ligne spéciale du rs
			rs.moveToInsertRow();
			System.out.println("nom : ");
			reponse =br.readLine();
			rs.updateString("nom", reponse);
			System.out.println("prenom : ");
			reponse =br.readLine();
			rs.updateString("prenom", reponse);
			System.out.println("adresse : ");
			reponse =br.readLine();
			rs.updateString("adresse", reponse);
			// validation de l'enregistrement et insertion dans la BDD
			rs.insertRow();
		} catch (IOException e){
			e.printStackTrace();
		} finally{
			if (rs!=null) rs.close();
			if (stmt!=null) stmt.close();
			//if (cnx!=null) cnx.close();
			DBConnection.closeConnexions(stmt, cnx);
		}
		
		return listeEleves;
	}
	
	
	
	/**
	 * MODIFICATION DES DONNEES DANS LE RESULTSET
	 * Methode qui recherche un élève et modifie son adresse 
	 * (nous partons sur l'hypothèse que le rs ne ramène qu'un enregistrement, pour simplifier)
	 * @param nom
	 * @param prenom
	 * @return un eleve
	 * @throws SQLException : propage l'exception
	 * @finally ferme les connexions ouvertes
	 */
	public static Eleve modificationRS(String nom, String prenom) throws SQLException{
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Eleve eleve = new Eleve();
		BufferedReader br = null;
		String reponse = null;
		try{
			cnx = DBConnection.seConnecter();
			//le ResultSet doit obligatoirement être de type CONCUR_UPDATABLE pour
			//pouvoir être modifié
			pstmt=cnx.prepareStatement(RECHERCHER, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, nom);
			pstmt.setString(2, prenom);
			rs=pstmt.executeQuery();
			while (rs.next()){
				eleve.setNom(rs.getString("nom"));				
				eleve.setPrenom(rs.getString("prenom"));
				rs.getString("adresse");
				// declaration du champ de saisie dans la console et conservation de la saisie dans un buffer
				br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("adresse actuelle : "+rs.getString("adresse"));
				System.out.println("saisir la nouvelle adresse (ou entrer pour conserver l'adresse actuelle) : ");
				//chargement du buffer dans la chaine reponse
				reponse = br.readLine();
				//teste ce qu'il y a dans la reponse
				if (!reponse.equals("")){
					//modification de l'adresse dans la BDD 
					//(1er argument:le nom de la colonne dans la BDD - 2eme argument: la nouvelle valeur)
					rs.updateString("adresse", reponse);
				}
				//la(les) modification(s) doit(doivent) être validée(s) ou annulée(s)
				System.out.println("Voulez-vous valider la modification sur l'adresse (o/n):");
				reponse= br.readLine();
				//teste la reponse
				if (reponse.toLowerCase().equals("o")){
					//modification validée
					rs.updateRow();
				} else {
					//modification annulée
					rs.cancelRowUpdates();
				}
				eleve.setAdresse(rs.getString("adresse"));
				
			}
		} catch (IOException e){
			e.printStackTrace();
		} finally{
			if (rs!=null) rs.close();
			if (pstmt!=null) pstmt.close();
			//if (cnx!=null) cnx.close();
			DBConnection.closeConnexions(pstmt, cnx);
		}
		
		return eleve;
	}

	/**
	 * SUPPRESSION DES DONNEES DANS LE RESULTSET
	 * Methode qui recherche un élève et le supprime 
	 * (nous partons sur l'hypothèse que le rs ne ramène qu'un enregistrement, pour simplifier)
	 * @param nom
	 * @param prenom
	 * @return un eleve
	 * @throws SQLException : propage l'exception
	 * @finally ferme les connexions ouvertes
	 */
	public static Eleve suppressionRS(String nom, String prenom) throws SQLException{
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Eleve eleve = new Eleve();
		BufferedReader br = null;
		String reponse = null;
		try{
			cnx = DBConnection.seConnecter();
			//désactive le mode autocommit
			cnx.setAutoCommit(false);
			//le ResultSet doit obligatoirement être de type CONCUR_UPDATABLE pour
			//pouvoir être modifié
			pstmt=cnx.prepareStatement(RECHERCHER, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, nom);
			pstmt.setString(2, prenom);
			rs=pstmt.executeQuery();
			while (rs.next()){
				eleve.setNom(rs.getString("nom"));				
				eleve.setPrenom(rs.getString("prenom"));
				eleve.setAdresse(rs.getString("adresse"));
				// declaration du champ de saisie dans la console et conservation de la saisie dans un buffer
				br = new BufferedReader(new InputStreamReader(System.in));
				//la suppression doit elle avoir lieu
				System.err.println("enregistrement à supprimer : "+eleve.toString());
				//suppression validée
				// la suppression d'une ligne est effectuée en positionnant le pointeur sur la ligne à supprimer
				rs.deleteRow();
				// position du pointeur après suppression
				System.out.println("le pointeur est maintenant sur la ligne : "+rs.getRow());
				System.out.println("Suppression définitive de cet enregistrement (o/n):");
				reponse= br.readLine();
				//teste la reponse
				if (reponse.toLowerCase().equals("o")){
					//validation de la suppression
					cnx.commit();
				} else {
					//annulation de la suppression
					cnx.rollback();
				}
			}
		} catch (IOException e){
			e.printStackTrace();
		} finally{
			if (rs!=null) rs.close();
			if (pstmt!=null) pstmt.close();
			//if (cnx!=null) cnx.close();
			DBConnection.closeConnexions(pstmt, cnx);
		}
		return eleve;
	}

}

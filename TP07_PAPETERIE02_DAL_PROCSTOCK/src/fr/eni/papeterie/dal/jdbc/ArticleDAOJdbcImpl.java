/**
 * 
 */
package fr.eni.papeterie.dal.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DBConnection;

/**
 * @author Eni Ecole
 * 
 */
public class ArticleDAOJdbcImpl {

	private static final String TYPE_STYLO = "STYLO";
	private static final String TYPE_RAMETTE = "RAMETTE";

/*	private static final String sqlSelectById = "select idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type "
			+ " from articles where idArticle = ?";
	private static final String sqlSelectAll = "select idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type "
			+ " from articles";
	private static final String sqlUpdate = "update articles set reference=?,marque=?,designation=?,prixUnitaire=?,qteStock=?,grammage=?,couleur=? where idArticle=?";
	private static final String sqlInsert = "insert into articles(reference,marque,designation,prixUnitaire,qteStock,type,grammage,couleur) values(?,?,?,?,?,?,?,?)";
	private static final String sqlDelete = "delete from articles where idArticle=?";
*/
/*	private static final String sqlSelectByMarque = "select reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type "
			+ " from articles where marque = ?";
	private static final String sqlSelectByMotCle = "select reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type "
			+ " from articles where marque like ? or designation like ?";
*/
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws DALException
	 */
	public static Article selectById(int id) throws DALException {
		Connection cnx = null;
		CallableStatement callstmt = null;
		ResultSet rs = null;
		Article art = null;
		
		cnx=DBConnection.seConnecter();
		try {
			callstmt = cnx.prepareCall("{call rechercherArticle(?) }");
			callstmt.setInt(1, id);

			rs = callstmt.executeQuery();
			if (rs.next()) {

				if (TYPE_STYLO.equalsIgnoreCase(rs.getString("type").trim())) {

					art = new Stylo(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference").trim(),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getString("couleur"));
				}
				if (TYPE_RAMETTE.equalsIgnoreCase(rs.getString("type").trim())) {
					art = new Ramette(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference").trim(),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getInt("grammage"));
				}
			}

		} catch (SQLException e) {
			throw new DALException("selectById failed - id = " + id, e);
		} finally {
			DBConnection.seDeconnecter(callstmt, cnx);
		}
		return art;
	}

	/**
	 * 
	 * @return
	 * @throws DALException
	 */
	public static List<Article> selectAll() throws DALException {
		Connection cnx = null;
		CallableStatement callstmt = null;
		ResultSet rs = null;
		List<Article> liste = new ArrayList<Article>();
		
		cnx=DBConnection.seConnecter();
		try {
			callstmt = cnx.prepareCall("{call listerArticle }");
			rs = callstmt.executeQuery();
			Article art = null;

			while (rs.next()) {
				if (TYPE_STYLO.equalsIgnoreCase(rs.getString("type").trim())) {

					art = new Stylo(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference").trim(),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getString("couleur"));
				}
				if (TYPE_RAMETTE.equalsIgnoreCase(rs.getString("type").trim())) {
					art = new Ramette(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference").trim(),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getInt("grammage"));
				}
				liste.add(art);
			}
		} catch (SQLException e) {
			throw new DALException("selectAll failed - ", e);
		} finally {
			DBConnection.seDeconnecter(callstmt, cnx);
		}
		return liste;

	}

	/**
	 * 
	 * @param data
	 * @throws DALException
	 */
	public static void update(Article data) throws DALException {
		Connection cnx = null;
		CallableStatement callstmt = null;
		
		cnx=DBConnection.seConnecter();
		try {
			callstmt = cnx.prepareCall("{call modifierArticle(?,?,?,?,?,?,?,?) }");
			callstmt.setString(1, data.getReference());
			callstmt.setString(2, data.getMarque());
			callstmt.setString(3, data.getDesignation());
			callstmt.setFloat(4, data.getPrixUnitaire());
			callstmt.setInt(5, data.getQteStock());
			callstmt.setInt(8, data.getIdArticle());
			if (data instanceof Ramette) {
				Ramette r = (Ramette) data;
				callstmt.setInt(6, r.getGrammage());
				callstmt.setNull(7, Types.VARCHAR);
			}
			if (data instanceof Stylo) {
				Stylo s = (Stylo) data;
				callstmt.setNull(6, Types.INTEGER);
				callstmt.setString(7, s.getCouleur());
			}

			callstmt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Update article failed - " + data, e);
		} finally {
			DBConnection.seDeconnecter(callstmt, cnx);
		}

	}

	/**
	 * 
	 * @param data
	 * @throws DALException
	 */
	@SuppressWarnings("resource")
	public static void insert(Article data) throws DALException {
		Connection cnx = null;
		CallableStatement callstmt = null;

		cnx=DBConnection.seConnecter();
		try {
			callstmt = cnx.prepareCall("{call insererArticle(?,?,?,?,?,?,?,?) }");
			callstmt.setString(1, data.getReference());
			callstmt.setString(2, data.getMarque());
			callstmt.setString(3, data.getDesignation());
			callstmt.setFloat(4, data.getPrixUnitaire());
			callstmt.setInt(5, data.getQteStock());
			if (data instanceof Ramette) {
				Ramette r = (Ramette) data;
				callstmt.setInt(6, r.getGrammage());
				callstmt.setNull(7, Types.VARCHAR);
				callstmt.setString(8, TYPE_RAMETTE);
			}
			if (data instanceof Stylo) {
				Stylo s = (Stylo) data;
				callstmt.setNull(6, Types.INTEGER);
				callstmt.setString(7, s.getCouleur());
				callstmt.setString(8, TYPE_STYLO);
			}

			int nbRows = callstmt.executeUpdate();
			if (nbRows == 1) {
				callstmt = cnx.prepareCall("{ ?=call recupIdArticle }");
				callstmt.registerOutParameter(1,java.sql.Types.INTEGER);
				callstmt.execute();
				data.setIdArticle(callstmt.getInt(1));
			}

		} catch (SQLException e) {
			throw new DALException("Insert article failed - " + data, e);
		} finally {
			DBConnection.seDeconnecter(callstmt, cnx);
		}
	}

	/**
	 * 
	 * @param id
	 * @throws DALException
	 */
	public static void delete(int id) throws DALException {
		Connection cnx = null;
		CallableStatement callstmt = null;
		
		cnx=DBConnection.seConnecter();
		try {
			// l'intégrité référentielle s'occupe d'invalider la suppression
			// si l'article est référencé dans une ligne de commande
			callstmt = cnx.prepareCall("{call supprimerArticle(?) }");
			callstmt.setInt(1, id);
			callstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete article failed - id=" + id, e);
		} finally {
			DBConnection.seDeconnecter(callstmt, cnx);
		}
	}

/*	
	public List<Article> selectByMarque(String marque) throws DALException {
		Connection cnx = null;
		CallableStatement callstmt = null;
		ResultSet rs = null;
		List<Article> liste = new ArrayList<Article>();
		
		cnx=DBConnection.seConnecter();
		try {
			callstmt = cnx.prepareStatement(sqlSelectByMarque);
			callstmt.setString(1, marque);
			rs = callstmt.executeQuery();
			Article art = null;

			while (rs.next()) {
				if (TYPE_STYLO.equalsIgnoreCase(rs.getString("type").trim())) {

					art = new Stylo(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference").trim(),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getString("couleur"));
				}
				if (TYPE_RAMETTE.equalsIgnoreCase(rs.getString("type").trim())) {
					art = new Ramette(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference").trim(),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getInt("grammage"));
				}
				liste.add(art);
			}
		} catch (SQLException e) {
			throw new DALException("selectByMarque failed - ", e);
		} finally {
			DBConnection.seDeconnecter(callstmt, cnx);
		}
		return liste;
	}
*/
/*	
	public List<Article> selectByMotCle(String motCle) throws DALException {
		Connection cnx = null;
		CallableStatement callstmt = null;
		ResultSet rs = null;
		List<Article> liste = new ArrayList<Article>();
		
		cnx=DBConnection.seConnecter();
		try {
			callstmt = cnx.prepareStatement(sqlSelectByMotCle);
			callstmt.setString(1, motCle);
			rs = callstmt.executeQuery();
			Article art = null;

			while (rs.next()) {
				if (TYPE_STYLO.equalsIgnoreCase(rs.getString("type").trim())) {

					art = new Stylo(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference").trim(),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getString("couleur"));
				}
				if (TYPE_RAMETTE.equalsIgnoreCase(rs.getString("type").trim())) {
					art = new Ramette(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference").trim(),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getInt("grammage"));
				}
				liste.add(art);
			}
		} catch (SQLException e) {
			throw new DALException("selectByMotCle failed - ", e);
		} finally {
			DBConnection.seDeconnecter(callstmt, cnx);
		}
		return liste;
	}
*/
}

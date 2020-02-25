/**
 * 
 */
package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;


/**
 * @author Eni Ecole
 * 
 */
public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String TYPE_STYLO = "STYLO";
	private static final String TYPE_RAMETTE = "RAMETTE";

	private static final String SQL_SELECT_BY_ID = "select idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type "
			+ " from articles where idArticle = ?";
	private static final String SQL_SELECT_ALL = "select idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type "
			+ " from articles";
	private static final String SQL_UPDATE = "update articles set reference=?,marque=?,designation=?,prixUnitaire=?,qteStock=?,grammage=?,couleur=? where idArticle=?";
	private static final String SQL_INSERT = "insert into articles(reference,marque,designation,prixUnitaire,qteStock,type,grammage,couleur) values(?,?,?,?,?,?,?,?)";
	private static final String SQL_DELETE = "delete from articles where idArticle=?";
	private static final String SQL_SELECT_BY_MARQUE = "select reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type "
			+ " from articles where marque = ?";
	private static final String SQL_SELECT_BY_MOT_CLE = "select reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type "
			+ " from articles where marque like ? or designation like ?";
	

	@Override
	public Article selectById(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Article art = null;
		try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(SQL_SELECT_BY_ID);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()){

				if (TYPE_STYLO.equalsIgnoreCase(rs.getString("type").trim())){

					art = new Stylo(rs.getInt("idArticle"),
							rs.getString("marque"),
							rs.getString("reference").trim(),
							rs.getString("designation"),
							rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"),
							rs.getString("couleur"));
				}
				if (TYPE_RAMETTE.equalsIgnoreCase(rs.getString("type").trim())){
					art = new Ramette(rs.getInt("idArticle"),
							rs.getString("marque"),
							rs.getString("reference").trim(),
							rs.getString("designation"),
							rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"),
							rs.getInt("grammage"));
				}
			}

		} catch (SQLException e) {
			throw new DALException("selectById failed - id = " + id , e);
		} finally {
			JdbcTools.seDeconnecter(pstmt, cnx);
		}
		return art;
	}

	@Override
	public List<Article> selectAll() throws DALException {
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Article> liste = new ArrayList<Article>();
		try {
			cnx = JdbcTools.getConnection();
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SQL_SELECT_ALL);
			Article art = null;

			while (rs.next()) {
				if (TYPE_STYLO.equalsIgnoreCase(rs.getString("type").trim())){

					art = new Stylo(rs.getInt("idArticle"),
							rs.getString("marque"),
							rs.getString("reference").trim(),
							rs.getString("designation"),
							rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"),
							rs.getString("couleur"));
				}
				if (TYPE_RAMETTE.equalsIgnoreCase(rs.getString("type").trim())){
					art = new Ramette(rs.getInt("idArticle"),
							rs.getString("marque"),
							rs.getString("reference").trim(),
							rs.getString("designation"),
							rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"),
							rs.getInt("grammage"));
				}
				liste.add(art);
			}
		} catch (SQLException e) {
			throw new DALException("selectAll failed - " , e);
		} finally {
			JdbcTools.seDeconnecter(stmt, cnx);
		}
		return liste;

	}



	@Override
	public void update(Article data) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(SQL_UPDATE);
			pstmt.setString(1, data.getReference());
			pstmt.setString(2, data.getMarque());
			pstmt.setString(3, data.getDesignation());
			pstmt.setFloat(4, data.getPrixUnitaire());
			pstmt.setInt(5, data.getQteStock());
			pstmt.setInt(8, data.getIdArticle());
			if (data instanceof Ramette) {
				Ramette r = (Ramette) data;
				pstmt.setInt(6, r.getGrammage());
				pstmt.setNull(7, Types.VARCHAR);
			}
			if (data instanceof Stylo) {
				Stylo s = (Stylo) data;
				pstmt.setNull(6, Types.INTEGER);
				pstmt.setString(7, s.getCouleur());
			}

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Update article failed - " + data, e);
		} finally {
			JdbcTools.seDeconnecter(pstmt, cnx);
		}

	}

	@Override
	public void insert(Article data) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, data.getReference());
			pstmt.setString(2, data.getMarque());
			pstmt.setString(3, data.getDesignation());
			pstmt.setFloat(4, data.getPrixUnitaire());
			pstmt.setInt(5, data.getQteStock());
			if (data instanceof Ramette) {
				Ramette r= (Ramette) data;
				pstmt.setString(6, TYPE_RAMETTE);
				pstmt.setInt(7, r.getGrammage());
				pstmt.setNull(8, Types.VARCHAR);
			}
			if (data instanceof Stylo) {
				Stylo s = (Stylo) data;
				pstmt.setString(6, TYPE_STYLO);
				pstmt.setNull(7, Types.INTEGER);
				pstmt.setString(8, s.getCouleur());
			}

			int nbRows = pstmt.executeUpdate();
			if(nbRows == 1){
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()){
					data.setIdArticle(rs.getInt(1));
				}

			}

		}catch(SQLException e){
			throw new DALException("Insert article failed - " + data, e);
		}
		finally {
			JdbcTools.seDeconnecter(pstmt, cnx);
		}
	}





	@Override
	public void delete(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		try {		
			cnx = JdbcTools.getConnection();
			//l'intégrité référentielle s'occupe d'invalider la suppression
			//si l'article est référencé dans une ligne de commande
			pstmt = cnx.prepareStatement(SQL_DELETE);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete article failed - id=" + id, e);
		} finally {
			JdbcTools.seDeconnecter(pstmt, cnx);
		}		
	}

	@Override
	public List<Article> selectByMarque(String marque) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Article> liste = new ArrayList<Article>();
		try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(SQL_SELECT_BY_MARQUE);
			pstmt.setString(1, marque);
			rs = pstmt.executeQuery();
			Article art = null;

			while (rs.next()) {
				if (TYPE_STYLO.equalsIgnoreCase(rs.getString("type").trim())){

					art = new Stylo(rs.getInt("idArticle"),
							rs.getString("marque"),
							rs.getString("reference").trim(),
							rs.getString("designation"),
							rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"),
							rs.getString("couleur"));
				}
				if (TYPE_RAMETTE.equalsIgnoreCase(rs.getString("type").trim())){
					art = new Ramette(rs.getInt("idArticle"),
							rs.getString("marque"),
							rs.getString("reference").trim(),
							rs.getString("designation"),
							rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"),
							rs.getInt("grammage"));
				}
				liste.add(art);
			}
		} catch (SQLException e) {
			throw new DALException("selectByMarque failed - " , e);
		} finally {
			JdbcTools.seDeconnecter(pstmt, cnx);
		}
		return liste;
	}

	@Override
	public List<Article> selectByMotCle(String motCle) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Article> liste = new ArrayList<Article>();
		try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(SQL_SELECT_BY_MOT_CLE);
			pstmt.setString(1, motCle);
			rs = pstmt.executeQuery();
			Article art = null;

			while (rs.next()) {
				if (TYPE_STYLO.equalsIgnoreCase(rs.getString("type").trim())){

					art = new Stylo(rs.getInt("idArticle"),
							rs.getString("marque"),
							rs.getString("reference").trim(),
							rs.getString("designation"),
							rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"),
							rs.getString("couleur"));
				}
				if (TYPE_RAMETTE.equalsIgnoreCase(rs.getString("type").trim())){
					art = new Ramette(rs.getInt("idArticle"),
							rs.getString("marque"),
							rs.getString("reference").trim(),
							rs.getString("designation"),
							rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"),
							rs.getInt("grammage"));
				}
				liste.add(art);
			}
		} catch (SQLException e) {
			throw new DALException("selectByMotCle failed - " , e);
		} finally {
			JdbcTools.seDeconnecter(pstmt, cnx);
		}
		return liste;
	}
}

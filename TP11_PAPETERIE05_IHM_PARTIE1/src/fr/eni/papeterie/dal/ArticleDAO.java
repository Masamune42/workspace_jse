/**
 * 
 */
package fr.eni.papeterie.dal;

import java.util.List;

import fr.eni.papeterie.bo.Article;

/**
 * @author Eni Ecole
 *
 */
public interface ArticleDAO {
	
	//Sélectionner un article par son idArticle
	public Article selectById(int id) throws DALException;
	
	//Sélectionner tous les articles 
	public List<Article> selectAll() throws DALException;
	
	//Modifier les attributs d'un article connu en BD
	public void update(Article data) throws DALException;
	
	//Insérer un nouvel article
	public void insert(Article data) throws DALException;
	
	//Supprimer un article
	public void delete(int id) throws DALException;
	
	//Sélectionner les articles par marque
	public List<Article> selectByMarque(String marque) throws DALException;
	
	//Sélectionner les articles par mot clé
	//On recherche le mot clé dans la désignation et la marque
	public List<Article> selectByMotCle(String motCle) throws DALException;

}

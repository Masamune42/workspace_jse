package fr.eni.papeterie.dal;

import fr.eni.papeterie.bo.Article;

public class DAOFactory {
	
	@SuppressWarnings("unchecked")
	public static DAO<Article> getArticleDAO()  {
		DAO<Article> articleDAO=null;
		try {
			articleDAO=(DAO<Article> ) Class.forName("fr.eni.papeterie.dal.jdbc.ArticleDAOJdbcImpl").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articleDAO; 
	}

}

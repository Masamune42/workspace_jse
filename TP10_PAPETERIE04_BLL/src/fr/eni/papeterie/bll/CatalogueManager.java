package fr.eni.papeterie.bll;

import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAOFactory;

/**
 * @author Eni Ecole
 * Un seul catalogue simultanément en mémoire.
 */
public class CatalogueManager {
	

	private List<Article> catalogue;
	
	private static CatalogueManager instance=null;
	
	private ArticleDAO daoArticles;
	
	

	private CatalogueManager() throws BLLException{
			//Instancier le Data Access Object
		 	daoArticles =DAOFactory.getArticleDAO();
		
		 	//Charger le catalogue
			try {
				catalogue = daoArticles.selectAll();
			} catch (DALException e) {
				throw new BLLException("Echec du chargement du catalogue - ", e);
			}
	}
	
	public static synchronized CatalogueManager getInstance() throws BLLException{	
		if (instance == null){
			instance = new CatalogueManager();	
		}
		return instance;
	}

	
	
	public List<Article> getCatalogue() {
		return catalogue;
	}

	
	/**
	 * Ajout d'un article au catalogue
	 * @param newArticle
	 * @return index du nouvel article dans le catalogue
	 * @throws BLLException 
	 */
	public int addArticle(Article newArticle) throws BLLException {
		if(newArticle.getIdArticle()!=null){
			throw new BLLException("Article deja existant.");
		}
		try {
			validerArticle(newArticle);
			daoArticles.insert(newArticle);
			catalogue.add(newArticle);
		} catch (DALException e) {
			throw new BLLException("Echec addArticle", e);
		}
		return catalogue.size()-1;
	}

	/**
	 * updateArticle : Modifier un article du catalogue
	 * @param article
	 * @throws BLLException
	 */
	public void updateArticle(Article article) throws BLLException{
		try {
			validerArticle(article);
			daoArticles.update(article);
			
		} catch (DALException e) {
			throw new BLLException("Echec updateArticle-article:"+article, e);
		}
	}
	
	
	/**
	 * extraire un article du catalogue
	 * @param ref
	 * @return
	 * @throws Exception
	 */
	public Article getArticle(int index) {
		return catalogue.get(index);
	}
	
	
	/**
	 * Supprimer un article du catalogue
	 * @param index
	 * @throws BLLException
	 */
	public void removeArticle(int index) throws BLLException{
		try {
			daoArticles.delete(catalogue.get(index).getIdArticle());
			catalogue.remove(index);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression de l'article - ", e);
		}
		
	}
	
	/**
	 * Valider les données d'un article
	 * @param a
	 * @throws BLLException
	 */
	public void validerArticle(Article a) throws BLLException
	{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		
		if(a==null){
			throw new BLLException("Article null");
		}
		//Les attributs des articles sont obligatoires
		if(a.getReference()==null || a.getReference().trim().length()==0){
			sb.append("La reference article est obligatoire.\n");
			valide = false;
		}
		if(a.getMarque()==null || a.getMarque().trim().length()==0){
			sb.append("La marque  est obligatoire.\n");
			valide = false;
		}
		if(a.getDesignation()==null || a.getDesignation().trim().length()==0){
			sb.append("La designation  est obligatoire.\n");
			valide = false;
		}
		if(a instanceof Ramette && ((Ramette)a).getGrammage()<=0){
			sb.append("Le grammage doit avoir une valeur positive.\n");
			valide = false;
		}
		if(a instanceof Stylo ){
			Stylo s = (Stylo) a;
			if(s.getCouleur()==null || s.getCouleur().trim().length()==0){
				sb.append("La couleur pour un stylo  est obligatoire.\n");
				valide = false;
			}
		}
		
		if(!valide){
			throw new BLLException(sb.toString());
		}

	}
	
	

}

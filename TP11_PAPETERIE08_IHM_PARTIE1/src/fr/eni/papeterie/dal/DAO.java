package fr.eni.papeterie.dal;

import java.util.List;

public interface DAO<T> {

	/**
	 * @author Eni Ecole
	 *
	 */
		
		//Sélectionner un business object par son id
		public T selectById(T obj) throws DALException;
		
		//Sélectionner tous les business objects 
		public List<T> selectAll() throws DALException;
		
		//Modifier les attributs d'un business object
		public void update(T data) throws DALException;
		
		//Insérer un nouveau business object
		public void insert(T data) throws DALException;
		
		//Supprimer un business object
		public void delete(T obj) throws DALException;
		
	
}

package fr.eni.cours.dal;

//import fr.eni.cours.dal.jdbc.DAOEleveJdbcImpl;

public class DAOFactory {
	
	public static DAOEleve getDAOEleve() throws DALException {
		DAOEleve daoEleve = null;
		
		try {
			daoEleve= (DAOEleve) Class.forName("fr.eni.cours.dal.jdbc.DAOEleveJdbcImpl").newInstance();
		} catch (InstantiationException e) {
			throw new DALException("Erreur d'instanciation",e);
		} catch (IllegalAccessException e) {
			throw new DALException("Acces illegal",e);
		} catch (ClassNotFoundException e) {
			throw new DALException("Classe non trouvée",e);
		}
//		daoEleve = new DAOEleveJdbcImpl();
		
	return daoEleve;	
	}

	
	//autres instanciations de DAO ...
}

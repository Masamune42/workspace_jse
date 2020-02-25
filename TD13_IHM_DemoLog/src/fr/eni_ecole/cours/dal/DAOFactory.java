package fr.eni_ecole.cours.dal;

// Nous encapsulons la creation d'instance dans une nouvelle classe DAOFactory
// cette classe declare une m�thode static getEleve() qui renvoit le type EleveDAO et non le type r�el de l'instance
// le fait que la methode soit static facilite l'appel � la methode, puisqu'aucune
// instance de DAOFactory n'est alors n�cessaire pour l'utiliser
public class DAOFactory {
	public static EleveDAO getEleveDAO() throws DALException  {
		EleveDAO eleveDAO=null;
		try {
			eleveDAO=(EleveDAO ) Class.forName("fr.eni_ecole.cours.dal.jdbc.EleveDAOJdbcImpl").newInstance();
		} catch (InstantiationException e) {
			throw new DALException ("DAL - " + e.getMessage());
		} catch (IllegalAccessException e) {
			throw new DALException ("DAL - " + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new DALException ("DAL - " + e.getMessage());
		}
		return eleveDAO; 
	}

}

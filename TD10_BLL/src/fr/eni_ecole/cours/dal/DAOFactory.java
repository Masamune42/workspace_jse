package fr.eni_ecole.cours.dal;

// Nous encapsulons la creation d'instance dans une nouvelle classe DAOFactory
// cette classe declare une méthode static getEleve() qui renvoit le type EleveDAO et non le type réel de l'instance
// le fait que la methode soit static facilite l'appel à la methode, puisqu'aucune
// instance de DAOFactory n'est alors nécessaire pour l'utiliser
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

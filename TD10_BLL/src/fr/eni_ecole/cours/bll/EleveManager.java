package fr.eni_ecole.cours.bll;

import java.util.List;

import fr.eni_ecole.cours.bo.Eleve;
import fr.eni_ecole.cours.dal.DALException;
import fr.eni_ecole.cours.bll.BLLException;
import fr.eni_ecole.cours.dal.DAOFactory;
import fr.eni_ecole.cours.dal.EleveDAO;

//Les composants par domaine metier sont appeles des managers
//ainsi le domaine metier (gestion des eleves) est d�fini par EleveManager
//la classe manager est compar� � un chef d'orchestre qui expose les services
//metier � la couche IHM et initie la sauvegarde des donn�es dans la couche DAL

public class EleveManager {

	//attribut liste des eleves
	private List<Eleve> eleves;
	
	//une instance unique sera stock�e dans un attribut static de type du Singleton
	//c'est un attribut priv� pour ne pas injecter d'instance depuis l'exterieur de la classe
	private static EleveManager instance = null;
	
	//attribut eleveDAO
	private EleveDAO eleveDAO;
	
	// le constructeur est priv� pour interdire la creation d'instance du Singleton
	// � l'exterieur de la classe
	private EleveManager() throws BLLException {
		try {
			//instancier la DAO EleveDAO via la Factory
			eleveDAO = DAOFactory.getEleveDAO();
			//et on charge la liste d'�l�ves
			eleves = eleveDAO.lister();
		} catch (DALException e) {
			throw new BLLException("probl�me chargement liste Eleve - "+ e.getMessage());
		}
		
	}

	// methode public getInstance() qui cr�� une seule instance de type EleveManager
	// si l'instance n'est pas cr��e
	public static EleveManager getInstance() throws BLLException{	
		if (instance == null){
			instance = new EleveManager();	
		}
		return instance;
	}


	/**
	 * Liste des �l�ves
	 * @return la liste des �l�ves
	 */
	public List<Eleve> getEleves() {
		return eleves;
	}

	/**
	 * extraire un eleve de la liste
	 * @param index
	 * @return un eleve
	 */
	public Eleve getEleve(int index) {
		return eleves.get(index);
	}

	/**
	 * Ajout d'un eleve � la liste et dans la base de donn�es
	 * @param newEleve
	 * @return index du nouvel eleve dans la liste
	 * @throws DALException erreur couche DAL
	 * @throws BLLException erreur couche BLL
	 */
	public int addEleve(Eleve newEleve) throws BLLException, DALException {
		//rechercher si l'eleve n'existe pas dej� dans la BDD
		Eleve rechEleve = eleveDAO.rechercher(newEleve.getNom(), newEleve.getPrenom());
		if(rechEleve.getNom()!=null){
			throw new BLLException("probl�me �leve d�ja existant.");
		}
		try {
			//valider les infos en fonction des r�gles de gestion
			validerEleve(newEleve);
			//ajouter � la BDD
			eleveDAO.ajouter(newEleve);
			//ajouter � la liste
			eleves.add(newEleve);
		} catch (BLLException e) {
			throw new BLLException("probl�me �chec addListeEleve - "+ e);
		}
		return eleves.size()-1;
	}


	/**
	 * Mise � jour de l'adresse d'un �l�ve dans la liste et la base de donn�es
	 * @throws DALException erreur couche DAL
	 * @throws BLLException erreur couche BLL
	 */
	public void majEleve(int indexEleves, Eleve eleveAffiche) throws BLLException, DALException{

		Eleve eleve = null;
		try {
			//R�cup�ration de l'�l�ve en m�moire
			eleve = eleves.get(indexEleves);
			//Mise � jour de l'adresse de l'�l�ve en m�moire dans la liste
			eleve.setAdresse(eleveAffiche.getAdresse());
			//valider les infos en fonction des r�gles de gestion
			validerEleve(eleve);
			//Mise � jour de la base de donn�es
			eleveDAO.modifier(eleve.getAdresse(), eleve.getNom(), eleve.getPrenom());
		} catch (BLLException e) {
			throw new BLLException ("probl�me �chec updateListeEleve - "+ e);
		}
	}




	/**
	 * Supprimer un eleve de la liste
	 * @param index
	 * @throws DALException erreur couche DAL
	 * @throws SQLException erreur couche BLL
	 */
	public void removeEleve(int index) throws DALException{
			//suppression de la base de donn�es
			eleveDAO.supprimer(eleves.get(index).getNom(), eleves.get(index).getPrenom());
			//suppression de la liste
			eleves.remove(index);
	}

	/**
	 * Valider les donn�es d'un eleve
	 * @param eleve
	 * @throws SQLException
	 */
	public void validerEleve(Eleve eleve) throws BLLException
	{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();

		if(eleve==null){
			throw new BLLException("probl�me article null");
		}
		//Les attributs des eleves sont obligatoires
		if(eleve.getNom()==null || eleve.getNom().trim().length()==0){
			sb.append("Le nom de l'eleve est obligatoire.\n");
			valide = false;
		}
		if(eleve.getPrenom()==null || eleve.getPrenom().trim().length()==0){
			sb.append("Le prenom de l'eleve est obligatoire.\n");
			valide = false;
		}
		if(eleve.getAdresse()==null || eleve.getAdresse().trim().length()==0){
			sb.append("L'adresse est obligatoire.\n");
			valide = false;
		}

		if(!valide){
			throw new BLLException("probl�me(s) : " + sb.toString());
		}

	}
	
}

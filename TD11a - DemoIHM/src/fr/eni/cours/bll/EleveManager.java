package fr.eni.cours.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.cours.bo.Eleve;
import fr.eni.cours.dal.DALException;
import fr.eni.cours.dal.DAOEleve;
import fr.eni.cours.dal.DAOFactory;

public class EleveManager {

	private  List<Eleve> eleves;
	private static EleveManager instance = null;
	private DAOEleve eleveDao;
	
	private EleveManager() throws SQLException, BLLException {
		try {
			eleveDao = DAOFactory.getDAOEleve();
			eleves = eleveDao.lister();
		} catch (DALException e) {
			throw new BLLException("probleme chargement de la liste Eleve", e);
		}
	}
	
	public static EleveManager getInstance() throws SQLException, BLLException {
		if (instance== null) {
			instance = new EleveManager();
		}
		return instance;
	}
	
	//methodes des specifications fonctionnelles (logique metier)
	
	//liste des eleves
	public List<Eleve> getEleves(){
		return eleves;
	}
	
	//extraire un eleve de la liste
	public Eleve getEleve(int index) {
		return eleves.get(index);
	}
	
	//ajouter un eleve à la liste et dans la table eleves de la BDD
	public int addEleve(Eleve newEleve) throws DALException, SQLException, BLLException {
		//rechercher dans la BDD si l'eleve n'existe pas déjà
			Eleve rechEleve= eleveDao.rechercher(newEleve.getNom(), newEleve.getPrenom());
			if (rechEleve.getNom()!= null) {
				throw new BLLException("eleve déjà existant.");
			}
			//verifier certaines regles de gestion
			validerEleve(newEleve);
			// ajouter l'eleve à la BDD
			eleveDao.ajouter(newEleve);
			//ajouter à la liste
			eleves.add(newEleve);
			
			return eleves.size()-1;
	}
	
	
	public void removeEleve(int index) throws DALException, SQLException {
		//suppression de l'eleve de la BDD
		eleveDao.supprimer(eleves.get(index).getNom(), eleves.get(index).getPrenom());
		//suppression dans liste
		eleves.remove(index);
	}
	

	public void majEleve(int index, Eleve dispEleve) throws BLLException, DALException, SQLException {
		Eleve eleve = null;
		//recupere l'eleve en memoire
		eleve = eleves.get(index);
		//mise à jour de l'adresse de l'eleve en memoire dans la liste
		eleve.setAdresse(dispEleve.getAdresse());
		//verifier certaines regles de gestion
		validerEleve(eleve);
		//mise à jour dans la BDD
		eleveDao.modifier(eleve.getAdresse(), eleve.getNom(), eleve.getPrenom());
		
	}
	
	
	public void validerEleve(Eleve eleve) throws BLLException {
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		if (eleve==null) {
			throw new BLLException("probleme eleve inexistant");
		}
		if (eleve.getNom()==null || eleve.getNom().isEmpty()) {
			sb.append("le nom de l'élève est oblibatoire.\n");
			valide=false;
		}
		if (eleve.getPrenom()==null || eleve.getNom().trim().length()==0) {
			sb.append("le prenom de l'élève est oblibatoire.\n");
			valide=false;
		}
		if (eleve.getAdresse()==null || eleve.getAdresse().isEmpty()) {
			sb.append("l'adresse de l'élève est oblibatoire.\n");
			valide=false;
		}
		
		if (!valide) {
			throw new BLLException("probleme : "+sb.toString());
		}
	}
	
}















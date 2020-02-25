package fr.eni.evaluations.dal.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eni.evaluations.bo.Stagiaire;
import fr.eni.evaluations.dal.DAO;

public class StagiaireDAOImplMemorySolution implements DAO<Stagiaire> {


	private int max_no_stagiaire = 1;
	private Map<Integer, Stagiaire> stagiaires = new HashMap<>(); 
	
	@Override
	public void insert(Stagiaire data) throws Exception {
		max_no_stagiaire++;
		stagiaires.put(max_no_stagiaire, data);

	}

	@Override
	public void update(Stagiaire data) throws Exception {
		Stagiaire s = stagiaires.get(data.getNoStagiaire());
		s.setPrenom(data.getPrenom());
		s.setNom(data.getNom());
		s.setMotDePasse(data.getMotDePasse());
		s.setEmail(data.getEmail());
	}

	@Override
	public void delete(Stagiaire data) throws Exception {
		stagiaires.remove(data.getNoStagiaire());

	}

	@Override
	public Stagiaire selectById(int id) throws Exception {
		return stagiaires.get(id);
	}

	@Override
	public List<Stagiaire> selectAll() throws Exception {
		List<Stagiaire> stagiaires = new ArrayList<>(this.stagiaires.values());
		return stagiaires;
	}

}

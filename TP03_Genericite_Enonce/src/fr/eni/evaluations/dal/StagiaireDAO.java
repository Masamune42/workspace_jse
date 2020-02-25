package fr.eni.evaluations.dal;

import java.util.List;

import fr.eni.evaluations.bo.Stagiaire;

public interface StagiaireDAO {

	public void insert(Stagiaire data) throws Exception;
	public void update(Stagiaire data) throws Exception;	
	public void delete(Stagiaire data) throws Exception;
	public Stagiaire selectById(int id) throws Exception;
	public List<Stagiaire> selectAll() throws Exception;
	
}

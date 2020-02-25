package fr.eni.evaluations;

import java.util.List;

import fr.eni.evaluations.bo.Stagiaire;
import fr.eni.evaluations.dal.StagiaireDAO;
import fr.eni.evaluations.dal.memory.StagiaireDAOImplMemory;

public class TestApp {

	public static void main(String[] args) {
		StagiaireDAO dao = new StagiaireDAOImplMemory();
		Stagiaire s1 = null;
		
		for(int i=1;i<=10;i++){
			s1 = new Stagiaire("p"+i, "n"+i, "e"+i, "mp"+i);
			try {
				dao.insert(s1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<Stagiaire> lesStagiaires=null;
		try {
			lesStagiaires = dao.selectAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Stagiaire s: lesStagiaires){
			System.out.println(s);
		}
		
		

	}

}

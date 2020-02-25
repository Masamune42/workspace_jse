package annuaire;

public class AnnuaireFactory {
	
	public static IAnnuaire getAnnuaireImpl(){
		return new AnnuaireListImpl();
	}

}

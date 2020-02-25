package fr.eni.papeterie.bll;

public enum TypesArticle {
	STYLO("Stylo"), RAMETTE("Ramette");
	
	private String type;
	
	TypesArticle(String type){
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
	
	
	
}

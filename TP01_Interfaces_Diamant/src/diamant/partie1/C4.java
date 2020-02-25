package diamant.partie1;

public class C4 extends C2 implements IC3{
	@Override
	public void methodeC3() {
		System.out.println("methode C3");
	}

	@Override
	public void methodeC2() {
		super.methodeC2();
	}
	
	public void methodeC4() {
		System.out.println("methode C4");
	}
	
}

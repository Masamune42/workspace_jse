package diamant.partie1;

public class Lanceur {

	// 1ere solution : il y a une classe mère
	// schema  : c3 implements l'interface ic3
	// puis c4 extends la classe mere c2 et implements l'interface ic3
	public static void main(String[] args) {
		C2 c2 = new C2();
		c2.methodeC2();
		C3 c3 = new C3();
		c3.methodeC3();
		C4 c4 = new C4();
		c4.methodeC2();
		c4.methodeC3();
		c4.methodeC4();

	}

}

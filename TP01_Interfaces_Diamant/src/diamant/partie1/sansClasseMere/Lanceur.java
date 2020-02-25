package diamant.partie1.sansClasseMere;

public class Lanceur {

	// 2eme solution : il n'y a aucune classe mère
	// schema  : c3 implements l'interface ic3
	// puis c2 implements l'interface ic2
	// ensuite ic2 extends ic3
	// et c4 implements l'interface ic2
	public static void main(String[] args) {
		C2 c2 = new C2();
		c2.methodeC2();
		c2.methodeC3();
		C3 c3 = new C3();
		c3.methodeC3();
		C4 c4 = new C4();
		c4.methodeC2();
		c4.methodeC3();
		c4.methodeC4();
	}

}

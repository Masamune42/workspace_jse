package observer;

public class ConcreteObserverB implements Observateur {

	@Override
	public void notifier() {
		System.out.println("ConcreteObserverB a reçu l'evenement."); 

	}

}

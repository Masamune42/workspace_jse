package observer;

public class ConcreteObserverA implements Observateur{

	@Override
	public void notifier() {
		System.out.println("ConcreteObserverA a reçu un evenement.");
		
	}
	
	

}

package fr.eni.demoLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DemoLog01 {

	/*
	 * Introduction : 
	 * 	- Créer un logger
	 *  - Créer des traces de niveaux diffÃ©rents
	 *  Remarque: par défaut un logger prend le niveau de log
	 *            du logger racine qui est DEBUG.
	 */
	public static void main(String[] args) {
		Logger loggerA =  LoggerFactory.getLogger("fr.eni.demoLog.DemoLog");

		loggerA.trace("Message 1 - loggerA - trace.");
		loggerA.debug("Message 2 - loggerA - debug.");
		loggerA.info("Message 3 - loggerA - info.");
		loggerA.warn("Message 4 - loggerA - warn.");
		loggerA.error("Message 5 - loggerA - error.");
		
	}

}

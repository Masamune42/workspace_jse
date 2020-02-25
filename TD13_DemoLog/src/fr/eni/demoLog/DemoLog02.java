package fr.eni.demoLog;

//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
//import ch.qos.logback.classic.LoggerContext;

public class DemoLog02 {

	/*
	 * Changer le niveau de log du logger
	 */
	public static void main(String[] args) {
		//Logger loggerA =  LoggerFactory.getLogger("fr.eni.demoLog.DemoLog");
		ch.qos.logback.classic.Logger loggerA = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("fr.eni.demoLog.DemoLog");
		loggerA.setLevel(Level.WARN); //Level.TRACE
		
		loggerA.trace("Message 1 - loggerA - trace.");
		loggerA.debug("Message 2 - loggerA - debug.");
		loggerA.info("Message 3 - loggerA - info.");
		loggerA.warn("Message 4 - loggerA - warn.");
		loggerA.error("Message 5 - loggerA - error.");
		
	}

}

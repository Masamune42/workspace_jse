package fr.eni.demoLog;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;

public class DemoLog03 {

	/*
	 * Hierarchie des loggers
	 */
	public static void main(String[] args) {
		//Logger loggerA =  LoggerFactory.getLogger("fr.eni.demoLog.DemoLog");
		ch.qos.logback.classic.Logger loggerA = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("fr.eni.demoLog.DemoLog");
		loggerA.setLevel(Level.ERROR); //Level.TRACE
		ch.qos.logback.classic.Logger loggerB = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("fr.eni");
		loggerB.setLevel(Level.TRACE); //Level.TRACE
		
		loggerA.trace("Message 1 - loggerA - trace.");
		loggerA.debug("Message 2 - loggerA - debug.");
		loggerA.info("Message 3 - loggerA - info.");
		loggerA.warn("Message 4 - loggerA - warn.");
		loggerA.error("Message 5 - loggerA - error.");

		loggerB.trace("Message 1 - loggerB - trace.");

	}

}

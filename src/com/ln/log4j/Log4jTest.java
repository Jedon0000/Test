package com.ln.log4j;

import org.apache.log4j.Logger;

public class Log4jTest {
	static Logger log = Logger.getLogger(Log4jTest.class);

	public static void main(String[] args) {

		// System.out.println("errrr");

		log.debug("this is an debug");
		log.info("this is an info");
	}
}

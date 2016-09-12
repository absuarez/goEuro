package com.goeurotest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.goeurotest.engine.CitySearchEngine;

/**
 * This is a very basic Runner, with very basic validation.
 * 
 * For parameter validation, it checks that one parameter has been sent through and only one.
 * Advance validation like the parameter is not a mvn property like "--blah=blah" is not covered.
 * 
 * @author ana
 *
 */
@Component
public class GoEuroTestRunner implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CitySearchEngine citySearchEngine;
	
	@Override
	public void run (String... cmdLineArguments ) throws Exception {		
		
		//some very basic validation
		if(cmdLineArguments==null || cmdLineArguments.length==0 || cmdLineArguments[0]==null || cmdLineArguments[0].equals("")){
			logger.info("Bad command. Example: java -jar goEuroTest-0.0.1.jar \"BERLIN\"");
			return;
		}
		logger.info("searching for city ... " + cmdLineArguments[0]);
		
		try {
			citySearchEngine.searchCity(cmdLineArguments[0]);
			logger.info("find generated CSV in this folder ");
			
		} catch (Exception e) {
			logger.info("some error has occured, see further logs for more information");
			throw e;
		}
	}
}

package com.goeurotest.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.goeurotest.engine.persistance.PersistanceService;
import com.goeurotest.engine.search.CitySearchService;

/**
 * Implementation of a search engine which:
 * 
 * - searches information for a city via a searchservice
 * - persists information obtained via csvgeneratorService
 * 
 * @author ana
 *
 */
@Component
public class CitySearchEngineImpl implements CitySearchEngine {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PersistanceService persistanceService;
	
	@Autowired
	private CitySearchService citySearchService;
	
	/**
	 * Searches for a city given the name of it.
	 * Persists the results via the persistance service.
	 * 
	 * @param cityName
	 */
	public void searchCity(String cityName) {
		
		try {
			persistanceService.persist(citySearchService.searchForCity(cityName));
			logger.info("a file should have been created");
			
		} catch (Exception e) {
			logger.error("an exception has ocurred ...", e);
			throw new RuntimeException(e);
		}
	}
}

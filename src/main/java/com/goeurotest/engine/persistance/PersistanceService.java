package com.goeurotest.engine.persistance;

import java.io.IOException;
import java.util.List;

import com.goeurotest.bo.City;

/**
 * 
 * @author ana
 */
public interface PersistanceService {
	
	/**
	 * generates a csv file with the list of cities passed as parameter
	 * 
	 * @param cities
	 * @return boolean indicating if the file has been generated or not
	 */
	boolean persist(List<City> cities) throws IOException;

}

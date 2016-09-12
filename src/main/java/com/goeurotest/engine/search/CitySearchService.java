package com.goeurotest.engine.search;

import java.util.List;

import com.goeurotest.bo.City;

/**
 * 
 * @author ana
 */
public interface CitySearchService {

	/**
	 * Finds all the registered cities for the given name.
	 * 
	 * @param cityName
	 * @return list of cities for the given name
	 */
	List<City> searchForCity(String cityName);
	
}

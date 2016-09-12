package com.goeurotest.engine;

/**
 * Interface to be implemented by any class which searches information for a city
 * and persists the results.
 * 
 * @author ana
 *
 */
public interface CitySearchEngine {

	public void searchCity(String cityName);
}

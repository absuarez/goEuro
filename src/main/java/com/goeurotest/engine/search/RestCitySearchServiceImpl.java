package com.goeurotest.engine.search;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.goeurotest.bo.City;

/**
 * RestTemplate implementation of the CitySearchService.
 * Calls a Rest API which returns a JSON response with the format:
 * 
 * [{"_id":380553,
 * "key":null,
 * "name":"London",
 * "fullName":"London, United Kingdom",
 * "iata_airport_code":null,
 * "type":"location",
 * "country":"United Kingdom",
 * "geo_position":{"latitude":51.50853,"longitude":-0.12574},
 * "locationId":12744,
 * "inEurope":true,
 * "countryId":75,
 * "countryCode":"GB",
 * "coreCountry":true,
 * "distance":null,
 * "names":{"it":"Londra","fi":"Lontoo","es":"Londres","tr":"Londra","pl":"Londyn","pt":"Londres","fr":"Londres","ru":"Лондон","zh":"伦敦","cs":"Londýn","ca":"Londres","nl":"Londen"},
 * "alternativeNames":{}}]
 * 
 * @author ana
 *
 */
@Service
public class RestCitySearchServiceImpl implements CitySearchService {

	private final static String BASE_URL = "https://api.goeuro.com/api/v2/position/suggest/en";
	
	@Override
	public List<City> searchForCity(String cityName) {
		
		final String url = BASE_URL + "/{cityName}";
		final City[] cities = new RestTemplate().getForObject(url, City[].class, cityName);
				
		return Arrays.asList(cities);
	}
}

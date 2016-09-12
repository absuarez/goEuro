package com.goeurotest.engine.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.goeurotest.bo.City;
import com.goeurotest.engine.search.CitySearchService;

/**
 * Integration test invoking the real Rest End point via the Rest Template Service.
 * 
 * We could mark this test as such, and only run it on the mvn Integration test phase. However since 
 * it's only a small test application, there are not so many tests and doesn't take so much time, hence 
 * it will left here to run as standard unit test.
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestCitySearchServiceImplITest {
	
	@Autowired
	private CitySearchService citySearchService;
	
	@Test
	public void shouldFindCityOfLondon(){
		
		List<City> cities = citySearchService.searchForCity("London");
		assertNotNull(cities);
		assertEquals(8,cities.size());
		assertEquals(380553L, cities.get(0).getId().longValue());
		assertEquals("London", cities.get(0).getName());
		assertEquals("location", cities.get(0).getType());
		assertNotNull("location", cities.get(0).getGeoPosition());
		assertEquals(51.50853, cities.get(0).getGeoPosition().getLatitude().doubleValue(),0);
		assertEquals(-0.12574, cities.get(0).getGeoPosition().getLongitude().doubleValue(),0);
	}
	
	@Test
	public void shouldNotFindCity(){
		
		List<City> cities = citySearchService.searchForCity("dummy");
		assertNotNull(cities);
		assertEquals(0,cities.size());	
	}
	
	@Test
	@Ignore
	//TODO: wire up URL as parameter and pass invalid End Point
	public void shouldNotFindRestEndPoint(){
		
		List<City> cities = citySearchService.searchForCity("London");
		assertNotNull(cities);
		assertEquals(8,cities.size());
	}
}

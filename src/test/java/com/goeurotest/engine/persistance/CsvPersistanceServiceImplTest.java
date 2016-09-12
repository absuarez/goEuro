package com.goeurotest.engine.persistance;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.goeurotest.bo.City;
import com.goeurotest.bo.GeoPosition;
import com.goeurotest.engine.persistance.PersistanceService;

/**
 * testing the different scenarios for the CSV generation
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvPersistanceServiceImplTest {
	
	@Autowired
	private PersistanceService persistanceService;
	
	@Test
	public void shouldGenerateAFileWithOnlyHeaderListNull() throws IOException{
		
		boolean result = persistanceService.persist(null);
		assertTrue(result);	
	}
	
	@Test
	public void shouldGenerateAFileWithOnlyHeaderListEmpty() throws IOException{
		
		boolean result = persistanceService.persist(Collections.emptyList());
		assertTrue(result);	
	}
	
	@Test
	public void shouldGenerateAFileWithOneCity() throws IOException{
		
		//List of cities
		List<City> cities = new ArrayList<City>();
		cities.add( new City(1l,"London","londonType",new GeoPosition(1d, 1d)) );
		
		boolean result = persistanceService.persist(cities);
		assertTrue(result);	
	}
	
	@Test
	public void shouldGenerateAFileSomeEmptyFields() throws IOException{
		
		List<City> cities = new ArrayList<City>();
		cities.add( new City(1l,null,null,new GeoPosition(1d, 1d)) );
		
		boolean result = persistanceService.persist(cities);
		assertTrue(result);	
	}
	
	@Test
	public void shouldGenerateAFileSomeEmptyFieldsInGeo() throws IOException{
		
		List<City> cities = new ArrayList<City>();
		cities.add( new City(1l,"London","londonType",new GeoPosition(null, 1d)) );
		
		boolean result = persistanceService.persist(cities);
		assertTrue(result);	
	}
	
	@Test
	public void shouldGenerateAFileAllEmptyFieldsInGeo() throws IOException{
		
		List<City> cities = new ArrayList<City>();
		cities.add( new City(1l,"London","londonType",null) );
		
		boolean result = persistanceService.persist(cities);
		assertTrue(result);	
	}
	
	@Test
	public void shouldGenerateAFileWithTwoCitiesAndOneWithAllEmptyFieldsInGeo() throws IOException{
		
		List<City> cities = new ArrayList<City>();
		cities.add( new City(1l,"London","londonType",null) );
		cities.add( new City(2l,"Berlin","location",new GeoPosition(2.78,3.45)) );
		
		boolean result = persistanceService.persist(cities);
		assertTrue(result);	
	}
}

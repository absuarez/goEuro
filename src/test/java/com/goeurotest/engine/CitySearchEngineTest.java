package com.goeurotest.engine;

import static org.mockito.Matchers.any;

import java.io.IOException;
import java.util.ArrayList;

import org.assertj.core.api.Fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.goeurotest.GoEuroTestApplication;
import com.goeurotest.bo.City;
import com.goeurotest.engine.persistance.PersistanceService;
import com.goeurotest.engine.search.CitySearchService;

import junit.framework.Assert;


/**
 * In this test class I#m checking the behavior of the engine, specially on the different exception
 * scenarios.
 * 
 * And a basic test for a non-exception scenario.
 * 
 * @author ana
 *
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CitySearchEngineTestConfiguration.class, GoEuroTestApplication.class })
public class CitySearchEngineTest {

	@Autowired
    private PersistanceService persistanceService;
    @Autowired
    private CitySearchService citySearchService;
    @Autowired
    private CitySearchEngine citySearchEngine;
    
    @Test(expected=RuntimeException.class)
    public void shouldGenerateExceptionFromRestTemplateException() {
    	
    	Mockito.when( citySearchService.searchForCity(any()) ).thenThrow(new RestClientException("intended exception"));
        citySearchEngine.searchCity("someCity");   
        Fail.shouldHaveThrown(RuntimeException.class);
    }
    
    @Test(expected=RuntimeException.class)
    public void shouldGenerateExceptionFromJacksonException() {
    	
    	Mockito.when( citySearchService.searchForCity(any()) ).thenThrow(new JsonMappingException("intended exception"));
        citySearchEngine.searchCity("someCity");   
        Fail.shouldHaveThrown(RuntimeException.class);
    }
    
    @Test(expected=RuntimeException.class)
    public void shouldGenerateExceptionFromCsvException() throws IOException {
    	
    	Mockito.when( persistanceService.persist(any()) ).thenThrow(new IOException("intended exception"));
    	Mockito.when( citySearchService.searchForCity(any()) ).thenReturn( new ArrayList<City>() );
        citySearchEngine.searchCity("someCity");   
        Fail.shouldHaveThrown(RuntimeException.class);
    }

}
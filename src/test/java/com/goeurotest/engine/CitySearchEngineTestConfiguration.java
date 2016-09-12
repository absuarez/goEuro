package com.goeurotest.engine;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.goeurotest.engine.persistance.PersistanceService;
import com.goeurotest.engine.search.CitySearchService;

/**
 * In this configuration class I declare Mock objects to be used only in unit test
 * of the CitySearchEngine
 * 
 * @author ana
 *
 */
@Profile("test")
@Configuration
public class CitySearchEngineTestConfiguration {

	@Bean
    @Primary
    public PersistanceService persistanceService() {
        return Mockito.mock(PersistanceService.class);
    }
	
	@Bean
    @Primary
    public CitySearchService citySearchService() {
        return Mockito.mock(CitySearchService.class);
    }
}
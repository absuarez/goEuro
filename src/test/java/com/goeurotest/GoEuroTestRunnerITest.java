package com.goeurotest;

import org.assertj.core.api.Fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Integration Test for the runner.
 * We could mark this test as such and assign it only to be run to the mvn integration-test phase.
 * However since the application is so small and it does not take so long to do the integrataion, it's ok to leave it to 
 * run as normal unit test eventhough it uses real instances integrated with end points.
 * 
 * @author ana
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoEuroTestRunnerITest {

	@Autowired
	private GoEuroTestRunner goEuroTestRunner;
	
	@Test
	public void shouldNotThrowExceptionWithNullParameters() {
		
		try {
			goEuroTestRunner.run(null);
		} catch (Exception e) {
			Fail.fail("should not throw exception due to empty arguments send over");
		}
	}
	
	@Test
	public void shouldFindCityInfoAndGenerateFile() {
		
		try {
			goEuroTestRunner.run("BERLIN");
		} catch (Exception e) {
			Fail.fail("should not throw exception due to empty arguments send over");
		}
	}
	
	@Test
	public void shouldNotFindCityInfoAndGenerateEmptyFile() {
		
		try {
			goEuroTestRunner.run("DUMMY");
		} catch (Exception e) {
			Fail.fail("should not throw exception due to empty arguments send over");
		}
	}
}

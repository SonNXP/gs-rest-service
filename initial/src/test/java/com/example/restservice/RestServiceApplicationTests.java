package com.example.restservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @SpringBootTest: tells Spring Boot to look for a main configuration class
 *                  (one with @SpringBootApplication, for instance) and use that
 *                  to start a Spring application context.
 */
@SpringBootTest
public class RestServiceApplicationTests {

	@Autowired
	private GreetingController greetingController;

	/**
	 * A nice feature of the Spring Test support is that the application context is
	 * cached between tests. That way, if you have multiple methods in a test case
	 * or multiple test cases with the same configuration, they incur the cost of
	 * starting the application only once. You can control the cache by using
	 * the @DirtiesContext annotation.
	 */
	@Test
	public void contextLoads() {
		assertThat(greetingController).isNotNull();
	}

}

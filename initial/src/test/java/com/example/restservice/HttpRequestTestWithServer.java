package com.example.restservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * ! use of webEnvironment=RANDOM_PORT to START THE SERVER with a random port
 * (useful to avoid conflicts in test environments)
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTestWithServer {

    /** injection of the local port */
    @LocalServerPort
    private int port;

    /**
     * Spring Boot has automatically provided a TestRestTemplate for you. All you
     * have to do is add @Autowired to it. (suitable for integration tests.)
     */
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/" + "greeting", String.class))
                .contains("Hello, World");
    }

    @Test
    public void greetingShouldReturnNameMessage() throws Exception {
        String name = "MiuMiu";
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/" + "greeting?" + "name=" + name, String.class))
                .contains("Hello, " + name);
    }
}

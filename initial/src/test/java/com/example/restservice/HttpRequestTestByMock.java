package com.example.restservice;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * ! Another useful approach is to NOT start the server at all but to test only
 * the layer below that, where Spring handles the incoming HTTP request and
 * hands it off to your controller. That way, almost of the full stack is used,
 * and your code will be called in exactly the same way as if it were processing
 * a real HTTP request but without the cost of starting the server. (should for
 * Unit Test). Detail: https://spring.io/guides/gs/testing-web/
 */
@SpringBootTest
@AutoConfigureMockMvc
public class HttpRequestTestByMock {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GreetingService greetingService;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/" + "greeting")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
    }

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        /**
         * Enables "when" stubbing methods. Use it when you want the mock to return
         * particular value when particular method is called.
         */
        when(greetingService.greet()).thenReturn("Hello, this is a service!");
        /**
         * because get("/service") when be called will run greetingService.greet() so
         * need to run greetingService.greet() first
         */
        this.mockMvc.perform(get("/service")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, this is a service!")));
    }
}
/**
 * n this test, the full Spring application context is started but without the
 * server. We can narrow the tests to only the web layer by using @WebMvcTest
 * then Spring Boot instantiates only the web layer rather than the whole
 * context. In an application with multiple controllers, you can even ask for
 * only one to be instantiated by using, for
 * example, @WebMvcTest(HomeController.class).
 */

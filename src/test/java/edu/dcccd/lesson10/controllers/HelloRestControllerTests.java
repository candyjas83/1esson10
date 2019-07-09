package edu.dcccd.lesson10.controllers;

import edu.dcccd.lesson10.entities.Greeting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class HelloRestControllerTests {
    @Autowired
    private TestRestTemplate template;

    @Test
    public void greetWithoutName(){
        ResponseEntity<Greeting> entity = template.getForEntity("/rest", Greeting.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, entity.getHeaders().getContentType());
        Greeting response = entity.getBody();
        assertEquals("Hello, World!", response.getGreeting());
    }

    @Test
    public void greetWithName(){
        Greeting response = template.getForObject("/rest?name=Spring Boot", Greeting.class);
        assertEquals("Hello, Spring Boot!", response.getGreeting());

    }
}

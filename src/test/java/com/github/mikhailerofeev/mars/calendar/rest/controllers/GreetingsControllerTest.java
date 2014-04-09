package com.github.mikhailerofeev.mars.calendar.rest.controllers;

import com.github.mikhailerofeev.mars.calendar.rest.dto.Greeting;
import com.github.mikhailerofeev.mars.calendar.server.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 09.04.14
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@DirtiesContext
public class GreetingsControllerTest {

  @Autowired
  private GreetingsController greetingsController;

  @Test
  public void testInject() throws IOException, URISyntaxException {
    assertNotNull(greetingsController);
    assertEquals("Hello, null!", greetingsController.greeting(mock(Principal.class)).getText());
  }

  @Test
  public void testHttpGet() throws IOException, URISyntaxException {
    RestTemplate restTemplate = new TestRestTemplate();
    int port = 8080;
    final URI url = new URI("http://localhost:" + port + "/rest/v1/greeting");
    final ResponseEntity<Greeting> entity = restTemplate.getForEntity(url, Greeting.class);
    assertEquals(HttpStatus.OK, entity.getStatusCode());
    assertEquals("Hello, Anonymous!", entity.getBody().getText());
  }

  @Test
  public void testHttpDelete() throws URISyntaxException {
    RestTemplate restTemplate = new TestRestTemplate();
    int port = 8080;
    final URI url = new URI("http://localhost:" + port + "/rest/v1/greeting");
    final ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, Greeting.class, String.class);
    assertEquals(HttpStatus.FORBIDDEN, stringResponseEntity.getStatusCode());

  }
}

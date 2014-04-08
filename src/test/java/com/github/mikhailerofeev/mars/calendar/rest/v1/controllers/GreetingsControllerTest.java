package com.github.mikhailerofeev.mars.calendar.rest.v1.controllers;

import com.github.mikhailerofeev.mars.calendar.server.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertNotNull;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 09.04.14
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest
//@ActiveProfiles("test")
public class GreetingsControllerTest {

  @Autowired
  private GreetingsController greetingsController;

  @Autowired
  private ConfigurableApplicationContext context;


  @Test
  public void testHttp() throws IOException, URISyntaxException {
    assertNotNull(greetingsController);
    assertNotNull(context);
    
    RestTemplate restTemplate = new TestRestTemplate();
    int port = 8080;
    final URI url = new URI("http://localhost:" + port + "/rest/v1/greeting");
    final ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
    System.out.println(entity.getBody());
//    assertEquals("Hello, worlds!", entity.getBody().getText());
  }
}

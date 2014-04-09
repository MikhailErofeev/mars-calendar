package com.github.mikhailerofeev.mars.calendar.rest.controllers;

import com.github.mikhailerofeev.mars.calendar.server.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 09.04.14
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class GreetingsControllerTest {

  @Autowired
  private GreetingsController greetingsController;

  @Test
  public void testInject() throws IOException, URISyntaxException {
    assertNotNull(greetingsController);
    assertEquals("Hello, worlds!", greetingsController.greeting().getText());
  }
}

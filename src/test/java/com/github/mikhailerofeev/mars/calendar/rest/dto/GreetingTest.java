package com.github.mikhailerofeev.mars.calendar.rest.dto;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 13.04.14
 */
@RunWith(JUnit4.class)
public class GreetingTest {


  ObjectMapper mapper = new ObjectMapper();


  @Test
  public void testSerialization() throws IOException {
    final Greeting src = new Greeting("hello");
    final String helloStr = mapper.writeValueAsString(src);
    assertEquals("{\"text\":\"hello\"}", helloStr);
    final Greeting dst = mapper.readValue(helloStr, Greeting.class);
    assertEquals(src, dst);
  }
}

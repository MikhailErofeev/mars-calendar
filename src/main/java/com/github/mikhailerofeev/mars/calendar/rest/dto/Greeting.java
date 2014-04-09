package com.github.mikhailerofeev.mars.calendar.rest.dto;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 09.04.14
 */
@Deprecated //for test purposes
public class Greeting {
  private final String text;

  public Greeting(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}

package com.github.mikhailerofeev.mars.calendar.rest.v1.dto;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 09.04.14
 */
public class Greeting {
  private final String text;

  public Greeting(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}

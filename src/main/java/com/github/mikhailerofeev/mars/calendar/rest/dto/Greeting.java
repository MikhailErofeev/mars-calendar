package com.github.mikhailerofeev.mars.calendar.rest.dto;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 09.04.14
 */
@Deprecated //for testing purposes
public class Greeting {
  private String text;

  public Greeting(String text) {
    this.text = text;
  }

  public Greeting() {
  }


  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}

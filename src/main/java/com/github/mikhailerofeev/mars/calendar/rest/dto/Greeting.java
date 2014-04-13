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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Greeting)) return false;

    Greeting greeting = (Greeting) o;

    if (text != null ? !text.equals(greeting.text) : greeting.text != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return text != null ? text.hashCode() : 0;
  }
}

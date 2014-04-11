package com.github.mikhailerofeev.mars.calendar.model.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 10.04.14
 */
@Entity
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String name;

  protected User() {

  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public User(String name) {
    this.name = name;
  }
}

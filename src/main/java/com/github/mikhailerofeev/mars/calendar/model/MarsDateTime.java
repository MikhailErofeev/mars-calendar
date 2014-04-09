package com.github.mikhailerofeev.mars.calendar.model;

import org.joda.time.DateTime;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 09.04.14
 */
@SuppressWarnings("UnusedDeclaration")
public class MarsDateTime {

  private final long unixTimeStamp;

  public MarsDateTime(final long unixTimeStamp) {
    this.unixTimeStamp = unixTimeStamp;
  }

  /**
   * now
   */
  public MarsDateTime() {
    unixTimeStamp = DateTime.now().getMillis();
  }

  public int getYear() {
    throw new NotImplementedException();
  }

  public int getMonthOfYear() {
    throw new NotImplementedException();
  }

}

package com.github.mikhailerofeev.mars.calendar.model.values.time;

import org.joda.time.Duration;

/**
 * Created by Максим on 13.04.2014.
 */
public class SolDurationFactory {
    public static Duration getSolDuration(String planet) {
        if(planet.toLowerCase().equals("mars"))
            return new Duration(88642663);
        else
            throw new IllegalArgumentException("No such planet.");
    }
}

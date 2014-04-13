package com.github.mikhailerofeev.mars.calendar.model.values.time;

import org.joda.time.Duration;

/**
 * Created by Максим on 13.04.2014.
 */
public class SolDurationFactory {
    public static Duration getSolDuration(String planet) throws Exception {
        if(planet == "mars")
            return new Duration(88642663);
        else
            throw new Exception("No such planet.");
    }
}

package com.github.mikhailerofeev.mars.calendar.model.values;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.Duration;
import org.joda.time.

/**
 * Created by Anton on 11.04.2014.
 */
public class PlanetDateTime {
    private DateTime timePoint;
    private DateTime epoch;
    private
    public PlanetDateTime() {
        this.timePoint = DateTime.now();
    }
    public PlanetDateTime(DateTime timePoint) {
        this.timePoint = timePoint;
    }
    public PlanetDateTime(DateTime timePoint, DateTime epoch) {
        this.timePoint = timePoint;
        this.epoch = epoch;
    }
    public PlanetDateTime(long unixTimeStamp) {
        this.timePoint = new DateTime(unixTimeStamp);
    }
    public PlanetDateTime(long unixTimeStamp, long epoch) {
        this.timePoint = new DateTime(unixTimeStamp);
        this.epoch = new DateTime(epoch);
    }
    public Duration timeSinceEpoch() {
        return new Duration(epoch, timePoint);
    }
    public DateTime timeTerrestial() {
        return timePoint;
    }



}

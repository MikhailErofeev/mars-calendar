package com.github.mikhailerofeev.mars.calendar.model.values;

import org.joda.time.*;

/**
 * Created by Anton on 11.04.2014.
 *
 */
public class PlanetDateTime {
    private DateTime timePoint;
    private DateTime epoch;
    private PlanetCalendar calendar;
    private Duration solDuration;
    //private Duration yearDuration;

    public PlanetDateTime() {
        this.timePoint = DateTime.now();
    }

    public PlanetDateTime(DateTime timePoint) {
        this.timePoint = timePoint;
        this.epoch = new DateTime(0);
    }

    public PlanetDateTime(DateTime timePoint, DateTime epoch) {
        this.timePoint = timePoint;
        this.epoch = epoch;
    }

    public PlanetDateTime(long unixTimeStamp) {
        this.timePoint = new DateTime(unixTimeStamp);
        this.epoch = new DateTime(0);
    }

    public PlanetDateTime(long unixTimeStamp, long epoch) {
        this.timePoint = new DateTime(unixTimeStamp);
        this.epoch = new DateTime(epoch);
    }

    public Duration timeSinceEpoch() {
        return new Duration(epoch, timePoint);
    }

    public DateTime toTerrestrial() {
        return timePoint;
    }

    /**
     * Determines the (terrestrial) duration of the specific year
     * @param year
     * @return
     */
    public Duration yearDuration(int year) {
        return new Duration(solDuration.getMillis() * calendar.solsInYear(year));
    }

    public Duration monthDuration(int year, int month) {
        return new Duration(solDuration.getMillis() * calendar.solsInMonth(year, month));
    }

    public int getYear() {
        // todo: optimization
        int year = 0;
        MutableDateTime timePoint = new MutableDateTime(this.timePoint);
        while (timePoint.isBefore(this.timePoint)) {
            ++year;
            timePoint.add(yearDuration(year));
        }
        return year;
    }

    public int getMonthNum() {
        // todo: optimization
        MutableDateTime timePoint = new MutableDateTime(this.timePoint);
        int year = getYear();
        timePoint.add(yearDuration(year - 1));
        int month = 0;
        while (timePoint.isBefore(this.timePoint)) {
            timePoint.add(monthDuration(year, month));
            ++month;
        }
        return month;
    }

    public PlanetMonth getMonth() {
        return calendar.getMonths().get(getMonthNum() - 1);
    }

    public int getDay() {
        // todo: optimization
        MutableDateTime timePoint = new MutableDateTime(this.timePoint);
        int year = getYear();
        timePoint.add(yearDuration(year - 1));
        int month = 0;
        while (timePoint.isBefore(this.timePoint)) {
            timePoint.add(monthDuration(year, month));
        }
        return month;
    }
}

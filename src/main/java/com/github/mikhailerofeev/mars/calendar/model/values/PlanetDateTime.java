package com.github.mikhailerofeev.mars.calendar.model.values;

import org.joda.time.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Anton on 11.04.2014.
 *
 */
public class PlanetDateTime {
    private DateTime timePoint;
    private DateTime epoch;
    private PlanetCalendar calendar;
    private Duration solDuration;

    private Integer year = null;
    private Integer monthNum = null; // from 1 ...
    private Integer day = null; // from 1 ...
    private Integer hour = null; // from 1 ...
    private Integer minute = null; // from 1 ...
    private Integer second = null; // from 1 ...

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

    public PlanetDateTime(DateTime timePoint, DateTime epoch, PlanetCalendar calendar, Duration solDuration) {
        this.timePoint = timePoint;
        this.epoch = epoch;
        this.calendar = calendar;
        this.solDuration = solDuration;
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

    /**
     * returns the year in the natural form
     * @return
     */
    public int getYear() {
        if (year == null) {
            // calculate
            throw new NotImplementedException();
        } else {
            return year;
        }
//        // todo: optimization
//        int year = 0;
//        MutableDateTime timePoint = new MutableDateTime(this.epoch);
//        while (timePoint.isBefore(this.timePoint)) {
//            ++year;
//            timePoint.add(yearDuration(year));
//        }
//        return year;
    }

    /**
     * returns the the month number starting from 1
     * @return
     */
    public int getMonthNum() {
        if (monthNum == null) {
            // calculate
            throw new NotImplementedException();
        } else {
            return monthNum;
        }
//        // todo: optimization
//        MutableDateTime timePoint = new MutableDateTime(this.epoch);
//        int year = getYear();
//        timePoint.add(yearDuration(year - 1));
//        int month = 0;
//        do {
//            timePoint.add(monthDuration(year, month));
//            ++month;
//        } while (timePoint.isBefore(this.timePoint));
//        return month;
    }

    public PlanetMonth getMonth() {
        return calendar.getMonths().get(getMonthNum() - 1);
    }

    public int getDay() {
        if (day == null) {
            // calculate
            throw new NotImplementedException();
        } else {
            return day;
        }
//        // todo: optimization
//        MutableDateTime timePoint = new MutableDateTime(this.epoch);
//        int year = getYear() - 1;
//        timePoint.add(yearDuration(year));
//        timePoint.add(monthDuration(year, getMonthNum()));
//        int day = 0;
//        while (timePoint.isBefore(this.timePoint)) {
//            ++day;
//            timePoint.add(solDuration);
//        }
//        return day;
    }

    public int getHour() {
        if (hour == null) {
            // calculate
            throw new NotImplementedException();
        } else {
            return hour;
        }

    }
}

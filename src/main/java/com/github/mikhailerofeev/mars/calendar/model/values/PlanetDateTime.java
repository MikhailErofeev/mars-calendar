package com.github.mikhailerofeev.mars.calendar.model.values;

import org.joda.time.*;

import java.util.Map;

/**
 * Created by Anton on 11.04.2014.
 *
 */
public class PlanetDateTime {
    private DateTime timePoint;
    private DateTime epoch;
    private PlanetCalendar calendar;
    private Duration solDuration;



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

    //public double planetEarthCoeff;

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
        // todo: optimization
        int year = 0;
        MutableDateTime timeStartPoint = new MutableDateTime(this.epoch);
        while (timeStartPoint.isBefore(this.timePoint)) {
            ++year;
            timeStartPoint.add(yearDuration(year));
        }
        return year;
    }

    /**
     * returns the the month number starting from 1
     * @return
     */
    public int getMonthNum() {
        // todo: optimization
        MutableDateTime timeStartPoint = new MutableDateTime(this.epoch);
        int year = getYear();
        timeStartPoint.add(yearDuration(year - 1));
        int month = 0;
        do {
            timeStartPoint.add(monthDuration(year, month));
            ++month;
        } while (timeStartPoint.isBefore(this.timePoint));
        return month;
    }

    public PlanetMonth getMonth() {
        return calendar.getMonths().get(getMonthNum() - 1);
    }

    public int getDay() {
        // todo: optimization
        MutableDateTime timeStartPoint = new MutableDateTime(this.epoch);
        int year = getYear() - 1;
        timeStartPoint.add(yearDuration(year));
        timeStartPoint.add(monthDuration(year, getMonthNum()));
        int day = 0;
        while (timeStartPoint.isBefore(this.timePoint)) {
            ++day;
            timeStartPoint.add(solDuration);
        }
        return day;
    }

    public int getHour() {
        // todo: optimization
        MutableDateTime timeStartPoint = new MutableDateTime(this.epoch);
        int year = getYear() - 1;
        timeStartPoint.add(yearDuration(year));
        timeStartPoint.add(monthDuration(year, getMonthNum()));
        timeStartPoint.add(solDuration.getMillis()*getDay());

        int hour = 0;
        while(timeStartPoint.isBefore(this.timePoint)){
            ++hour;
            timeStartPoint.add(60*60*1000);
        }
        return  hour;
    }

    public int getMinute() {
        // todo: optimization
        MutableDateTime timeStartPoint = new MutableDateTime(this.epoch);
        int year = getYear() - 1;
        timeStartPoint.add(yearDuration(year));
        timeStartPoint.add(monthDuration(year, getMonthNum()));
        timeStartPoint.add(solDuration.getMillis()*getDay());
        timeStartPoint.add(60*60*1000*getHour());

        int minute = 0;
        while(timeStartPoint.isBefore(this.timePoint)){
            ++minute;
            timeStartPoint.add(60*1000);
        }
        return minute;
    }


}

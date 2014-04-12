package com.github.mikhailerofeev.mars.calendar.model.values;

import org.joda.time.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Date;

/**
 * Created by Anton on 11.04.2014.
 *
 */
public class PlanetDateTime {
    private final DateTime timePoint;
    private final DateTime epoch;
    private PlanetCalendar calendar = null;
    private Duration solDuration;

    private Integer year        = null;
    private Integer weekNum     = null;
    private Integer monthNum    = null; // from 1 ...
    private Integer day         = null; // from 1 ...
    private Integer hour        = null; // from 1 ...
    @SuppressWarnings("UnusedDeclaration")
    private Integer minute      = null; // from 1 ...
    @SuppressWarnings("UnusedDeclaration")
    private Integer second      = null; // from 1 ...

    @SuppressWarnings("UnusedDeclaration")
    public PlanetDateTime() {
        this.timePoint  = DateTime.now();
        this.epoch      = new DateTime(0);
    }

    @SuppressWarnings("UnusedDeclaration")
    public PlanetDateTime(DateTime timePoint) {
        this.timePoint = timePoint;
        this.epoch = new DateTime(0);
    }

    @SuppressWarnings("UnusedDeclaration")
    public PlanetDateTime(DateTime timePoint, DateTime epoch) {
        this.timePoint = timePoint;
        this.epoch = epoch;
    }

    @SuppressWarnings("UnusedDeclaration")
    public PlanetDateTime(long unixTimeStamp) {
        this.timePoint = new DateTime(unixTimeStamp);
        this.epoch = new DateTime(0);
    }

    @SuppressWarnings("UnusedDeclaration")
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

    @SuppressWarnings("UnusedDeclaration")
    public Duration timeSinceEpoch() {
        return new Duration(epoch, timePoint);
    }

    private double solsSinceEpoch() {
        return timeSinceEpoch().getMillis() / solDuration.getMillis();
    }

    public long wholeSolsSinceEpoch() {
        return (long)solsSinceEpoch();
    }

    public Duration durationSinceSolStart() {
        return new Duration(timeSinceEpoch().getMillis() % solDuration.getMillis());
    }

    @SuppressWarnings("UnusedDeclaration")
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
            // todo: optimization
//            year = 0;
//            MutableDateTime timePoint = new MutableDateTime(this.epoch);
//            while (timePoint.isBefore(this.timePoint)) {
//                ++year;
//                timePoint.add(yearDuration(year));
//            }
            int solsSincePeriod = (int)(wholeSolsSinceEpoch() % (long)calendar.solsInLeapPeriod());
            int periodsSinceEpoch = (int)(wholeSolsSinceEpoch() / (long)calendar.solsInLeapPeriod());
            year = periodsSinceEpoch * calendar.getLeapPeriod().size();
            int solsElapsed = 0;
            // may need to be changed to 1 (instead of 0)
            while (solsElapsed < solsSincePeriod) {
                solsElapsed += calendar.solsInYear(0);
                ++year;
            }
            --year; // because we return the nearest PREVIOUS year, not the NEXT one (!!!)
        }
        return year;
    }

    /**
     * returns the the month number starting from 1
     * @return
     */
    public int getMonthNum() {
        if (monthNum == null) {
            // todo: optimization
            MutableDateTime timePoint = new MutableDateTime(this.epoch);
            year = getYear();
            timePoint.add(yearDuration(year));
            monthNum = 0;
            while (timePoint.isBefore(this.timePoint)) {
                timePoint.add(monthDuration(year, monthNum));
                ++monthNum;
            }
        }
        return monthNum;
    }

    @SuppressWarnings("UnusedDeclaration")
    public PlanetMonth getMonth() {
        return calendar.getMonths().get(getMonthNum() - 1);
    }

    public int getDay() {
        if (day == null) {
            // todo: optimization
            MutableDateTime timePoint = new MutableDateTime(this.epoch);
            year = getYear();
            monthNum = getMonthNum();
            timePoint.add(yearDuration(year));
            timePoint.add(monthDuration(year, monthNum));
            day = 1;
            while (timePoint.isBefore(this.timePoint)) {
                ++day;
                timePoint.add(solDuration);
            }
        }
        return day;

    }

    @SuppressWarnings("UnusedDeclaration")
    public long getHour() {
        if (hour == null) {
            return durationSinceSolStart().getStandardHours();
        }
        else {
            return hour;
        }
    }

    public long getMinute() {
        if (minute == null)
            return (durationSinceSolStart().getStandardMinutes() - durationSinceSolStart().getStandardHours() * 60);
        else {
            return minute;
        }
    }

    public long getSecond() {
        if (second == null)
            return (durationSinceSolStart().getStandardSeconds() - durationSinceSolStart().getStandardHours() * 60 * 60);
        else {
            return second;
        }
    }

    public int getWeekNum(){
        throw new NotImplementedException();
    }
}

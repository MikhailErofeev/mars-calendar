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

    private void calcTime() {
        int solsSincePeriod = (int)(wholeSolsSinceEpoch() % (long)calendar.solsInLeapPeriod());
        int periodsSinceEpoch = (int)(wholeSolsSinceEpoch() / (long)calendar.solsInLeapPeriod());
        year = periodsSinceEpoch * calendar.getLeapPeriod().size();
        int solsElapsed = 0;
        // may need to be changed to 1 (instead of 0)
        while (solsElapsed <= solsSincePeriod) {
            solsElapsed += calendar.solsInYear(year);
            ++year;
        }
        solsElapsed -= calendar.solsInYear(year);
        --year; // because we return the nearest PREVIOUS year, not the NEXT one (!!!)
        // -- by now, the year is supposed to be calculated -- //
        monthNum = 0;

    }

    /**
     * returns the year in the natural form
     * @return
     */
    public int getYear() {
        if (year == null) calcTime();
        return year;
    }

    /**
     * returns the the month number starting from 1
     * @return
     */
    public int getMonthNum() {
        if (monthNum == null) calcTime();
        return monthNum;
    }

    @SuppressWarnings("UnusedDeclaration")
    public PlanetMonth getMonth() {
        return calendar.getMonths().get(getMonthNum() - 1);
    }

    public int getDay() {
        if (day == null) calcTime();
        return day;
    }

    @SuppressWarnings("UnusedDeclaration")
    public long getHour() {
        if (hour == null) calcTime();
        return hour;
    }

    public long getMinute() {
       if (minute == null) calcTime();
       return minute;
    }

    public long getSecond() {
        if (second == null) calcTime();
        return second;
    }

    public int getWeek(){
        throw new NotImplementedException();
    }
}

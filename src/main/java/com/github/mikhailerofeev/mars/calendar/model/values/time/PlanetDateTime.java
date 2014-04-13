package com.github.mikhailerofeev.mars.calendar.model.values.time;

import org.joda.time.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

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
    private Integer monthNum    = null; // from 1 ...
    private Integer sol         = null; // from 1 ...
    private Integer hour        = null; // from 1 ...
    private Integer minute      = null; // from 1 ...
    private Integer second      = null; // from 1 ...
    private Integer week        = null; // from 1 ...

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

    private double solsSinceEpoch() {
        return timeSinceEpoch().getMillis() / solDuration.getMillis();
    }

    public long wholeSolsSinceEpoch() {
        return (long)solsSinceEpoch();
    }

    public Duration durationSinceSolStart() {
        return new Duration(timeSinceEpoch().getMillis() % solDuration.getMillis());
    }

    public DateTime toTerrestrial() {
        return timePoint;
    }

    public DateTime getTimePoint() {
        return timePoint;
    }

    public DateTime getEpoch() {
        return epoch;
    }

    public PlanetCalendar getCalendar() {
        return calendar;
    }

    public Duration getSolDuration() {
        return solDuration;
    }

    /**
     *
     * @param year
     * @return terrestrial duration of the specific year
     */
    public Duration yearDuration(int year) {
        return new Duration(solDuration.getMillis() * calendar.solsInYear(year));
    }

    public Duration monthDuration(int year, int month) {
        return new Duration(solDuration.getMillis() * calendar.solsInMonth(year, month));
    }

    private void calcTime() {
        long wholeSolsSinceEpoch = wholeSolsSinceEpoch();
        long solsInLeapPeriod = calendar.solsInLeapPeriod();
        int periodsSinceEpoch = (int)(wholeSolsSinceEpoch / solsInLeapPeriod);
        year = periodsSinceEpoch * calendar.getLeapPeriod().size();
        long solsElapsedUntilCurrentCalc = periodsSinceEpoch * calendar.solsInLeapPeriod();
        // may need to be changed to 1 (instead of 0)
        while (solsElapsedUntilCurrentCalc <= wholeSolsSinceEpoch) {
            solsElapsedUntilCurrentCalc += calendar.solsInYear(year);
            ++year;
        }
        solsElapsedUntilCurrentCalc -= calendar.solsInYear(year);
        //--year; // because we return the nearest PREVIOUS year, not the NEXT one (!!!)
        // -- by now, the year is supposed to be calculated -- //
        monthNum = 0;
        while (solsElapsedUntilCurrentCalc <= wholeSolsSinceEpoch) {
            // we use the month starting from zero here as it accesses the list
            solsElapsedUntilCurrentCalc += calendar.solsInMonth(year, monthNum);
            ++monthNum;
        }
        solsElapsedUntilCurrentCalc -= calendar.solsInMonth(year, monthNum);
        // no need to decrement sol because it should start from 0 (!!!)
        // -- by now, we have month -- //
        sol = 0;
        while (solsElapsedUntilCurrentCalc <= wholeSolsSinceEpoch) {
            ++sol;
            ++solsElapsedUntilCurrentCalc;
        }
        // no need to decrement sol because it should start from 1 (!!!)
        --solsElapsedUntilCurrentCalc;
        // sol calculated
        long hoursElapsedUntilCurrentCalc = solsElapsedUntilCurrentCalc * solDuration.getStandardHours();
        long totalHoursElapsed = timeSinceEpoch().getStandardHours();
        hour = 0;
        while (hoursElapsedUntilCurrentCalc <= totalHoursElapsed) {
            ++hoursElapsedUntilCurrentCalc;
            ++hour;
        }
        --hoursElapsedUntilCurrentCalc;
        --hour;
        // hour calculated
        long minutesElapsedUntilCurrentCalc = hoursElapsedUntilCurrentCalc * 60;
        long totalMinutesElapsed = timeSinceEpoch().getStandardMinutes();
        minute = 0;
        while (minutesElapsedUntilCurrentCalc <= totalMinutesElapsed) {
            ++minutesElapsedUntilCurrentCalc;
            ++minute;
        }
        --minutesElapsedUntilCurrentCalc;
        --minute;
        // minute calculated
        long secondsElapsedUntilCurrentCalc = minutesElapsedUntilCurrentCalc * 60;
        long totalSecondsElapsed = timeSinceEpoch().getStandardSeconds();
        second = 0;
        while (secondsElapsedUntilCurrentCalc <= totalSecondsElapsed) {
            ++secondsElapsedUntilCurrentCalc;
            ++second;
        }
        --secondsElapsedUntilCurrentCalc;
        --second;
        // second calculated
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
     *
     * @return month number starting from 1
     */
    public int getMonthNum() {
        if (monthNum == null) calcTime();
        return monthNum;
    }

    /**
     *
     * @return month object (if you need month number use getMonthNum instead)
     */
    public PlanetMonth getMonth() {
        return calendar.getMonths().get(getMonthNum() - 1);
    }

    /**
     *
     * @return sol number starting with 1
     */
    public int getSol() {
        if (sol == null) calcTime();
        return sol;
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

    /**
     *
     * @return week number starting with 1
     */
    public int getWeek(){
        if (week == null) {
            if (calendar.weekRestarts()) {
                // we use sol minus one because getSol returns the number of the sol for normal humans
                // (i.e. starting from 1)
                week = (getSol() - 1) / calendar.getWeekSols().size() + 1;
            } else {
                week = (int)(wholeSolsSinceEpoch() / calendar.getWeekSols().size() + 1);
            }
        }
        return week;
    }
}

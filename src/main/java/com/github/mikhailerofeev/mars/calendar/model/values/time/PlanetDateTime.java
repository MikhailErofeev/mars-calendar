package com.github.mikhailerofeev.mars.calendar.model.values.time;

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

    private Integer year        = null;
    private Integer monthOfYear = null; // from 1 ...
    private Integer solOfMonth = null; // from 1 ...
    private Integer hourOfSol = null; // from 1 ...
    private Integer minuteOfHour = null; // from 1 ...
    private Integer secondOfMinute = null; // from 1 ...
    private Integer week        = null; // from 1 ...


    public PlanetDateTime(DateTime timePoint, DateTime epoch, PlanetCalendar calendar, Duration solDuration) {
        this.timePoint = timePoint;
        this.epoch = epoch;
        this.calendar = calendar;
        this.solDuration = solDuration;
    }

    public PlanetDateTime(DateTime epoch, PlanetCalendar calendar, Duration solDuration, int year, int monthOfYear, int solOfMonth, int hourOfSol, int minuteOfHour, int secondOfMinute) {
        this.epoch = epoch;
        this.calendar = calendar;
        this.year = year;
        this.monthOfYear = monthOfYear;
        this.solOfMonth = solOfMonth;
        this.hourOfSol = hourOfSol;
        this.minuteOfHour = minuteOfHour;
        this.secondOfMinute = secondOfMinute;
        MutableDateTime mutableTimePoint = new MutableDateTime(epoch);
        mutableTimePoint.add(calendar.solsInLeapPeriod() * solDuration.getMillis());
        int yearsUntilCurrentCalc = year - (year % calendar.getLeapPeriod().size());
        while (yearsUntilCurrentCalc <= year) {
            mutableTimePoint.add(calendar.solsInYear(yearsUntilCurrentCalc) * solDuration.getMillis());
            ++yearsUntilCurrentCalc;
        }
        --yearsUntilCurrentCalc;
        for (int currentMonth = 0; currentMonth < monthOfYear - 1; ++currentMonth) {
            mutableTimePoint.add(calendar.solsInMonth(yearsUntilCurrentCalc, monthOfYear) * solDuration.getMillis());
        }
        for (int currentSol = 0; currentSol < solOfMonth - 1; ++currentSol) {
            mutableTimePoint.add(solDuration.getMillis());
        }
        for (int currentHour = 0; currentHour < hourOfSol; ++currentHour) {
            mutableTimePoint.add(3600000);
        }
        for (int currentMinute = 0; currentMinute < minuteOfHour; ++currentMinute) {
            mutableTimePoint.add(60000);
        }
        for (int currentSecond = 0; currentSecond < secondOfMinute; ++currentSecond) {
            mutableTimePoint.add(1000);
        }
        timePoint = new DateTime(mutableTimePoint);
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
        solsElapsedUntilCurrentCalc -= calendar.solsInYear(year - 1);
        //--year; // because we return the nearest PREVIOUS year, not the NEXT one (!!!)
        // -- by now, the year is supposed to be calculated -- //
        monthOfYear = 0;
        while (solsElapsedUntilCurrentCalc <= wholeSolsSinceEpoch) {
            // we use the month starting from zero here as it accesses the list
            solsElapsedUntilCurrentCalc += calendar.solsInMonth(year, monthOfYear);
            ++monthOfYear;
        }
        solsElapsedUntilCurrentCalc -= calendar.solsInMonth(year, monthOfYear);
        // no need to decrement solOfMonth because it should start from 0 (!!!)
        // -- by now, we have month -- //
        solOfMonth = 0;
        while (solsElapsedUntilCurrentCalc < wholeSolsSinceEpoch) {
            ++solOfMonth;
            ++solsElapsedUntilCurrentCalc;
        }
        ++solOfMonth; // because it should start from 1
        // solOfMonth calculated

        long secondsElapsedUntilCurrentCalc = solsElapsedUntilCurrentCalc * solDuration.getMillis() / 1000;
        long totalSecondsElapsed = timeSinceEpoch().getStandardSeconds();
        hourOfSol = 0;
        while (secondsElapsedUntilCurrentCalc < totalSecondsElapsed) {
            secondsElapsedUntilCurrentCalc += 3600;
            ++hourOfSol;
        }
        secondsElapsedUntilCurrentCalc -= 3600;
        --hourOfSol;
        // hourOfSol calculated

        minuteOfHour = 0;
        while (secondsElapsedUntilCurrentCalc < totalSecondsElapsed) {
            secondsElapsedUntilCurrentCalc += 60;
            ++minuteOfHour;
        }
        secondsElapsedUntilCurrentCalc -= 60;
        --minuteOfHour;
        // minuteOfHour calculated

        secondOfMinute = 0;
        while (secondsElapsedUntilCurrentCalc < totalSecondsElapsed) {
            ++secondsElapsedUntilCurrentCalc;
            ++secondOfMinute;
        }
        --secondsElapsedUntilCurrentCalc;
        --secondOfMinute;
        // secondOfMinute calculated

//        // kludges:
//        if (hourOfSol == solDuration.getStandardHours()) hourOfSol = 0;
//        if (minuteOfHour == 60) minuteOfHour = 0;
//        if (secondOfMinute == 60) secondOfMinute = 0;
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
    public int getMonthOfYear() {
        if (monthOfYear == null) calcTime();
        return monthOfYear;
    }

    /**
     *
     * @return month object (if you need month number use getMonthOfYear instead)
     */
    public PlanetMonth getMonthObject() {
        return calendar.getMonths().get(getMonthOfYear() - 1);
    }

    /**
     *
     * @return solOfMonth number starting with 1
     */
    public int getSolOfMonth() {
        if (solOfMonth == null) calcTime();
        return solOfMonth;
    }

    @SuppressWarnings("UnusedDeclaration")
    public int getHourOfDay() {
        if (hourOfSol == null) calcTime();
        return hourOfSol;
    }

    public int getMinuteOfHour() {
       if (minuteOfHour == null) calcTime();
       return minuteOfHour;
    }

    public int getSecondOfMinute() {
        if (secondOfMinute == null) calcTime();
        return secondOfMinute;
    }

    /**
     *
     * @return week number starting with 1
     */
    public int getWeek(){
        if (week == null) {
            if (calendar.weekRestarts()) {
                // we use solOfMonth minus one because getSolOfMonth returns the number of the solOfMonth for normal humans
                // (i.e. starting from 1)
                week = (getSolOfMonth() - 1) / calendar.getWeekSols().size() + 1;
            } else {
                week = (int)(wholeSolsSinceEpoch() / calendar.getWeekSols().size() + 1);
            }
        }
        return week;
    }

}

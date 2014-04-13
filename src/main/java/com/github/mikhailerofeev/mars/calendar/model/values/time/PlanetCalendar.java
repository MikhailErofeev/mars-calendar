package com.github.mikhailerofeev.mars.calendar.model.values.time;



import java.util.List;
import java.util.Map;


/**
 * Created by Anton on 11.04.2014.
 */
public class PlanetCalendar {
    /**
     *  months with default number of days
     */
    private List<PlanetMonth> months;
    /**
     * weeksols' names
     */
    private List<String> weekSols;
    /**
     * The PlanetMonth is the month which contains these days.
     * The Integer is the number of extra days (if positive) or lacking days (if negative)
     */
    private List<Map<PlanetMonth, Integer>> leapPeriod;
    /**
     * If true, the week is restarted after the end of each month, otherwise isn't.
     */
    private boolean weekRestart = false;

    public PlanetCalendar(List<PlanetMonth> months, List<String> weekSols, List<Map<PlanetMonth, Integer>> leapPeriod, boolean weekRestart) {
        this.months = months;
        this.weekSols = weekSols;
        this.leapPeriod = leapPeriod;
        this.weekRestart = weekRestart;
    }

    public List<PlanetMonth> getMonths() {
        return months;
    }

    public List<String> getWeekSols() {
        return weekSols;
    }

    public List<Map<PlanetMonth, Integer>> getLeapPeriod() {
        return leapPeriod;
    }

    public boolean weekRestarts() {
        return weekRestart;
    }

    public int standardSolsInYear() {
        int sols = 0;
        for (PlanetMonth month : months) {
            sols += month.getNumSols();
        }
        return sols;
    }

    public int extraSolsInYear(int year) {
        int sols = 0;
        for (Map.Entry<PlanetMonth, Integer> leapYear : leapPeriod.get(year % leapPeriod.size()).entrySet()) {
            sols += leapYear.getKey().getNumSols();
        }
        return sols;
    }

    /**
     * returns the total number of sols in a specific year
     * @param year
     * @return
     */
    public int solsInYear(int year) {
        return standardSolsInYear() + extraSolsInYear(year);
    }

    /**
     * returns the total number of sols in a specific month
     * @param year
     * @param monthNum: the number of the month (starting from 0)
     * @return
     */
    public int solsInMonth(int year, int monthNum) {
        PlanetMonth month = months.get(monthNum);
        int sols = month.getNumSols();
        Map<PlanetMonth, Integer> leapYear = leapPeriod.get(year % leapPeriod.size());
        if (!leapYear.isEmpty() && leapYear.containsKey(month)) {
            sols += leapYear.get(month);
        }
        return sols;
    }

    public int solsInLeapPeriod() {
        int sols = standardSolsInYear() * leapPeriod.size();
        for (int i = 0; i < leapPeriod.size(); ++i) {
            sols += extraSolsInYear(i);
        }
        return sols;
    }

}

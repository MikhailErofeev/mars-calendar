package com.github.mikhailerofeev.mars.calendar.model.values;



import org.apache.commons.lang3.tuple.Pair;

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
    private List<String> weekSols;
    /**
     * The first Integer is number of the year which is leap year.
     * The second Integer is the number of extra days (if positive) or lacking days (if negative)
     * the month contains in addition to the default number of days.
     * The PlanetMonth is the month which contains these days.
     */
    private Map<Integer, Map<PlanetMonth, Integer>> leapYears;

    public PlanetCalendar(List<PlanetMonth> months) {
        this.months = months;
    }

    public PlanetCalendar(List<PlanetMonth> months, Map<Integer, Map<PlanetMonth, Integer>> leapYears) {
        this.months = months;
        this.leapYears = leapYears;
    }

    public PlanetCalendar(List<PlanetMonth> months,
                          Map<Integer, Map<PlanetMonth, Integer>> leapYears,
                          List<String> weekSols) {
        this.months = months;
        this.weekSols = weekSols;
        this.leapYears = leapYears;
    }

    public List<PlanetMonth> getMonths() {
        return months;
    }

    public List<String> getWeekSols() {
        return weekSols;
    }

    public Map<Integer, Map<PlanetMonth, Integer>> getLeapYears() {
        return leapYears;
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
        for (Map.Entry<Integer, Map<PlanetMonth, Integer>> leapYear : leapYears.entrySet()) {
            if (year % leapYear.getKey() == 0) {
                for (Map.Entry<PlanetMonth, Integer> extraDays : leapYear.getValue().entrySet()) {
                    sols += extraDays.getValue();
                }
            }
        }
        return sols;
    }

    public int solsInYear(int year) {
        return standardSolsInYear() + extraSolsInYear(year);
    }

    public int solsInMonth(int year, int month) {
        int sols = months.get(month).getNumSols();
        for (Map.Entry<Integer, Map<PlanetMonth, Integer>> leapYear : leapYears.entrySet()) {
            if (year % leapYear.getKey() == 0 && leapYear.getValue().containsKey(month)) {
                sols += leapYear.getValue().get(month);
            }
        }
        return sols;
    }

}

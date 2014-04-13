package com.github.mikhailerofeev.mars.calendar.model.values.time;

import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 * Created by Максим on 13.04.2014.
 */
public class LinkedMonth {
    public MonthReply origin = new MonthReply();
    public MonthReply alternative = new MonthReply();

    public LinkedMonth(String originalPlanet, String alternativePlanet, int year, int month) {
        month--;
        originalPlanet = originalPlanet.toLowerCase();
        alternativePlanet = alternativePlanet.toLowerCase();

        origin.planetName = originalPlanet;
        origin.month = month;
        origin.year = year;
        PlanetCalendar pc = CalendarFactory.getPlanetCalendar(originalPlanet);
        origin.name = pc.getMonths().get(month).getName();
        origin.days = pc.getMonths().get(month).getNumSols();
        //origin.timestamp = new DateTime().getMillis(); //1 jan 00.00 since def epoch

        alternative = getMonthReply(alternativePlanet, year, month);
    }

    MonthReply getMonthReply(String planet, int year, int month) {
        month ++ ;
        DateTime defaultEpoch = EpochFactory.getDefaultEpoch();
        PlanetCalendar pc = CalendarFactory.getPlanetCalendar(planet);
        PlanetDateTime dpt = new PlanetDateTime(defaultEpoch, pc, year, month, 1, 0, 0, 0);
        DateTime eTime = dpt.toTerrestrial();

        int eyear = eTime.getYear();
        int emonth = eTime.getMonthOfYear();
        int eday = dpt.getMonthObject().getNumSols();
        int ehour = eTime.getHourOfDay();
        int eminute = eTime.getMinuteOfDay();

        DateTime eDT = new DateTime(eyear,emonth, eday, ehour, eminute);

        Duration solDuration = SolDurationFactory.getSolDuration(planet);

        PlanetDateTime edpt = new PlanetDateTime(eDT, defaultEpoch , pc, solDuration);

        MonthReply mr = new MonthReply();
        mr.planetName = planet;
        mr.month = edpt.getMonthOfYear();
        mr.year = edpt.getYear();

        mr.name = edpt.getMonthObject().getName();
        mr.days = edpt.getMonthObject().getNumSols();
        //origin.timestamp = new DateTime().getMillis(); //1 jan 00.00 since def epoch

        return mr;
    }
}

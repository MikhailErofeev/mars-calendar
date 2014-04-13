package com.github.mikhailerofeev.mars.calendar.model.values.time;

import org.joda.time.DateTime;

/**
 * Created by Максим on 13.04.2014.
 */
public class LinkedMonth {
    public PlanetMonth origin      = null;
    public PlanetMonth alternative = null;

    LinkedMonth(String planet, int year, int month){
        PlanetDateTime pdt = null;
        try {
            pdt = new PlanetDateTime(new DateTime(year, month, 1, 0, 0), EpochFactory.getDefaultEpoch(), CalendarFactory.getPlanetCalendar(planet.toLowerCase()),SolDurationFactory.getSolDuration(planet.toLowerCase()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert pdt != null;
        origin = pdt.getMonth();

    }
}

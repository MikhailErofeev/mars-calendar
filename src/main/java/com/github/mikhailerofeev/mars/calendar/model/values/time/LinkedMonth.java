package com.github.mikhailerofeev.mars.calendar.model.values.time;

import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 * Created by Максим on 13.04.2014.
 */
public class LinkedMonth {
    public MonthReply origin = null;
    public MonthReply alternative = null;

    public LinkedMonth(String originalPlanet, String alternativePlanet, int year, int month) {
        originalPlanet = originalPlanet.toLowerCase();
        alternativePlanet = alternativePlanet.toLowerCase();

        origin = getMonthReply(originalPlanet, year, month);
        alternative = getMonthReply(alternativePlanet, year, month);
    }

    MonthReply getMonthReply(String planet, int year, int month) {
        PlanetDateTime pdt = null;
        MonthReply  mr = null;
        mr.name = planet;


        if (planet.equals("earth")) {
            DateTime catchedDateTime = new DateTime(year, month, 1, 0, 0);
            DateTime defaulEpoch = EpochFactory.getDefaultEpoch();

//            pdt = new PlanetDateTime(catchedDateTime, defaulEpoch);
            DateTime dt = new DateTime(0).plus(pdt.timeSinceEpoch());
            //mr. = dt.monthOfYear();
            //todo -implement to earth conversion
        } else {
            DateTime catchedDateTime = new DateTime(year, month, 1, 0, 0);
            DateTime defaulEpoch = EpochFactory.getDefaultEpoch();
            PlanetCalendar pc = CalendarFactory.getPlanetCalendar(planet);
            Duration solDuration = SolDurationFactory.getSolDuration(planet);

            pdt = new PlanetDateTime(catchedDateTime, defaulEpoch, pc, solDuration);

            mr.year = pdt.getYear();
            mr.days = pdt.getSolOfMonth();
            mr.month = pdt.getMonthOfYear();
        }

        return mr;
    }
}

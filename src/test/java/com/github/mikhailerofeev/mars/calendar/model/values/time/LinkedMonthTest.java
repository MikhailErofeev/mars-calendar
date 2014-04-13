package com.github.mikhailerofeev.mars.calendar.model.values.time;

import junit.framework.TestCase;
import org.joda.time.DateTime;
import org.junit.Test;

/**
 * Created by Максим on 13.04.2014.
 */
public class LinkedMonthTest extends TestCase {

    @Test
    public void testPDT() {
        int year = 2014;
        int month = 1;

        String originalPlanet = "earth";
        String alternativePlanet = "mars";

        LinkedMonth currMonth = new LinkedMonth(originalPlanet, alternativePlanet, year, month);

    }
}

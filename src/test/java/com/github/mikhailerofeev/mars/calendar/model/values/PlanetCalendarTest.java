package com.github.mikhailerofeev.mars.calendar.model.values;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.*;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Anton on 12.04.2014.
 */
@RunWith(JUnit4.class)
public class PlanetCalendarTest {

    @Test
    public void testCalendarCreation(){
        String[] monthNames = {
                "Sagittarius", "Dhanus", "Capricornus", "Makara", "Aquarius", "Kumbha",
                "Pisces", "Mina", "Aries", "Mesha", "Taurus", "Rishabha",
                "Gemini", "Mithuna", "Cancer", "Karka", "Leo", "Simha",
                "Virgo", "Kanya", "Libra", "Tula", "Scorpius", "Vrishika"
        };

        int[] monthDayNums = {
                28, 28, 28, 28, 28, 27,
                28, 28, 28, 28, 28, 27,
                28, 28, 28, 28, 28, 27,
                28, 28, 28, 28, 28, 27
        };
        List<PlanetMonth> months = new ArrayList<PlanetMonth>();
        for (int i = 0; i < 24; ++i) {
            months.add(new PlanetMonth(monthDayNums[i], monthNames[i]));
        }
        assertEquals(months.size(), 24);

        String[] weekSolNames = {
                "Sol Solis", "Sol Lunae", "Sol Martis", "Sol Mercurii", "Sol Jovis", "Sol Veneris", "Sol Saturni"
        };
        List<String> weekSols = new ArrayList<String> (Arrays.asList(weekSolNames));
        assertEquals(weekSols.size(), 7);

        int periodTime = 10;
        Map<PlanetMonth, Integer> leapMonths = new TreeMap<PlanetMonth, Integer>();
        leapMonths.put(months.get(23), 1);
        assertEquals(leapMonths.size(), 1);

        List<Map<PlanetMonth, Integer>> leapPeriod = new ArrayList<Map<PlanetMonth, Integer>>(periodTime);
        for (int i = 0; i < 6; ++i) {
            leapPeriod.add(new TreeMap(leapMonths));
        }
        assertEquals(leapPeriod.size(), 6);
        assertEquals(leapPeriod.get(5), leapPeriod.get(4));
        assertTrue(leapPeriod.get(6).isEmpty());

        PlanetCalendar marsCalendar = new PlanetCalendar(months, weekSols, leapPeriod, true);
    }
}

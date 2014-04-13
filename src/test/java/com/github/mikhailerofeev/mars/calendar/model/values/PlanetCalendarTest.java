package com.github.mikhailerofeev.mars.calendar.model.values;

import com.github.mikhailerofeev.mars.calendar.model.values.time.PlanetCalendar;
import com.github.mikhailerofeev.mars.calendar.model.values.time.PlanetMonth;
import org.junit.Before;
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
    private PlanetCalendar marsCalendar;

    @Before
    public void createCalendar() {
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
        Map<PlanetMonth, Integer> leapMonths = new HashMap<PlanetMonth, Integer>();
        leapMonths.put(months.get(23), 1);
        assertEquals(leapMonths.size(), 1);

        List<Map<PlanetMonth, Integer>> leapPeriod = new ArrayList<Map<PlanetMonth, Integer>>(periodTime);
        for (int i = 0; i < 6; ++i) {
            leapPeriod.add(new HashMap(leapMonths));
        }
        assertEquals(leapPeriod.size(), 6);
        assertEquals(leapPeriod.get(5), leapPeriod.get(4));
        //assertTrue(leapPeriod.get(6) == null);

        marsCalendar = new PlanetCalendar(months, weekSols, leapPeriod, true);
    }

    @Test
    public void testCalendarCreation(){
        int a0 = marsCalendar.extraSolsInYear(100);
        int a1 = marsCalendar.solsInMonth(100, 5);
        int a2 = marsCalendar.solsInYear(100);
        int a3 = marsCalendar.standardSolsInYear();
        List<Map<PlanetMonth, Integer>> a4 = new ArrayList<Map<PlanetMonth, Integer>>(marsCalendar.getLeapPeriod());
        List<PlanetMonth> a5 = new ArrayList<PlanetMonth>(marsCalendar.getMonths());
        List<String> a6 = marsCalendar.getWeekSols();
    }
}

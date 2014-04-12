package com.github.mikhailerofeev.mars.calendar.model.values;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Максим on 12.04.2014.
 */
public class PlanetDateTimeTest {

    private DateTime timeNow;
    private DateTime epochTime;
    private DateTime zeroTime;

    @Before
    public void Setup()
    {
        timeNow     = DateTime.now();
        epochTime   = new DateTime(1965, 7, 15, 1, 0);
        zeroTime    = new DateTime(1, 1, 1, 0, 0);
    }

    public PlanetCalendar CalInit(){
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
        return marsCalendar;
    }


    @Test
    public void testZeroEpochZeroTime() throws Exception{
        PlanetDateTime marsZeroTime     = new PlanetDateTime(zeroTime,epochTime,CalInit(),new Duration(88642663));
        System.out.println(marsZeroTime.getDay());
    }

    private void testGetTime(DateTime expected, DateTime asq){
        assert(expected == asq);
    }

    private void testGetVal(int expected, int asq){
        assert(expected == asq);
    }

}

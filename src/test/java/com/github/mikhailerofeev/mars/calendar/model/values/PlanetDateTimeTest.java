package com.github.mikhailerofeev.mars.calendar.model.values;

import com.github.mikhailerofeev.mars.calendar.model.values.time.PlanetCalendar;
import com.github.mikhailerofeev.mars.calendar.model.values.time.PlanetDateTime;
import com.github.mikhailerofeev.mars.calendar.model.values.time.PlanetMonth;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
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

    private DateTime timeToStore;
    private DateTime epochToUse;
    private DateTime zeroTime;
    private PlanetCalendar calendar;

    @Before
    public void CalInit(){
        timeToStore = DateTime.now();
        epochToUse = new DateTime(1965, 7, 15, 1, 0);
        zeroTime    = new DateTime(1, 1, 1, 0, 0);

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
        int leapYearsNum = 6;
        Map<PlanetMonth, Integer> leapMonths = new HashMap<PlanetMonth, Integer>();
        leapMonths.put(months.get(23), 1);
        assertEquals(leapMonths.size(), 1);

        List<Map<PlanetMonth, Integer>> leapPeriod = new ArrayList<Map<PlanetMonth, Integer>>(periodTime);
        for (int i = 0; i < leapYearsNum; ++i) {
            leapPeriod.add(new HashMap(leapMonths));
        }
        for (int i = 6; i < periodTime; ++i) {
            leapPeriod.add(new HashMap());
        }
        assertEquals(leapPeriod.size(), 10);
        assertEquals(leapPeriod.get(5), leapPeriod.get(4));
        //assertTrue(leapPeriod.get(6) == null);

        calendar = new PlanetCalendar(months, weekSols, leapPeriod, true);
    }


    @Test
    public void testZeroEpochZeroTime() throws Exception{
        Duration solDuration = new Duration(88642663);
        for (int j = -40; j < 30; ++j) {
            timeToStore = DateTime.now().plus(new Duration(3600000 * j));
            PlanetDateTime marsZeroTime = new PlanetDateTime(timeToStore, epochToUse, calendar, solDuration);
            assertEquals(marsZeroTime.getSolDuration().getStandardHours(), 24);
            assertTrue(new Duration(86400000).isShorterThan(solDuration));
            assertTrue(marsZeroTime.getCalendar().weekRestarts());
            for (int i = 0; i < marsZeroTime.getCalendar().getMonths().size(); ++i) {
                assertEquals(marsZeroTime.getCalendar().getMonths().get(i).getNumSols(), 27 + ((i + 1) % 6 == 0 ? 0 : 1));
            }
            assertEquals(marsZeroTime.getCalendar().getMonths().size(), 24);
            //assertEquals(marsZeroTime.getYear(), 26);
            System.out.println(marsZeroTime.getYear() + "/" + marsZeroTime.getMonthOfYear() + "/" + marsZeroTime.getSolOfMonth()
                    + ", " + marsZeroTime.getHourOfDay() + ":" + marsZeroTime.getMinuteOfHour() + ":" + marsZeroTime.getSecondOfMinute());
        }


    }

    @Test
    public void testTimeZones(){
        DateTimeZone dateTimeZone = DateTimeZone.forOffsetHours(0);
        DateTime dateTime = new DateTime(0, dateTimeZone);
        DateTime a = dateTime;
        assertEquals(a.getHourOfDay(), 0);

        PlanetDateTime pdt = new PlanetDateTime(dateTime, dateTime, null, new Duration(24*60*60*100));
        assertEquals(pdt.getHourOfDay(), 0);

    }
}

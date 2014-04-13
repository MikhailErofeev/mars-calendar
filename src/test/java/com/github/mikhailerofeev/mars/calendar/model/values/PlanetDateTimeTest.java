package com.github.mikhailerofeev.mars.calendar.model.values;

import com.github.mikhailerofeev.mars.calendar.model.values.time.PlanetCalendar;
import com.github.mikhailerofeev.mars.calendar.model.values.time.PlanetDateTime;
import com.github.mikhailerofeev.mars.calendar.model.values.time.PlanetMonth;
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

        calendar = new PlanetCalendar(months, weekSols, leapPeriod, true);
    }


    @Test
    public void testZeroEpochZeroTime() throws Exception{
        Duration solDuration = new Duration(88642663);
        PlanetDateTime marsZeroTime = new PlanetDateTime(DateTime.now(), epochToUse, calendar, solDuration);
        assertEquals(marsZeroTime.getSolDuration().getStandardHours(), 24);
        assertTrue(new Duration(86400000).isShorterThan(solDuration));
        assertTrue(marsZeroTime.getCalendar().weekRestarts());
        for (int i = 0; i < marsZeroTime.getCalendar().getMonths().size(); ++i) {
            assertEquals(marsZeroTime.getCalendar().getMonths().get(i).getNumSols(), 27 + ((i + 1) % 6 == 0 ? 0 : 1));
        }
        assertEquals(marsZeroTime.getCalendar().getMonths().size(), 24);
        assertEquals(marsZeroTime.getYear(), 24);
    }
}

package com.github.mikhailerofeev.mars.calendar.model.values.time;

import org.joda.time.DateTime;

import java.util.*;

/**
 * Created by Максим on 13.04.2014.
 */
public class CalendarFactory {
    public static PlanetCalendar getPlanetCalendar(String name) {
        if (name.toLowerCase().equals("mars")) {
            PlanetDateTime planetDT;
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
            String[] weekSolNames = {
                    "Sol Solis", "Sol Lunae", "Sol Martis", "Sol Mercurii", "Sol Jovis", "Sol Veneris", "Sol Saturni"
            };
            List<String> weekSols = new ArrayList<String>(Arrays.asList(weekSolNames));
            int periodTime = 10;
            int leapYearsNum = 6;
            Map<PlanetMonth, Integer> leapMonths = new HashMap<PlanetMonth, Integer>();
            leapMonths.put(months.get(23), 1);
            List<Map<PlanetMonth, Integer>> leapPeriod = new ArrayList<Map<PlanetMonth, Integer>>(periodTime);
            for (int i = 0; i < leapYearsNum; ++i) {
                leapPeriod.add(new HashMap(leapMonths));
            }
            for (int i = leapYearsNum; i < periodTime; ++i) {
                leapPeriod.add(new HashMap());
            }
            PlanetCalendar planetCalendar = new PlanetCalendar(months, weekSols, leapPeriod, true);
            return planetCalendar;
        }
        else if (name.toLowerCase().equals("earth")) {
            PlanetDateTime planetDT;
            String[] monthNames = {
                    "January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"
            };

            int[] monthDayNums = {
                    31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
            };
            List<PlanetMonth> months = new ArrayList<PlanetMonth>();
            for (int i = 0; i < monthNames.length; ++i) {
                months.add(new PlanetMonth(monthDayNums[i], monthNames[i]));
            }
            String[] weekSolNames = {
                    "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
            };
            List<String> weekSols = new ArrayList<String>(Arrays.asList(weekSolNames));
            int periodTime = 1;
            int leapYearsNum = 4;
            Map<PlanetMonth, Integer> leapMonths = new HashMap<PlanetMonth, Integer>();
            leapMonths.put(months.get(2), 1);
            List<Map<PlanetMonth, Integer>> leapPeriod = new ArrayList<Map<PlanetMonth, Integer>>(periodTime);
            for (int i = 0; i < leapYearsNum; ++i) {
                leapPeriod.add(new HashMap(leapMonths));
            }
            for (int i = leapYearsNum; i < periodTime; ++i) {
                leapPeriod.add(new HashMap());
            }
            PlanetCalendar planetCalendar = new PlanetCalendar(months, weekSols, leapPeriod, true);
            return planetCalendar;
        }
        else throw new IllegalArgumentException("Planet not found");
    }
}

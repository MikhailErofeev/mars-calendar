package com.github.mikhailerofeev.mars.calendar.model.values;

import org.joda.time.DateTime;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 09.04.14
 */
@SuppressWarnings("UnusedDeclaration")
public class MarsDateTime {

    public static String[] monthNames = {
        "Sagittarius", "Dhanus", "Capricornus", "Makara", "Aquarius", "Kumbha",
        "Pisces", "Mina", "Aries", "Mesha", "Taurus", "Rishabha",
        "Gemini", "Mithuna", "Cancer", "Karka", "Leo", "Simha",
        "Virgo", "Kanya", "Libra", "Tula", "Scorpius", "Vrishika"
    };

    public static String[] weekSolNames = {
        "Sol Solis", "Sol Lunae", "Sol Martis", "Sol Mercurii", "Sol Jovis", "Sol Veneris", "Sol Saturni"
    };

    private final long unixTimeStamp;

    public MarsDateTime(final long unixTimeStamp) {
        this.unixTimeStamp = unixTimeStamp;
    }

    /**
     * now
     */
    public MarsDateTime() {
        unixTimeStamp = DateTime.now().getMillis();
    }

    public int getYear() {
        throw new NotImplementedException();
    }

    public int getMonthOfYear() {
        throw new NotImplementedException();
    }

    public int getSol() {
        throw new NotImplementedException();
    }



    /** Mars Solar Date (in seconds) */
    public double getMarsSolarDate() {
        return (double)DateTime.parse("2000-01-06T00:00:00").getMillis() / 88775244. + 44795.9998;
    }

    @Deprecated
    public double getMSD() {
        return getMarsSolarDate();
    }

    /** Mars Time Coordinated */
    public long getMarsTimeCoordinated() {
        return ((long)getMarsSolarDate() % 86400) * 24;
    }

    @Deprecated
    public long getMTC() {
        return getMarsTimeCoordinated();
    }

}

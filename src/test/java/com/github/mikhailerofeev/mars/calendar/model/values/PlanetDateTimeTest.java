package com.github.mikhailerofeev.mars.calendar.model.values;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

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

    @Test
    public void testZeroEpochZeroTime() throws Exception{
        PlanetDateTime marsZeroTime     = new PlanetDateTime(zeroTime);
        PlanetDateTime marsZeroEpoch    = new PlanetDateTime(zeroTime, epochTime);


    }

    private void testGetTime(DateTime expected, DateTime asq){
        assert(expected == asq);
    }

    private void testGetVal(int expected, int asq){
        assert(expected == asq);
    }

}

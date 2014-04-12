package com.github.mikhailerofeev.mars.calendar.model.values;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Anton on 12.04.2014.
 */
@RunWith(JUnit4.class)
public class PlanetCalendarTest {

    @Test
    public void testCalendarCreation(){
        List<PlanetMonth> months = new ArrayList<PlanetMonth>(){{
            add(new PlanetMonth(5, "goo"));
        }};
        PlanetCalendar marsCalendar = new PlanetCalendar()
    }
}

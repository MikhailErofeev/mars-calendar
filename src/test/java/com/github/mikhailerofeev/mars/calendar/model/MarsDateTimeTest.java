package com.github.mikhailerofeev.mars.calendar.model;

import com.github.mikhailerofeev.mars.calendar.model.values.MarsDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by Anton on 09.04.2014.
 */
@RunWith(JUnit4.class)
public class MarsDateTimeTest {
    @Test
    public void testGetYear() throws Exception {
        MarsDateTime now = new MarsDateTime();
        now.getYear();

    }
}

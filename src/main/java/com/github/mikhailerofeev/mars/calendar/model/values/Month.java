package com.github.mikhailerofeev.mars.calendar.model.values;

/**
 * Created by Anton on 11.04.2014.
 */
public class Month {
    private int numDays;
    private String name = null;

    Month(int numDays) {
        this.numDays = numDays;
    }

    Month(int numDays, String name) {
        this.numDays = numDays;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNumDays() {
        return numDays;
    }


}

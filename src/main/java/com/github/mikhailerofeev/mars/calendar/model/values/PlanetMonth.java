package com.github.mikhailerofeev.mars.calendar.model.values;

/**
 * Created by Anton on 11.04.2014.
 */
public class PlanetMonth {
    private int numSols;
    private String name = null;

    PlanetMonth(int numSols) {
        this.numSols = numSols;
    }

    PlanetMonth(int numSols, String name) {
        this.numSols = numSols;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNumSols() {
        return numSols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanetMonth)) return false;

        PlanetMonth that = (PlanetMonth) o;

        if (numSols != that.numSols) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numSols;
        result = 31 * result + name.hashCode();
        return result;
    }
}

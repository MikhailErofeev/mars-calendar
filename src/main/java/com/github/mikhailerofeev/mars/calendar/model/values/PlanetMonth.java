package com.github.mikhailerofeev.mars.calendar.model.values;

/**
 * Contains abstact month class
 */
public class PlanetMonth {
    private int numSols;
    private String name = null;

    @SuppressWarnings("UnusedDeclaration")
    PlanetMonth(int numSols) {
        this.numSols = numSols;
    }

    PlanetMonth(int numSols, String name) {
        this.numSols = numSols;
        this.name = name;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getName() {
        return name;
    }

    @SuppressWarnings("UnusedDeclaration")
    public int getNumSols() {
        return numSols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanetMonth)) return false;

        PlanetMonth that = (PlanetMonth) o;

        return numSols == that.numSols && name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = numSols;
        result = 31 * result + name.hashCode();
        return result;
    }
}

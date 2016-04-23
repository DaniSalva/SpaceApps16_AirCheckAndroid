package com.spaceapps.aircheck.JSONObjects.weather;

public class Coord {

    private Float lon;
    private Float lat;

    /**
     * No args constructor for use in serialization
     */
    public Coord() {
    }

    /**
     * @param lon
     * @param lat
     */
    public Coord(Float lon, Float lat) {
        this.lon = lon;
        this.lat = lat;
    }

    /**
     * @return The lon
     */
    public Float getLon() {
        return lon;
    }

    /**
     * @param lon The lon
     */
    public void setLon(Float lon) {
        this.lon = lon;
    }

    /**
     * @return The lat
     */
    public Float getLat() {
        return lat;
    }

    /**
     * @param lat The lat
     */
    public void setLat(Float lat) {
        this.lat = lat;
    }

}

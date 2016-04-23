package com.spaceapps.aircheck.JSONObjects.ozone;

public class O3 {

    private String time;
    private Location location;
    private Float data;

    /**
     * No args constructor for use in serialization
     */
    public O3() {
    }

    /**
     * @param time
     * @param location
     * @param data
     */
    public O3(String time, Location location, Float data) {
        this.time = time;
        this.location = location;
        this.data = data;
    }

    /**
     * @return The time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time The time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return The data
     */
    public Float getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(Float data) {
        this.data = data;
    }

}

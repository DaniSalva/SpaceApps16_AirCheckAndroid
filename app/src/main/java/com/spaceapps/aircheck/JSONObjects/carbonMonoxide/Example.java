package com.spaceapps.aircheck.JSONObjects.carbonMonoxide;

import java.util.ArrayList;
import java.util.List;

public class Example {

    private String time;
    private Location location;
    private List<Datum> data = new ArrayList<Datum>();

    /**
     * No args constructor for use in serialization
     */
    public Example() {
    }

    /**
     * @param time
     * @param location
     * @param data
     */
    public Example(String time, Location location, List<Datum> data) {
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
    public List<Datum> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<Datum> data) {
        this.data = data;
    }

}

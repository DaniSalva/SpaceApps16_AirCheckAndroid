package com.spaceapps.aircheck.JSONObjects.ozone;

public class Location {

    private Float latitude;
    private Float longitude;

    /**
     * No args constructor for use in serialization
     */
    public Location() {
    }

    /**
     * @param longitude
     * @param latitude
     */
    public Location(Float latitude, Float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * @return The latitude
     */
    public Float getLatitude() {
        return latitude;
    }

    /**
     * @param latitude The latitude
     */
    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    /**
     * @return The longitude
     */
    public Float getLongitude() {
        return longitude;
    }

    /**
     * @param longitude The longitude
     */
    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

}

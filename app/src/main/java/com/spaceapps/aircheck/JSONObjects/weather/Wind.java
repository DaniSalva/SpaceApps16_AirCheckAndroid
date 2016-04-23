package com.spaceapps.aircheck.JSONObjects.weather;

public class Wind {

    private Float speed;
    private Float deg;

    /**
     * No args constructor for use in serialization
     */
    public Wind() {
    }

    /**
     * @param speed
     * @param deg
     */
    public Wind(Float speed, Float deg) {
        this.speed = speed;
        this.deg = deg;
    }

    /**
     * @return The speed
     */
    public Float getSpeed() {
        return speed;
    }

    /**
     * @param speed The speed
     */
    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    /**
     * @return The deg
     */
    public Float getDeg() {
        return deg;
    }

    /**
     * @param deg The deg
     */
    public void setDeg(Float deg) {
        this.deg = deg;
    }

}

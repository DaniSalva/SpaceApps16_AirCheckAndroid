package com.spaceapps.aircheck.JSONObjects.weather;

public class Clouds {

    private Integer all;

    /**
     * No args constructor for use in serialization
     */
    public Clouds() {
    }

    /**
     * @param all
     */
    public Clouds(Integer all) {
        this.all = all;
    }

    /**
     * @return The all
     */
    public Integer getAll() {
        return all;
    }

    /**
     * @param all The all
     */
    public void setAll(Integer all) {
        this.all = all;
    }

}

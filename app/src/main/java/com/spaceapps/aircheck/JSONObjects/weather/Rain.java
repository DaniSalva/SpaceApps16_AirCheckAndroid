package com.spaceapps.aircheck.JSONObjects.weather;

public class Rain {

    private Integer _3h;

    /**
     * No args constructor for use in serialization
     */
    public Rain() {
    }

    /**
     * @param _3h
     */
    public Rain(Integer _3h) {
        this._3h = _3h;
    }

    /**
     * @return The _3h
     */
    public Integer get3h() {
        return _3h;
    }

    /**
     * @param _3h The 3h
     */
    public void set3h(Integer _3h) {
        this._3h = _3h;
    }

}

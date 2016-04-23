package com.spaceapps.aircheck.JSONObjects.carbonMonoxide;

public class Datum {

    private Float precision;
    private Float pressure;
    private Float value;

    /**
     * No args constructor for use in serialization
     */
    public Datum() {
    }

    /**
     * @param precision
     * @param pressure
     * @param value
     */
    public Datum(Float precision, Float pressure, Float value) {
        this.precision = precision;
        this.pressure = pressure;
        this.value = value;
    }

    /**
     * @return The precision
     */
    public Float getPrecision() {
        return precision;
    }

    /**
     * @param precision The precision
     */
    public void setPrecision(Float precision) {
        this.precision = precision;
    }

    /**
     * @return The pressure
     */
    public Float getPressure() {
        return pressure;
    }

    /**
     * @param pressure The pressure
     */
    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }

    /**
     * @return The value
     */
    public Float getValue() {
        return value;
    }

    /**
     * @param value The value
     */
    public void setValue(Float value) {
        this.value = value;
    }

}

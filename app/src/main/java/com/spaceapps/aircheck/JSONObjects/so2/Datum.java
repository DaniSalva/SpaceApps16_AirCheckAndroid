
package com.spaceapps.aircheck.JSONObjects.so2;


public class Datum {

    private Double precision;
    private Double pressure;
    private Double value;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param precision
     * @param pressure
     * @param value
     */
    public Datum(Double precision, Double pressure, Double value) {
        this.precision = precision;
        this.pressure = pressure;
        this.value = value;
    }

    /**
     * 
     * @return
     *     The precision
     */
    public Double getPrecision() {
        return precision;
    }

    /**
     * 
     * @param precision
     *     The precision
     */
    public void setPrecision(Double precision) {
        this.precision = precision;
    }

    /**
     * 
     * @return
     *     The pressure
     */
    public Double getPressure() {
        return pressure;
    }

    /**
     * 
     * @param pressure
     *     The pressure
     */
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    /**
     * 
     * @return
     *     The value
     */
    public Double getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    public void setValue(Double value) {
        this.value = value;
    }

}

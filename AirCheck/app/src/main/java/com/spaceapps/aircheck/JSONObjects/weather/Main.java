package com.spaceapps.aircheck.JSONObjects.weather;

public class Main {

    private Float temp;
    private Integer pressure;
    private Integer humidity;
    private Float tempMin;
    private Float tempMax;
    private Float seaLevel;
    private Float grndLevel;

    /**
     * No args constructor for use in serialization
     */
    public Main() {
    }

    /**
     * @param seaLevel
     * @param humidity
     * @param pressure
     * @param grndLevel
     * @param tempMax
     * @param temp
     * @param tempMin
     */
    public Main(Float temp, Integer pressure, Integer humidity, Float tempMin, Float tempMax, Float seaLevel, Float grndLevel) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.seaLevel = seaLevel;
        this.grndLevel = grndLevel;
    }

    /**
     * @return The temp
     */
    public Float getTemp() {
        return temp;
    }

    /**
     * @param temp The temp
     */
    public void setTemp(Float temp) {
        this.temp = temp;
    }

    /**
     * @return The pressure
     */
    public Integer getPressure() {
        return pressure;
    }

    /**
     * @param pressure The pressure
     */
    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    /**
     * @return The humidity
     */
    public Integer getHumidity() {
        return humidity;
    }

    /**
     * @param humidity The humidity
     */
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    /**
     * @return The tempMin
     */
    public Float getTempMin() {
        return tempMin;
    }

    /**
     * @param tempMin The temp_min
     */
    public void setTempMin(Float tempMin) {
        this.tempMin = tempMin;
    }

    /**
     * @return The tempMax
     */
    public Float getTempMax() {
        return tempMax;
    }

    /**
     * @param tempMax The temp_max
     */
    public void setTempMax(Float tempMax) {
        this.tempMax = tempMax;
    }

    /**
     * @return The seaLevel
     */
    public Float getSeaLevel() {
        return seaLevel;
    }

    /**
     * @param seaLevel The sea_level
     */
    public void setSeaLevel(Float seaLevel) {
        this.seaLevel = seaLevel;
    }

    /**
     * @return The grndLevel
     */
    public Float getGrndLevel() {
        return grndLevel;
    }

    /**
     * @param grndLevel The grnd_level
     */
    public void setGrndLevel(Float grndLevel) {
        this.grndLevel = grndLevel;
    }

}

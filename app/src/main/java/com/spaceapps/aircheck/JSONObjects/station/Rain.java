
package com.spaceapps.aircheck.JSONObjects.station;

public class Rain {

    private Integer _1h;
    private Double _24h;
    private Integer today;

    /**
     * 
     * @return
     *     The _1h
     */
    public Integer get1h() {
        return _1h;
    }

    /**
     * 
     * @param _1h
     *     The 1h
     */
    public void set1h(Integer _1h) {
        this._1h = _1h;
    }

    /**
     * 
     * @return
     *     The _24h
     */
    public Double get24h() {
        return _24h;
    }

    /**
     * 
     * @param _24h
     *     The 24h
     */
    public void set24h(Double _24h) {
        this._24h = _24h;
    }

    /**
     * 
     * @return
     *     The today
     */
    public Integer getToday() {
        return today;
    }

    /**
     * 
     * @param today
     *     The today
     */
    public void setToday(Integer today) {
        this.today = today;
    }

}

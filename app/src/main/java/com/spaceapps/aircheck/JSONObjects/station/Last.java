
package com.spaceapps.aircheck.JSONObjects.station;

import java.util.ArrayList;
import java.util.List;

public class Last {

    private Main main;
    private Wind wind;
    private Visibility visibility;
    private Calc calc;
    private List<Cloud> clouds = new ArrayList<Cloud>();
    private Integer dt;

    /**
     * 
     * @return
     *     The main
     */
    public Main getMain() {
        return main;
    }

    /**
     * 
     * @param main
     *     The main
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * 
     * @return
     *     The wind
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * 
     * @param wind
     *     The wind
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    /**
     * 
     * @return
     *     The visibility
     */
    public Visibility getVisibility() {
        return visibility;
    }

    /**
     * 
     * @param visibility
     *     The visibility
     */
    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    /**
     * 
     * @return
     *     The calc
     */
    public Calc getCalc() {
        return calc;
    }

    /**
     * 
     * @param calc
     *     The calc
     */
    public void setCalc(Calc calc) {
        this.calc = calc;
    }

    /**
     * 
     * @return
     *     The clouds
     */
    public List<Cloud> getClouds() {
        return clouds;
    }

    /**
     * 
     * @param clouds
     *     The clouds
     */
    public void setClouds(List<Cloud> clouds) {
        this.clouds = clouds;
    }

    /**
     * 
     * @return
     *     The dt
     */
    public Integer getDt() {
        return dt;
    }

    /**
     * 
     * @param dt
     *     The dt
     */
    public void setDt(Integer dt) {
        this.dt = dt;
    }

}

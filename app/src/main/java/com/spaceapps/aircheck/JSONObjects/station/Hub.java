
package com.spaceapps.aircheck.JSONObjects.station;

public class Hub {

    private Station station;
    private Double distance;
    private Last last;

    /**
     * 
     * @return
     *     The station
     */
    public Station getStation() {
        return station;
    }

    /**
     * 
     * @param station
     *     The station
     */
    public void setStation(Station station) {
        this.station = station;
    }

    /**
     * 
     * @return
     *     The distance
     */
    public Double getDistance() {
        return distance;
    }

    /**
     * 
     * @param distance
     *     The distance
     */
    public void setDistance(Double distance) {
        this.distance = distance;
    }

    /**
     * 
     * @return
     *     The last
     */
    public Last getLast() {
        return last;
    }

    /**
     * 
     * @param last
     *     The last
     */
    public void setLast(Last last) {
        this.last = last;
    }

}

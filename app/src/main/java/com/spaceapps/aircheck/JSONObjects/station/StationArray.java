package com.spaceapps.aircheck.JSONObjects.station;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manuel on 24/04/2016.
 */
public class StationArray {
    List<Hub> stations = new ArrayList<Hub>();

    public StationArray() {
    }

    public StationArray(List<Hub> stations) {
        this.stations = stations;
    }

    public List<Hub> getStations() {
        return stations;
    }

    public void setStations(List<Hub> stations) {
        this.stations = stations;
    }
}

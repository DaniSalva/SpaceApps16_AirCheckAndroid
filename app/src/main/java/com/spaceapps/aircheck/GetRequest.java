package com.spaceapps.aircheck;

/**
 * Created by Daniel on 24/04/2016.
 */

import java.util.ArrayList;
import java.util.List;

public class GetRequest {

    private String eyes;
    private String nasal;
    private String so2;
    private String mouth;
    private String user;
    private String date;
    private String wheeze;
    private String cough;
    private String sneeze;
    private Double no2;
    private List<Double> loc = new ArrayList<Double>();
    private Integer temp;
    private Double humidity;
    private String breath;
    private Double o3;

    /**
     *
     * @return
     * The eyes
     */
    public String getEyes() {
        return eyes;
    }

    /**
     *
     * @param eyes
     * The eyes
     */
    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    public String getNasal() {
        return nasal;
    }

    /**
     *
     * @param nasal
     * The nasal
     */
    public void setNasal(String nasal) {
        this.nasal = nasal;
    }

    /**
     *
     * @return
     * The so2
     */
    public String getSo2() {
        return so2;
    }

    /**
     *
     * @param so2
     * The so2
     */
    public void setSo2(String so2) {
        this.so2 = so2;
    }

    /**
     *
     * @return
     * The mouth
     */
    public String getMouth() {
        return mouth;
    }

    /**
     *
     * @param mouth
     * The mouth
     */
    public void setMouth(String mouth) {
        this.mouth = mouth;
    }

    /**
     *
     * @return
     * The user
     */
    public String getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The wheeze
     */
    public String getWheeze() {
        return wheeze;
    }

    /**
     *
     * @param wheeze
     * The wheeze
     */
    public void setWheeze(String wheeze) {
        this.wheeze = wheeze;
    }

    /**
     *
     * @return
     * The cough
     */
    public String getCough() {
        return cough;
    }

    /**
     *
     * @param cough
     * The cough
     */
    public void setCough(String cough) {
        this.cough = cough;
    }

    /**
     *
     * @return
     * The sneeze
     */
    public String getSneeze() {
        return sneeze;
    }

    /**
     *
     * @param sneeze
     * The sneeze
     */
    public void setSneeze(String sneeze) {
        this.sneeze = sneeze;
    }

    /**
     *
     * @return
     * The no2
     */
    public Double getNo2() {
        return no2;
    }

    /**
     *
     * @param no2
     * The no2
     */
    public void setNo2(Double no2) {
        this.no2 = no2;
    }

    /**
     *
     * @return
     * The loc
     */
    public List<Double> getLoc() {
        return loc;
    }

    /**
     *
     * @param loc
     * The loc
     */
    public void setLoc(List<Double> loc) {
        this.loc = loc;
    }

    /**
     *
     * @return
     * The temp
     */
    public Integer getTemp() {
        return temp;
    }

    /**
     *
     * @param temp
     * The temp
     */
    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    /**
     *
     * @return
     * The humidity
     */
    public Double getHumidity() {
        return humidity;
    }

    /**
     *
     * @param humidity
     * The humidity
     */
    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    /**
     *
     * @return
     * The breath
     */
    public String getBreath() {
        return breath;
    }

    /**
     *
     * @param breath
     * The breath
     */
    public void setBreath(String breath) {
        this.breath = breath;
    }

    /**
     *
     * @return
     * The o3
     */
    public Double getO3() {
        return o3;
    }

    /**
     *
     * @param o3
     * The o3
     */
    public void setO3(Double o3) {
        this.o3 = o3;
    }

    @Override
    public String toString() {
        return "GetRequest{" +
                "eyes='" + eyes + '\'' +
                ", nasal='" + nasal + '\'' +
                ", so2='" + so2 + '\'' +
                ", mouth='" + mouth + '\'' +
                ", user='" + user + '\'' +
                ", date='" + date + '\'' +
                ", wheeze='" + wheeze + '\'' +
                ", cough='" + cough + '\'' +
                ", sneeze='" + sneeze + '\'' +
                ", no2=" + no2 +
                ", loc=" + loc +
                ", temp=" + temp +
                ", humidity=" + humidity +
                ", breath='" + breath + '\'' +
                ", o3=" + o3 +
                '}';
    }
}
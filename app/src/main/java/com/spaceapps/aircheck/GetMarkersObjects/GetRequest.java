
package com.spaceapps.aircheck.GetMarkersObjects;

public class GetRequest {

    private Loc loc;
    private String eyes;
    private String cough;
    private Integer temp;
    private Double o3;
    private String nasal;
    private Double humidity;
    private String breath;
    private Object so2;
    private String mouth;
    private String user;
    private Date date;
    private String wheeze;
    private Id Id;
    private String sneeze;
    private Double no2;

    /**
     * 
     * @return
     *     The loc
     */
    public Loc getLoc() {
        return loc;
    }

    /**
     * 
     * @param loc
     *     The loc
     */
    public void setLoc(Loc loc) {
        this.loc = loc;
    }

    /**
     * 
     * @return
     *     The eyes
     */
    public String getEyes() {
        return eyes;
    }

    /**
     * 
     * @param eyes
     *     The eyes
     */
    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    /**
     * 
     * @return
     *     The cough
     */
    public String getCough() {
        return cough;
    }

    /**
     * 
     * @param cough
     *     The cough
     */
    public void setCough(String cough) {
        this.cough = cough;
    }

    /**
     * 
     * @return
     *     The temp
     */
    public Integer getTemp() {
        return temp;
    }

    /**
     * 
     * @param temp
     *     The temp
     */
    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    /**
     * 
     * @return
     *     The o3
     */
    public Double getO3() {
        return o3;
    }

    /**
     * 
     * @param o3
     *     The o3
     */
    public void setO3(Double o3) {
        this.o3 = o3;
    }

    /**
     * 
     * @return
     *     The nasal
     */
    public String getNasal() {
        return nasal;
    }

    /**
     * 
     * @param nasal
     *     The nasal
     */
    public void setNasal(String nasal) {
        this.nasal = nasal;
    }

    /**
     * 
     * @return
     *     The humidity
     */
    public Double getHumidity() {
        return humidity;
    }

    /**
     * 
     * @param humidity
     *     The humidity
     */
    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    /**
     * 
     * @return
     *     The breath
     */
    public String getBreath() {
        return breath;
    }

    /**
     * 
     * @param breath
     *     The breath
     */
    public void setBreath(String breath) {
        this.breath = breath;
    }

    /**
     * 
     * @return
     *     The so2
     */
    public Object getSo2() {
        return so2;
    }

    /**
     * 
     * @param so2
     *     The so2
     */
    public void setSo2(Object so2) {
        this.so2 = so2;
    }

    /**
     * 
     * @return
     *     The mouth
     */
    public String getMouth() {
        return mouth;
    }

    /**
     * 
     * @param mouth
     *     The mouth
     */
    public void setMouth(String mouth) {
        this.mouth = mouth;
    }

    /**
     * 
     * @return
     *     The user
     */
    public String getUser() {
        return user;
    }

    /**
     * 
     * @param user
     *     The user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * 
     * @return
     *     The date
     */
    public Date getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 
     * @return
     *     The wheeze
     */
    public String getWheeze() {
        return wheeze;
    }

    /**
     * 
     * @param wheeze
     *     The wheeze
     */
    public void setWheeze(String wheeze) {
        this.wheeze = wheeze;
    }

    /**
     * 
     * @return
     *     The Id
     */
    public Id getId() {
        return Id;
    }

    /**
     * 
     * @param Id
     *     The _id
     */
    public void setId(Id Id) {
        this.Id = Id;
    }

    /**
     * 
     * @return
     *     The sneeze
     */
    public String getSneeze() {
        return sneeze;
    }

    /**
     * 
     * @param sneeze
     *     The sneeze
     */
    public void setSneeze(String sneeze) {
        this.sneeze = sneeze;
    }

    /**
     * 
     * @return
     *     The no2
     */
    public Double getNo2() {
        return no2;
    }

    /**
     * 
     * @param no2
     *     The no2
     */
    public void setNo2(Double no2) {
        this.no2 = no2;
    }

    @Override
    public String toString() {
        return  "Date: " + date + "\n"+
                "Temp: " + temp +
                "  O3:" + o3 + "\n"+
                "SO2:" + so2 +
                "  NO2:" + no2 +
                "Humidity:" + humidity + "\n\n"+
                "Syntoms\n"+
                "*******\n"+
                "Eyes:'" + eyes + '\'' +
                "  Cough:'" + cough + '\n' +
                "Nasal: '" + nasal +
                "  Breath: '" + breath + '\n' +
                "Mouth: '" + mouth +
                "  Wheeze: '" + wheeze + '\n' +
                "Sneeze: '" + sneeze;
    }
}

package com.coronainfo.model;

public class LocationStats {
    private String state;
    private String Country;
    private int latestTotalCase;
    private int difFromPreviousDay;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getLatestTotalCase() {
        return latestTotalCase;
    }

    public void setLatestTotalCase(int latestTotalCase) {
        this.latestTotalCase = latestTotalCase;
    }

    public int getDifFromPreviousDay() {
        return difFromPreviousDay;
    }

    public void setDifFromPreviousDay(int difFromPreviousDay) {
        this.difFromPreviousDay = difFromPreviousDay;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", Country='" + Country + '\'' +
                ", latestTotalCase=" + latestTotalCase +
                ", difFromPreviousDay=" + difFromPreviousDay +
                '}';
    }
}

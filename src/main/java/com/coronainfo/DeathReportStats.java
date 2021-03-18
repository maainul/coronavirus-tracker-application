package com.coronainfo;

public class DeathReportStats {
    private String state;
    private String Country;
    private int latestTotalDeath;
    private int diffDeathFromPreviousDay;

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

    public int getLatestTotalDeath() {
        return latestTotalDeath;
    }

    public void setLatestTotalDeath(int latestTotalDeath) {
        this.latestTotalDeath = latestTotalDeath;
    }

    public int getDiffDeathFromPreviousDay() {
        return diffDeathFromPreviousDay;
    }

    public void setDiffDeathFromPreviousDay(int diffDeathFromPreviousDay) {
        this.diffDeathFromPreviousDay = diffDeathFromPreviousDay;
    }

    @Override
    public String toString() {
        return "DeathReportStats{" +
                "state='" + state + '\'' +
                ", Country='" + Country + '\'' +
                ", latestTotalDeath=" + latestTotalDeath +
                ", diffDeathFromPreviousDay=" + diffDeathFromPreviousDay +
                '}';
    }
}

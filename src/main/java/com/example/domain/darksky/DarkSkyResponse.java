package com.example.domain.darksky;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by bsheen on 8/18/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DarkSkyResponse {
    private Currently currently;
    private Hourly hourly;
    private Daily daily;
    private String timezone;

    public Currently getCurrently() {
        return currently;
    }

    public void setCurrently(Currently currently) {
        this.currently = currently;
    }

    public Hourly getHourly() {
        return hourly;
    }

    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return "DarkSkyResponse{" +
                "currently=" + currently +
                ", hourly=" + hourly +
                ", daily=" + daily +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}

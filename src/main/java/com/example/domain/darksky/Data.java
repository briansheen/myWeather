package com.example.domain.darksky;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by bsheen on 8/18/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    private Long time;
    private String simpleTime;
    private String summary;
    private String icon;
    private Double precipProbability;
    private String precipType;
    private Double temperature;
    private Double apparentTemperature;
    private Double humidity;
    private Double temperatureMin;
    private Double temperatureMax;
    private Double apparentTemperatureMin;
    private Double apparentTemperatureMax;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(Double precipProbability) {
        this.precipProbability = precipProbability;
    }

    public String getPrecipType() {
        return precipType;
    }

    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getApparentTemperature() {
        return apparentTemperature;
    }

    public void setApparentTemperature(Double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public Double getApparentTemperatureMin() {
        return apparentTemperatureMin;
    }

    public void setApparentTemperatureMin(Double apparentTemperatureMin) {
        this.apparentTemperatureMin = apparentTemperatureMin;
    }

    public Double getApparentTemperatureMax() {
        return apparentTemperatureMax;
    }

    public void setApparentTemperatureMax(Double apparentTemperatureMax) {
        this.apparentTemperatureMax = apparentTemperatureMax;
    }

    public String getSimpleTime() {
        return simpleTime;
    }

    public void setSimpleTime(String simpleTime) {
        this.simpleTime = simpleTime;
    }

    @Override
    public String toString() {
        return "Data{" +
                "time=" + time +
                ", simpleTime='" + simpleTime + '\'' +
                ", summary='" + summary + '\'' +
                ", icon='" + icon + '\'' +
                ", precipProbability=" + precipProbability +
                ", precipType='" + precipType + '\'' +
                ", temperature=" + temperature +
                ", apparentTemperature=" + apparentTemperature +
                ", humidity=" + humidity +
                ", temperatureMin=" + temperatureMin +
                ", temperatureMax=" + temperatureMax +
                ", apparentTemperatureMin=" + apparentTemperatureMin +
                ", apparentTemperatureMax=" + apparentTemperatureMax +
                '}';
    }
}

package com.example.domain.geocoding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by bsheen on 8/16/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Results {
    private Formatted_Address formatted_address;
    private Geometry geometry;

    public Formatted_Address getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(Formatted_Address formatted_address) {
        this.formatted_address = formatted_address;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return "Results{" +
                "formatted_address=" + formatted_address +
                ", geometry=" + geometry +
                '}';
    }
}

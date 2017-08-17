package com.example.domain.geocoding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by bsheen on 8/16/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Formatted_Address {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Formatted_Address{" +
                "address='" + address + '\'' +
                '}';
    }
}

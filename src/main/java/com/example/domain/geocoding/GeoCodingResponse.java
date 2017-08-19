package com.example.domain.geocoding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by bsheen on 8/16/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoCodingResponse {
    private List<Results> results;
    private String status;

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GeoCodingResponse{" +
                "results=" + results +
                ", status='" + status + '\'' +
                '}';
    }
}

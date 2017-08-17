package com.example.domain.geocoding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by bsheen on 8/16/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoCodingResponse {
    private List<Results> results;
}

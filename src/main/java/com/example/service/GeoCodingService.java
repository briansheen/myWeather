package com.example.service;

import com.example.domain.geocoding.GeoCodingResponse;
import com.example.domain.geocoding.Results;

/**
 * Created by bsheen on 8/18/17.
 */
public interface GeoCodingService {
    GeoCodingResponse search(String query);
}

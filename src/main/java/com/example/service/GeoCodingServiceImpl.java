package com.example.service;

import com.example.domain.geocoding.GeoCodingResponse;
import com.example.domain.geocoding.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by bsheen on 8/18/17.
 */

@Service
public class GeoCodingServiceImpl implements GeoCodingService {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public GeoCodingResponse search(String query) {
        return restTemplate.getForObject("https://maps.googleapis.com/maps/api/geocode/json?address=" + query + "&key=AIzaSyD1S5rkiZuhlHtDpXlcRo30l46CzEpucVE", GeoCodingResponse.class);
    }

}

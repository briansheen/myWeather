package com.example.service;

import com.example.domain.darksky.DarkSkyResponse;
import com.example.domain.geocoding.GeoCodingResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by bsheen on 8/18/17.
 */

@Service
public class DarkSkyServiceImpl implements DarkSkyService {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public DarkSkyResponse search(Double lat, Double lng) {
        return restTemplate.getForObject("https://api.darksky.net/forecast/ab19dad29f373a6bcf1c1b1bc145cdbb/" + lat + "," + lng, DarkSkyResponse.class);
    }
}

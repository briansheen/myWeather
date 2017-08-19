package com.example.service;

import com.example.domain.darksky.DarkSkyResponse;

/**
 * Created by bsheen on 8/18/17.
 */
public interface DarkSkyService {

    DarkSkyResponse search(Double lat, Double lng);

}

package com.example.controller;

import com.example.domain.geocoding.GeoCodingResponse;
import com.example.service.GeoCodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;

/**
 * Created by bsheen on 8/17/17.
 */

@Controller
public class WeatherController {

    @Autowired
    GeoCodingService geoCodingService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model){
        return "home";
    }

    @PostMapping("/home")
    public String searchResult(Model model, @RequestParam(value = "query", required = true) String query){
        try{
            GeoCodingResponse geoCodingResponse = geoCodingService.search(query);
            model.addAttribute("geoCodingResponse", geoCodingResponse);
        } catch(RestClientException e){
            model.addAttribute("query", query);
            return "searchError";
        }
        model.addAttribute("query", query);
        return "result";
    }

    @GetMapping("/result")
    public String result(Model model){
        return "result";
    }
}

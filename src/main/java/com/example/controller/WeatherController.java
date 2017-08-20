package com.example.controller;

import com.example.domain.Search;
import com.example.domain.TimeConvert;
import com.example.domain.User;
import com.example.domain.darksky.DarkSkyResponse;
import com.example.domain.darksky.Data;
import com.example.domain.geocoding.GeoCodingResponse;
import com.example.domain.geocoding.Location;
import com.example.service.DarkSkyService;
import com.example.service.GeoCodingService;
import com.example.service.SearchService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    DarkSkyService darkSkyService;

    @Autowired
    UserService userService;

    @Autowired
    SearchService searchService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @PostMapping("/result")
    public String searchResult(Model model, @RequestParam(value = "query", required = true) String query) {
        try {
            GeoCodingResponse geoCodingResponse = geoCodingService.search(query);
            if(geoCodingResponse.getStatus().equals("OK")) {
                Location location = geoCodingResponse.getResults().get(0).getGeometry().getLocation();
                model.addAttribute("geoCodingResponse", geoCodingResponse);
                DarkSkyResponse darkSkyResponse = darkSkyService.search(location.getLat(), location.getLng());
                TimeConvert timeConvert = new TimeConvert();
                for(Data data : darkSkyResponse.getHourly().getData()){
                    data.setSimpleTime(timeConvert.getSimpleDate(data.getTime(),darkSkyResponse.getTimezone()));
                }
                for(Data data : darkSkyResponse.getDaily().getData()){
                    data.setSimpleTime(timeConvert.getSimpleDate(data.getTime(),darkSkyResponse.getTimezone()));
                }
                model.addAttribute("darkSkyResponse", darkSkyResponse);
            } else{
                throw new RestClientException("");
            }
        } catch (RestClientException e) {
            model.addAttribute("query", query);
            return "resultError";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.findByUsername(currentPrincipalName);
        Search search = new Search();
        search.setSearchQuery(query);
        search.setUser(user);
        searchService.addSearch(search);
        model.addAttribute("query", query);
        return "result";
    }

    @PostMapping("/resultdate")
    public String searchResultWithDate(Model model, @RequestParam(value="query", required = true) String query, @RequestParam(value="queryDate", required=true) String queryDate){
        try {
            GeoCodingResponse geoCodingResponse = geoCodingService.search(query);
            if(geoCodingResponse.getStatus().equals("OK")) {
                Location location = geoCodingResponse.getResults().get(0).getGeometry().getLocation();
                model.addAttribute("geoCodingResponse", geoCodingResponse);
                TimeConvert timeConvert = new TimeConvert();
                DarkSkyResponse darkSkyResponse = darkSkyService.searchWithDate(location.getLat(), location.getLng(),timeConvert.getEpochTime(queryDate));
                for(Data data : darkSkyResponse.getHourly().getData()){
                    data.setSimpleTime(timeConvert.getSimpleDate(data.getTime(),darkSkyResponse.getTimezone()));
                }
                for(Data data : darkSkyResponse.getDaily().getData()){
                    data.setSimpleTime(timeConvert.getSimpleDate(data.getTime(),darkSkyResponse.getTimezone()));
                }
                model.addAttribute("darkSkyResponse", darkSkyResponse);
            } else{
                throw new RestClientException("");
            }
        } catch (RestClientException e) {
            model.addAttribute("query", query);
            model.addAttribute("queryDate", queryDate);
            return "resultDateError";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.findByUsername(currentPrincipalName);
        Search search = new Search();
        search.setSearchQuery(query);
        search.setUser(user);
        searchService.addSearch(search);
        model.addAttribute("query", query);
        model.addAttribute("queryDate", queryDate);
        return "resultDate";
    }

    @GetMapping("/history")
    public String history(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("searchList", searchService.findSearchesByUser(currentPrincipalName));
        return "history";
    }
}

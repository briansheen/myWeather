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

import java.util.TimeZone;

/**
 * Created by bsheen on 8/17/17.
 */

@Controller
public class WeatherController {

    private static final String[] x = {"12AM", "1AM", "2AM", "3AM", "4AM", "5AM", "6AM", "7AM", "8AM", "9AM", "10AM", "11AM", "12PM", "1PM", "2PM", "3PM", "4PM", "5PM", "6PM", "7PM", "8PM", "9PM", "10PM", "11PM"};

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

    @GetMapping("/result")
    public String redirectToHome(Model model){
        return "redirect:/home";
    }

    @GetMapping("/resultdate")
    public String redirectToHomeDate(Model model){
        return "redirect:/home";
    }

    @PostMapping("/result")
    public String searchResult(Model model, @RequestParam(value = "query", required = true) String query) {
        try {
            GeoCodingResponse geoCodingResponse = geoCodingService.search(query);
            if (geoCodingResponse.getStatus().equals("OK")) {
                Location location = geoCodingResponse.getResults().get(0).getGeometry().getLocation();
                model.addAttribute("geoCodingResponse", geoCodingResponse);
                DarkSkyResponse darkSkyResponse = darkSkyService.search(location.getLat(), location.getLng());
                TimeConvert timeConvert = new TimeConvert();
//                for (Data data : darkSkyResponse.getHourly().getData()) {
//                    data.setSimpleTime(timeConvert.getSimpleDate(data.getTime(), darkSkyResponse.getTimezone()));
//                }
                for (Data data : darkSkyResponse.getDaily().getData()) {
                    data.setSimpleTime(timeConvert.getSimpleDate(data.getTime(), darkSkyResponse.getTimezone()));
                }
                String[] day = new String[darkSkyResponse.getDaily().getData().size()];
                Double[] ylo = new Double[day.length];
                Double[] yhi = new Double[day.length];
                for(int i = 0; i < day.length; ++i){
                    day[i] = timeConvert.getDay(darkSkyResponse.getDaily().getData().get(i).getSimpleTime());
                    ylo[i] = darkSkyResponse.getDaily().getData().get(i).getTemperatureMin();
                    yhi[i] = darkSkyResponse.getDaily().getData().get(i).getTemperatureMax();
                }
                model.addAttribute("day", day);
                model.addAttribute("ylo", ylo);
                model.addAttribute("yhi", yhi);
                model.addAttribute("darkSkyResponse", darkSkyResponse);
            } else {
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
    public String searchResultWithDate(Model model, @RequestParam(value = "query", required = true) String query, @RequestParam(value = "queryDate", required = true) String queryDate) {
        try {
            GeoCodingResponse geoCodingResponse = geoCodingService.search(query);
            if (geoCodingResponse.getStatus().equals("OK")) {
                Location location = geoCodingResponse.getResults().get(0).getGeometry().getLocation();
                model.addAttribute("geoCodingResponse", geoCodingResponse);
                TimeConvert timeConvert = new TimeConvert();
                DarkSkyResponse darkSkyResponse = darkSkyService.search(location.getLat(), location.getLng());
                darkSkyResponse = darkSkyService.searchWithDate(location.getLat(), location.getLng(), timeConvert.getEpochTime(queryDate, darkSkyResponse.getTimezone()));
                for (Data data : darkSkyResponse.getHourly().getData()) {
                    data.setSimpleTime(timeConvert.getSimpleDate(data.getTime(), darkSkyResponse.getTimezone()));
                }
                for (Data data : darkSkyResponse.getDaily().getData()) {
                    data.setSimpleTime(timeConvert.getSimpleDate(data.getTime(), darkSkyResponse.getTimezone()));
                }
                Double[] y = new Double[x.length];
                Double[] yapp = new Double[x.length];
                for (int i = 0; i < x.length; ++i) {
                    y[i] = darkSkyResponse.getHourly().getData().get(i).getTemperature();
                    yapp[i] = darkSkyResponse.getHourly().getData().get(i).getApparentTemperature();
                }
                model.addAttribute("x", x);
                model.addAttribute("y", y);
                model.addAttribute("yapp", yapp);
                model.addAttribute("darkSkyResponse", darkSkyResponse);

            } else {
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

package com.example.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by bsheen on 8/19/17.
 */
public class TimeConvert {

    public String getSimpleDate(long time, String timeZone) {
        Date date = new Date(time * 1000L);
        SimpleDateFormat format = new SimpleDateFormat("EEE, MMM dd, yyyy hh:mm a");
        format.setTimeZone(TimeZone.getTimeZone(timeZone));
        return format.format(date);
    }

    public String getDay(String simpleDate){
            return simpleDate.substring(0,11);
    }

    public Long getEpochTime(String simpleDate, String timeZone) {
        TimeZone tz = TimeZone.getTimeZone(timeZone);
        String timeZoneAbbr = "";
        for(int i = 0; i < tz.getDisplayName().length(); ++i){
            char c = tz.getDisplayName().charAt(i);
            if(Character.isUpperCase(c)){
                timeZoneAbbr+=c;
            }
        }
        simpleDate += (" " + timeZoneAbbr);
        SimpleDateFormat format = new SimpleDateFormat("MMM dd yyyy zzz");
        try {
            Date date = format.parse(simpleDate);
            Long epoch = date.getTime();
            return epoch / (1000L);
        } catch (ParseException e) {
            return null;
        }
    }


}

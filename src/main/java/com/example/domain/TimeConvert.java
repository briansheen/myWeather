package com.example.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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

    public Long getEpochTime(String simpleDate) {
        SimpleDateFormat format = new SimpleDateFormat("MMM dd yyyy");
        try {
            Date date = format.parse(simpleDate);
            Long epoch = date.getTime();
            return epoch / (1000L);
        } catch (ParseException e) {
            return null;
        }
    }


}

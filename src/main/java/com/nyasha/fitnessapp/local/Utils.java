package com.nyasha.fitnessapp.local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class Utils {

    public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalTime convertToLocalTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }

    public static String get5mDashResult(double value) {

        if (value < 1.0)
            return "super";

        if (value < 1.20)
            return "good";

        if (value < 1.35)
            return "average";

        else
            return "poor";
    }

    public static String getSitAndReachTest(double value) {

        if (value > 30)
            return "super";

        if (value > 21)
            return "good";

        if (value > 15)
            return "average";

        if (value > 8)
            return "fair";

        else
            return "poor";
    }
}

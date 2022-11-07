package com.example.clothesshop.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {
    public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }
    public static boolean dateBetween(Date date, Date before, Date after){
        return date.after(before) && date.before(after);
    }
}

package com.giftr.util;

import java.time.LocalDate;

public class DateUtil {
    public static LocalDate getDate(String dateString) {
        LocalDate date;
        if (dateString == null) {
            date = LocalDate.now();
        } else {
            date = LocalDate.parse(dateString);
        }

        return date;
    }

    private DateUtil() {}
}

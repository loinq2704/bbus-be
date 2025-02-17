package com.fpt.bbusbe.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    public static LocalDateTime convertToLocalDateTime(String dateTimeStr) {
        // Define the format of the date-time string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Parse the string into LocalDateTime
        return LocalDateTime.parse(dateTimeStr, formatter);
    }
}


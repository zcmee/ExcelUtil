package com.github.zcmee.excelutil.utils;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class DateOperations {
    private static final DateOperations instance = new DateOperations();

    private DateOperations() {
    }

    public static DateOperations getInstance() {
        return instance;
    }

    public Date getDateFromString(String formatDate, String date) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(formatDate);
        return formatter.parseDateTime(date).toDate();
    }

}

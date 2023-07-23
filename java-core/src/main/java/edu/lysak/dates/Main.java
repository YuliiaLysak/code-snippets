package edu.lysak.dates;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        LocalDateTime date1 = LocalDateTime.parse("2021-05-21T09:57:08.01420Z", DateTimeFormatter.ISO_DATE_TIME);
        ZonedDateTime date2 = ZonedDateTime.parse("2021-05-21T09:57:08.01420Z", DateTimeFormatter.ISO_ZONED_DATE_TIME);
        System.out.println(date1);
        System.out.println(date2);
    }
}

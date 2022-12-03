package com.yxxmg.date;

import java.time.*;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/12/3
 */
@RunWith(JUnit4.class)
public class DateTest {
    @Test
    public void test() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        LocalDate localDate1 = LocalDate.now(Clock.systemDefaultZone());
        System.out.println(localDate1);
        LocalDate localDate2 = LocalDate.now(ZoneId.systemDefault());
        System.out.println(localDate2);
        System.out.println("=======================");
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        LocalTime localTime1 = LocalTime.now(ZoneId.systemDefault());
        System.out.println(localTime1);
        LocalTime localTime2 = LocalTime.now(Clock.systemDefaultZone());
        System.out.println(localTime2);
        System.out.println("=======================");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        System.out.println("DayOfMonth:" + localDateTime.getDayOfMonth());
        System.out.println("DayOfWeek:" + localDateTime.getDayOfWeek());
        System.out.println("DayOfYear:" + localDateTime.getDayOfYear());
        System.out.println("Year:" + localDateTime.getYear());
        System.out.println("Month:" + localDateTime.getMonth());
        System.out.println("Hour:" + localDateTime.getHour());
        System.out.println("Minute:" + localDateTime.getMinute());
        System.out.println("Second:" + localDateTime.getSecond());
        LocalDateTime localDateTime1 = LocalDateTime.now(ZoneId.systemDefault());
        System.out.println(localDateTime1);
        LocalDateTime localDateTime2 = LocalDateTime.now(Clock.systemDefaultZone());
        System.out.println(localDateTime2);
    }

    @Test
    public void testFormat() {
        String strDate = "2022-12-03 21:29:30";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDate = dateTimeFormatter.format(LocalDateTime.now());
        System.out.println(formatDate);
        System.out.println("=======================");

    }
}

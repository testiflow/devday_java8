// $Id$
package ch.iflow.devday201801;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

import com.sun.xml.internal.ws.api.pipe.NextAction;

public class DatesAndTimes {
    public static void main(String[] args) {
        // LocalDate, LocalTime, LocalDateTime
        LocalDate date = LocalDate.now();
        LocalDate devDayDate = LocalDate.of(2018, 1, 18);
        System.out.println("Today: " + date);
        System.out.println("Dev Day: " + devDayDate);
        boolean devDayDone = date.isAfter(devDayDate);
        System.out.println("Dev Day done: " + devDayDone);
        
        LocalDateTime devDayNoon = devDayDate.atTime(12, 00);
        System.out.println("Dev day noon: " + devDayNoon);
        
        int day = date.getDayOfMonth();
        Month month = date.getMonth();
        String germanMonthName = month.getDisplayName(TextStyle.FULL, Locale.GERMANY);
        String frenchMonthName = month.getDisplayName(TextStyle.FULL, Locale.FRANCE);
        System.out.println("Current month (german/french): " + germanMonthName + " / " + frenchMonthName);
        
        // Instant
        Instant instant = Instant.now();
        System.out.println("Instant.now: " + instant);
        
        // Duration and Period
        Period periodTilChristmas = Period.between(date, date.withMonth(12).withDayOfMonth(24));
        System.out.println("Days until christmas: " + periodTilChristmas);
        
        Duration timeSinceBreak = Duration.between(devDayNoon, LocalDateTime.now());
        System.out.println("Time since break: " + timeSinceBreak);
        
        // Manipulations
        LocalDate futureDate = date.plusMonths(2).minusDays(10);
        LocalDate todayIn2020 = date.withYear(2020);
        System.out.println("earlier date: " + futureDate);
        System.out.println("today in 2020: " + todayIn2020);
        
        // TemporalAdjusters
        LocalDate nextSunday = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        LocalDate lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("next sunday: " + nextSunday);
        System.out.println("last day of month: " + lastDayOfMonth);
        
        // format and parse
        System.out.println("Today basic: " + DateTimeFormatter.BASIC_ISO_DATE.format(date));
        DateTimeFormatter germanFormat = DateTimeFormatter.ofPattern("d.MMM yyyy", Locale.GERMAN);
        System.out.println("Today: " + germanFormat.format(date));
        
        // time zones
        ZoneId zurichTime = ZoneId.of("Europe/Zurich");
        ZonedDateTime todayStartInZurich = date.atStartOfDay(zurichTime);
        ZonedDateTime devDayNoonZurich = devDayNoon.atZone(zurichTime);
        System.out.println("today start in zurich: " + todayStartInZurich);
    }
}

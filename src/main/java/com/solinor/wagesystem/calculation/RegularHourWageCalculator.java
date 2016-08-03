package com.solinor.wagesystem.calculation;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by yolan
 */
public class RegularHourWageCalculator {

    public static final int MINUTES_IN_HOUR = 60;

    private LocalTime regularHourStart;
    private LocalTime regularHourEnd;
    private BigDecimal regularHourlyWage;

    public RegularHourWageCalculator(int startHour, int startMinute, int endHour, int endMinute, String hourlyWage) {
        regularHourStart = LocalTime.of(startHour, startMinute);
        regularHourEnd = LocalTime.of(endHour, endMinute);
        regularHourlyWage = new BigDecimal(hourlyWage);
    }

    public BigDecimal calculate(LocalDateTime start, LocalDateTime end) {


        LocalTime regularTimeStart = findStartTime(start);
        LocalTime regularTimeEnd = findEndTime(start, end);


        BigDecimal regularHoursWage = calculateRegularHoursWage(regularTimeStart, regularTimeEnd);
        return regularHoursWage;
    }

    private BigDecimal calculateRegularHoursWage(LocalTime regularTimeStart, LocalTime regularTimeEnd) {

        Duration d = Duration.between(regularTimeStart, regularTimeEnd);

        BigDecimal regularHourWage;

        long regularMinutes = d.toMinutes();
        if (0 != regularMinutes) {
            BigDecimal regularHours = new BigDecimal(regularMinutes /  MINUTES_IN_HOUR);
            regularHourWage = regularHourlyWage.multiply(regularHours);

        } else {
            regularHourWage = BigDecimal.ZERO;
        }
        return regularHourWage;
    }

    private LocalTime findEndTime(LocalDateTime start, LocalDateTime end) {
        LocalTime regularTimeEnd;
        if (end.isBefore(LocalDateTime.of(start.toLocalDate(), regularHourEnd))) {
            regularTimeEnd = end.toLocalTime();
        } else {
            regularTimeEnd = regularHourEnd;
        }
        return regularTimeEnd;
    }

    private LocalTime findStartTime(LocalDateTime start) {
        LocalTime regularTimeStart;
        if (start.isAfter(LocalDateTime.of(start.toLocalDate(), regularHourStart))) {
            regularTimeStart = start.toLocalTime();
        } else {
            regularTimeStart = regularHourStart;
        }
        return regularTimeStart;
    }
}



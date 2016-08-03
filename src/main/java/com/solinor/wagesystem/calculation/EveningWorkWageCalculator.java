package com.solinor.wagesystem.calculation;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by yolan
 */
public class EveningWorkWageCalculator {


    private LocalTime regularHourStart;
    private LocalTime regularHourEnd;
    private BigDecimal regularHourlyWage;
    private BigDecimal eveningCompensation;

    public EveningWorkWageCalculator(int startHour, int startMinute, int endHour, int endMinute, String regularHourlyWage,String eveningCompensation) {
        regularHourStart = LocalTime.of(startHour, startMinute);
        regularHourEnd = LocalTime.of(endHour, endMinute);
        this.regularHourlyWage = new BigDecimal(regularHourlyWage);
        this.eveningCompensation = new BigDecimal(eveningCompensation);
    }
    
    public BigDecimal calculate(LocalDateTime start, LocalDateTime end) {
        //todo can be refactored to use one method
        BigDecimal eveningHours = BigDecimal.ZERO;
        LocalDateTime regularStart = LocalDateTime.of(start.toLocalDate(), regularHourStart);
        if (start.isBefore(regularStart)) {
            Duration d = Duration.between(start, regularStart);
            long earlyMinutes = d.toMinutes();
            if (earlyMinutes != 0) {
                BigDecimal earlyHours = new BigDecimal(earlyMinutes / 60);
                eveningHours = eveningHours.add(earlyHours);
            }
        }

        LocalDateTime regularEnd = LocalDateTime.of(start.toLocalDate(), regularHourEnd);
        if (end.isAfter(regularEnd)) {
            Duration d = Duration.between(regularEnd, end);
            long lateMinutes = d.toMinutes();
            if (lateMinutes != 0) {
                BigDecimal lateHours = new BigDecimal(lateMinutes / 60);
                eveningHours = eveningHours.add(lateHours);
            }
        }
        BigDecimal eveningHoursWage = regularHourlyWage.add(eveningCompensation);
        return eveningHours.multiply(eveningHoursWage);
    }
}

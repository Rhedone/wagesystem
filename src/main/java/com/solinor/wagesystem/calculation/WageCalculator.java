package com.solinor.wagesystem.calculation;

import com.solinor.wagesystem.model.CalculatedWage;
import com.solinor.wagesystem.model.OverHourCompensation;
import com.solinor.wagesystem.model.WageEntry;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yolan
 */
@Component
public class WageCalculator {
    public static final int MINUTES_IN_HOUR = 60;
    public static final int REGULAR_WORK_HOURS = 8;
    private final BigDecimal REGULAR_HOURLY_WAGE = new BigDecimal("3.75");
    private final BigDecimal EVENING_COMPENSATION = new BigDecimal("1.15");
    private final LocalTime REGULAR_HOUR_START = LocalTime.of(6, 0);
    private final LocalTime REGULAR_HOUR_END = LocalTime.of(18, 0);
    private BigDecimal overHoursRemaining;
    //todo assumption only round at end of calc

    public Map<Integer, CalculatedWage> calculate(List<WageEntry> entries) {
        Map<Integer, CalculatedWage> totalPerEmployee = new HashMap<Integer, CalculatedWage>();
        for (WageEntry we : entries) {
            calculateDailyWage(totalPerEmployee, we);
        }
        return totalPerEmployee;
    }

    private void calculateDailyWage(Map<Integer, CalculatedWage> totalPerEmployee, WageEntry we) {
        BigDecimal dailyTotal = calculate(we);

        if (totalPerEmployee.containsKey(we.getId())) {
            addToWage(totalPerEmployee, we, dailyTotal);
        } else {
            makeNewEmployee(totalPerEmployee, we, dailyTotal);
        }
    }

    private void makeNewEmployee(Map<Integer, CalculatedWage> totalPerEmployee, WageEntry we, BigDecimal dailyTotal) {
        CalculatedWage cw = new CalculatedWage(we.getId(), we.getName(), dailyTotal);
        totalPerEmployee.put(we.getId(), cw);
    }

    private void addToWage(Map<Integer, CalculatedWage> totalPerEmployee, WageEntry we, BigDecimal dailyTotal) {
        CalculatedWage cw = totalPerEmployee.get(we.getId());
        cw.add(dailyTotal);
    }

    private BigDecimal calculate(WageEntry entry) {
        LocalDateTime start = entry.getStart();
        LocalDateTime end = entry.getEnd();

        BigDecimal regularHourWage = calculateRegularDailyWage(start, end);
        BigDecimal lateHourWage = calculateEveningWorkCompensation(start, end);
        BigDecimal overTimeCompensation = calculateOverTimeCompensation(start, end);

        BigDecimal total = calculateDailyTotal(regularHourWage, lateHourWage, overTimeCompensation);
        return total;
    }

    private BigDecimal calculateDailyTotal(BigDecimal regularHourWage, BigDecimal lateHourWage, BigDecimal overTimeCompensation) {
        return regularHourWage.add(lateHourWage.add(overTimeCompensation)).round(new MathContext(4, RoundingMode.HALF_UP));
    }


    private BigDecimal calculateRegularDailyWage(LocalDateTime start, LocalDateTime end) {
        //todo does not take minutes into account
        //todo way too long make private class
        LocalTime regularTimeStart;
        LocalTime regularTimeEnd;

        if (start.isAfter(LocalDateTime.of(start.toLocalDate(),REGULAR_HOUR_START))) {
            regularTimeStart = start.toLocalTime();
        } else {
            regularTimeStart = REGULAR_HOUR_START;
        }
        if (end.isBefore(LocalDateTime.of(start.toLocalDate(),REGULAR_HOUR_END))) {
            regularTimeEnd = end.toLocalTime();
        } else {
            regularTimeEnd = REGULAR_HOUR_END;
        }
        Duration d = Duration.between(regularTimeStart, regularTimeEnd);
        BigDecimal regularHourCompensation;
        long regularMinutes = d.toMinutes();
        if (regularMinutes != 0) {
            BigDecimal regularHours = new BigDecimal(regularMinutes / 60);
            regularHourCompensation = (REGULAR_HOURLY_WAGE.multiply(regularHours));

        } else {
            regularHourCompensation = BigDecimal.ZERO;
        }
        return regularHourCompensation;
    }

    private BigDecimal calculateEveningWorkCompensation(LocalDateTime start, LocalDateTime end) {
        //todo can be refactored to use one method
        BigDecimal eveningHours = BigDecimal.ZERO;
        LocalDateTime regularStart = LocalDateTime.of(start.toLocalDate(), REGULAR_HOUR_START);
        if (start.isBefore(regularStart)) {
            Duration d = Duration.between(start, regularStart);
            long earlyMinutes = d.toMinutes();
            if (earlyMinutes != 0) {
                BigDecimal earlyHours = new BigDecimal(earlyMinutes / 60);
                eveningHours = eveningHours.add(earlyHours);
            }
        }
        LocalDateTime regularEnd = LocalDateTime.of(start.toLocalDate(), REGULAR_HOUR_END);
        if (end.isAfter(regularEnd)) {
            Duration d = Duration.between(regularEnd, end);
            long lateMinutes = d.toMinutes();
            if (lateMinutes != 0) {
                BigDecimal lateHours = new BigDecimal(lateMinutes / 60);
                eveningHours = eveningHours.add(lateHours);
            }
        }
        BigDecimal eveningHoursWage = REGULAR_HOURLY_WAGE.add(EVENING_COMPENSATION);
        return eveningHours.multiply(eveningHoursWage);

    }

    private BigDecimal calculateOverTimeCompensation(LocalDateTime start, LocalDateTime end) {
        Duration d = Duration.between(start, end);
        long overHourSeconds = d.toMinutes();
        BigDecimal hoursWorked = new BigDecimal((overHourSeconds / MINUTES_IN_HOUR) - REGULAR_WORK_HOURS);

        List<OverHourCompensation> overHourCompensations = OverHourCompensationFactory.makeOverHourCompensations();
        int i = 0;
        BigDecimal overHoursCompensation = BigDecimal.ZERO;
        overHoursRemaining =hoursWorked;
        BigDecimal totalOverHourCompensation = calculateRemainingOverHourCompensation( overHourCompensations, overHoursCompensation, i);
        return totalOverHourCompensation;
    }

    private BigDecimal calculateRemainingOverHourCompensation(List<OverHourCompensation> overHourCompensations, BigDecimal overHoursCompensation, int i) {
        OverHourCompensation overHourCompensation = overHourCompensations.get(i);
        if (overHoursRemaining.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal hoursInSlot = overHourCompensation.getHours();
            if (overHoursRemaining.compareTo(hoursInSlot) > 0) {
                overHoursRemaining =overHoursRemaining.subtract(hoursInSlot);
                overHoursCompensation = calculateRemainingOverHourCompensation(overHourCompensations, overHoursCompensation, i+1);
            }
            BigDecimal hourlyCompensation = REGULAR_HOURLY_WAGE.multiply(overHourCompensation.getCompensationPercentage());
            BigDecimal compensationForHourSlot = overHoursRemaining.multiply(hourlyCompensation);
            overHoursCompensation = overHoursCompensation.add(compensationForHourSlot);

        }
        return overHoursCompensation;
    }

}

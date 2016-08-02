package com.solinor.wagesystem.calculation;

import com.solinor.wagesystem.model.CalculatedWage;
import com.solinor.wagesystem.model.WageEntry;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
    private final BigDecimal REGULAR_HOURLY_WAGE = new BigDecimal("3.75");
    private final BigDecimal EVENING_COMPENSATION = new BigDecimal("1.15");
    private final LocalTime REGULAR_HOUR_START = LocalTime.of(6, 0);
    private final LocalTime REGULAR_HOUR_END = LocalTime.of(18, 0);
    //todo assumption only round at end of calc

    public Map<Integer, CalculatedWage> calculate(List<WageEntry> entries) {
        Map<Integer, CalculatedWage> totalPerEmployee = new HashMap<Integer, CalculatedWage>();
        for (WageEntry we : entries) {
            BigDecimal dailyTotal = calculate(we);
            if (totalPerEmployee.containsKey(we.getId())) {
                CalculatedWage cw = totalPerEmployee.get(we.getId());
                cw.add(dailyTotal);
            } else {
                CalculatedWage cw= new CalculatedWage(we.getId(),we.getName(),dailyTotal);
                totalPerEmployee.put(we.getId(), cw);
            }
        }
        return totalPerEmployee;
    }

    private BigDecimal calculate(WageEntry entry) {
        LocalDateTime start = entry.getStart();
        LocalDateTime end = entry.getEnd();

        BigDecimal regularHourWage = calculateRegularDailyWage(start, end);
        BigDecimal lateHourWage = calculateEveningWorkCompensation(start, end);
        calculateOverTimeCompensation(start, end);

        BigDecimal total = calculateDailyTotal(regularHourWage, lateHourWage);
        return total;
    }

    private BigDecimal calculateDailyTotal(BigDecimal regularHourWage, BigDecimal lateHourWage) {
        return regularHourWage.add(lateHourWage);
    }


    private BigDecimal calculateRegularDailyWage(LocalDateTime start, LocalDateTime end) {
        //todo does not take minutes into account
        LocalTime regularTimeStart;
        LocalTime regularTimeEnd;
        if (start.getHour() > REGULAR_HOUR_START.getHour()) {
            regularTimeStart = start.toLocalTime();
        } else {
            regularTimeStart = REGULAR_HOUR_START;
        }
        if (end.getHour() < REGULAR_HOUR_END.getHour()) {
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
        BigDecimal eveningHours = BigDecimal.ZERO;
        if (start.toLocalTime().getHour() < REGULAR_HOUR_START.getHour()) {
            Duration d = Duration.between(start.toLocalTime(), REGULAR_HOUR_START);
            long earlyMinutes = d.toMinutes();
            if (earlyMinutes != 0) {
                BigDecimal earlyHours = new BigDecimal(earlyMinutes / 60);
                eveningHours = eveningHours.add(earlyHours);
            }
        }
        if (end.toLocalTime().getHour() > REGULAR_HOUR_END.getHour()) {
            Duration d = Duration.between(REGULAR_HOUR_END, end.toLocalTime());
            long lateMinutes = d.toMinutes();
            if (lateMinutes != 0) {
                BigDecimal lateHours = new BigDecimal(lateMinutes / 60);
                eveningHours = eveningHours.add(lateHours);
            }
        }
        BigDecimal eveningHoursWage = REGULAR_HOURLY_WAGE.add(EVENING_COMPENSATION);
        return eveningHours.multiply(eveningHoursWage);

    }

    private void calculateOverTimeCompensation(LocalDateTime start, LocalDateTime end) {
        Duration d = Duration.between(start, end);
        long l = d.toMinutes();
        BigDecimal hoursWorked = new BigDecimal(l / 60);

//        if(hoursWorked.subtract(new BigDecimal(""))>0)

    }

}

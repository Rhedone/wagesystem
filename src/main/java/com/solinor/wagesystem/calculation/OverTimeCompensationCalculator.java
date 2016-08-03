package com.solinor.wagesystem.calculation;

import com.solinor.wagesystem.model.OverHourCompensation;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by yolan
 */
public class OverTimeCompensationCalculator {
    public static final int MINUTES_IN_HOUR = 60;

    public int regularWorkHours;
    private BigDecimal regularHourlyWage;
    private BigDecimal overHoursRemaining;

    public OverTimeCompensationCalculator(int regularWorkHours, String regularHourlyWage) {
        this.regularWorkHours = regularWorkHours;
        this.regularHourlyWage = new BigDecimal(regularHourlyWage);
    }

    public BigDecimal calculate(LocalDateTime start, LocalDateTime end) {

        Duration d = Duration.between(start, end);
        long overHourSeconds = d.toMinutes();
        BigDecimal hoursWorked = new BigDecimal((overHourSeconds / MINUTES_IN_HOUR) - regularWorkHours);

        List<OverHourCompensation> overHourCompensations = OverHourCompensationFactory.makeOverHourCompensations();
        int i = 0;
        BigDecimal overHoursCompensation = BigDecimal.ZERO;
        overHoursRemaining = hoursWorked;
        BigDecimal totalOverHourCompensation = calculateRemainingOverHourCompensation(overHourCompensations, overHoursCompensation, i);
        return totalOverHourCompensation;
    }

    private BigDecimal calculateRemainingOverHourCompensation(List<OverHourCompensation> overHourCompensations, BigDecimal overHoursCompensation, int i) {
        OverHourCompensation overHourCompensation = overHourCompensations.get(i);
        if (overHoursRemaining.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal hoursInSlot = overHourCompensation.getHours();
            if (overHoursRemaining.compareTo(hoursInSlot) > 0) {
                overHoursRemaining = overHoursRemaining.subtract(hoursInSlot);
                overHoursCompensation = calculateRemainingOverHourCompensation(overHourCompensations, overHoursCompensation, i + 1);
            }
            BigDecimal hourlyCompensation = regularHourlyWage.multiply(overHourCompensation.getCompensationPercentage());
            BigDecimal compensationForHourSlot = overHoursRemaining.multiply(hourlyCompensation);
            overHoursCompensation = overHoursCompensation.add(compensationForHourSlot);

        }
        return overHoursCompensation;
    }

}

package com.solinor.wagesystem.calculation;

import com.solinor.wagesystem.model.CalculatedWage;
import com.solinor.wagesystem.model.OverHourCompensation;
import com.solinor.wagesystem.model.WageEntry;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
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
public class TotalWageCalculator {
    @Inject
    private RegularHourWageCalculator regularHourWageCalculator;

    @Inject
    private EveningWorkWageCalculator eveningWorkWageCalculator;

    @Inject
    OverTimeCompensationCalculator overTimeCompensationCalculator;

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

        BigDecimal regularHourWage = regularHourWageCalculator.calculate(start, end);
        BigDecimal lateHourWage = eveningWorkWageCalculator.calculate(start, end);
        BigDecimal overTimeCompensation =overTimeCompensationCalculator.calculate(start, end);

        BigDecimal total = calculateDailyTotal(regularHourWage, lateHourWage, overTimeCompensation);
        return total;
    }

    private BigDecimal calculateDailyTotal(BigDecimal regularHourWage, BigDecimal lateHourWage, BigDecimal overTimeCompensation) {
        return regularHourWage.add(lateHourWage.add(overTimeCompensation)).round(new MathContext(4, RoundingMode.HALF_UP));
    }

}

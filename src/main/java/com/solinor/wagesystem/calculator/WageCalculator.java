package com.solinor.wagesystem.calculator;

import com.solinor.wagesystem.model.WageEntry;
import org.springframework.stereotype.Component;

import javax.xml.datatype.Duration;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by yolan
 */
@Component
public class WageCalculator {
    private final BigDecimal HOURLY_WAGE = new BigDecimal("3.75");
    private final BigDecimal OVERHOUR_COMPENSATION = new BigDecimal("1.15");
    private final BigDecimal OVERTIME_COMPENSATION_FIRST_TWO_HOURS = new BigDecimal("1.25");
    private final BigDecimal OVERTIME_COMPENSATION_TWO_TO_FOUR_HOURS = new BigDecimal("1.50");
    private final BigDecimal OVERTIME_COMPENSATION_OVER_FOUR_HOURS = new BigDecimal("2.00");

    public void calculate(List<WageEntry> entries) {
        for (WageEntry we : entries) {
            calculate(we);
        }
    }

    private void calculate(WageEntry entry) {
        LocalDateTime start = entry.getStart();
        LocalDateTime end = entry.getEnd();

        calculateRegularDailyWage(start,end);
        calculateEveningWorkCompensation();
        calculateOverTimeCompensation();
    }


    private void calculateRegularDailyWage(LocalDateTime start, LocalDateTime end) {
        
    }

    private void calculateEveningWorkCompensation() {

    }
    private void calculateOverTimeCompensation() {

    }

}

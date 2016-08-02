package com.solinor.wagesystem.model;

import java.math.BigDecimal;

/**
 * Created by yolan
 */
public class OverHourCompensation {
    private BigDecimal hours;
    private BigDecimal compensationPercentage;

    public OverHourCompensation(BigDecimal hours, BigDecimal compensationPercentage) {
        this.hours = hours;
        this.compensationPercentage = compensationPercentage;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public BigDecimal getCompensationPercentage() {
        return compensationPercentage;
    }
}

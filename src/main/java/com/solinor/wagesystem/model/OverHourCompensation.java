package com.solinor.wagesystem.model;

import java.math.BigDecimal;

/**
 * Created by yolan
 */
public class OverHourCompensation {
    private int hours;
    private BigDecimal compensationPercentage;

    public OverHourCompensation(int hours, BigDecimal compensationPercentage) {
        this.hours = hours;
        this.compensationPercentage = compensationPercentage;
    }

    public int getHours() {
        return hours;
    }

    public BigDecimal getCompensationPercentage() {
        return compensationPercentage;
    }
}

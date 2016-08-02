package com.solinor.wagesystem.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by yolan
 */
public class CalculatedWage {
    private int id;
    private String name;
    private BigDecimal wage;

    public CalculatedWage(int id, String name, BigDecimal wage) {
        this.id = id;
        this.name = name;
        this.wage = wage;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getWage() {
        return wage;
    }

    public void add(BigDecimal dailyTotal) {
        wage = wage.add(dailyTotal);
    }
}

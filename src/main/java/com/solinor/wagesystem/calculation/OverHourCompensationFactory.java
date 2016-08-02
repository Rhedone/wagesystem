package com.solinor.wagesystem.calculation;

import com.solinor.wagesystem.model.OverHourCompensation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yolan
 */
public class OverHourCompensationFactory {

    private OverHourCompensationFactory() {
    }

    public static List<OverHourCompensation> makeOverHourCompensations() {
        List<OverHourCompensation> overHourCompensations = new ArrayList<OverHourCompensation>();
        overHourCompensations.add(new OverHourCompensation(new BigDecimal("2"), new BigDecimal("0.25")));
        overHourCompensations.add(new OverHourCompensation(new BigDecimal("2"), new BigDecimal("0.50")));
        overHourCompensations.add(new OverHourCompensation(new BigDecimal(Integer.MAX_VALUE), new BigDecimal("1.00")));
        return overHourCompensations;
    }
}

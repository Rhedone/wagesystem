package com.solinor.wagesystem.calculation;

import com.solinor.wagesystem.model.CalculatedWage;
import com.solinor.wagesystem.model.WageEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by yolan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context.xml")
public class TotalWageCalculatorTest {

    @Inject
    private TotalWageCalculator calculator;

    @Test
    public void testWageCalculatorRegularHoursOneEntry() {
        List<WageEntry> entries = new ArrayList<WageEntry>();
        WageEntry e = new WageEntry("Scott Scala", 2, LocalDateTime.of(2014, 3, 2, 6, 0), LocalDateTime.of(2014, 3, 2, 14, 0));
        entries.add(e);

        Map<Integer, CalculatedWage> totalPerEmployee = calculator.calculate(entries);
        assertTrue(totalPerEmployee.containsKey(2));
        assertEquals(new BigDecimal("30.00"), totalPerEmployee.get(2).getWage());
    }

    @Test
    public void testWageCalculatorRegularHoursTwoEntries() {
        List<WageEntry> entries = new ArrayList<WageEntry>();
        WageEntry e = new WageEntry("Scott Scala", 2, LocalDateTime.of(2014, 3, 2, 6, 0), LocalDateTime.of(2014, 3, 2, 14, 0));
        WageEntry e1 = new WageEntry("Charlie CSharp", 3, LocalDateTime.of(2014, 3, 2, 6, 0), LocalDateTime.of(2014, 3, 2, 13, 0));
        entries.add(e);
        entries.add(e1);

         Map<Integer, CalculatedWage> totalPerEmployee = calculator.calculate(entries);
         assertTrue(totalPerEmployee.containsKey(2));
        assertEquals(new BigDecimal("30.00"), totalPerEmployee.get(2).getWage());
        assertTrue(totalPerEmployee.containsKey(3));
        assertEquals(new BigDecimal("26.25"), totalPerEmployee.get(3).getWage());
    }

    @Test
    public void testWageCalculatorRegularHoursOneEntryWithMinutes() {
        List<WageEntry> entries = new ArrayList<WageEntry>();
        WageEntry e = new WageEntry("Scott Scala", 2, LocalDateTime.of(2014, 3, 2, 6, 30), LocalDateTime.of(2014, 3, 2, 14, 30));
        entries.add(e);

         Map<Integer, CalculatedWage> totalPerEmployee = calculator.calculate(entries);
         assertTrue(totalPerEmployee.containsKey(2));
        assertEquals(new BigDecimal("30.00"), totalPerEmployee.get(2).getWage());
    }

    @Test
    public void testWageCalculatorEveningHoursOnePersonEarly() {
        List<WageEntry> entries = new ArrayList<WageEntry>();
        WageEntry e = new WageEntry("Scott Scala", 2, LocalDateTime.of(2014, 3, 2, 5, 0), LocalDateTime.of(2014, 3, 2, 13, 0));
        entries.add(e);

         Map<Integer, CalculatedWage> totalPerEmployee = calculator.calculate(entries);
         assertTrue(totalPerEmployee.containsKey(2));
        assertEquals(new BigDecimal("31.15"), totalPerEmployee.get(2).getWage());
    }

    @Test
    public void testWageCalculatorEveningHoursOnePersonLate() {
        List<WageEntry> entries = new ArrayList<WageEntry>();
        WageEntry e = new WageEntry("Scott Scala", 2, LocalDateTime.of(2014, 3, 2, 11, 0), LocalDateTime.of(2014, 3, 2, 19, 0));
        entries.add(e);

         Map<Integer, CalculatedWage> totalPerEmployee = calculator.calculate(entries);
         assertTrue(totalPerEmployee.containsKey(2));
        assertEquals(new BigDecimal("31.15"), totalPerEmployee.get(2).getWage());
    }

    @Test
    public void testWageCalculatorOverHoursOnePersonTenHours() {
        List<WageEntry> entries = new ArrayList<WageEntry>();
        WageEntry e = new WageEntry("Scott Scala", 2, LocalDateTime.of(2014, 3, 2, 10, 0), LocalDateTime.of(2014, 3, 2, 20, 0));
        entries.add(e);

         Map<Integer, CalculatedWage> totalPerEmployee = calculator.calculate(entries);
         assertTrue(totalPerEmployee.containsKey(2));
        assertEquals(new BigDecimal("41.68"), totalPerEmployee.get(2).getWage());
    }

    @Test
    public void testWageCalculatorOverHoursOnePersonTwelveHours() {
        List<WageEntry> entries = new ArrayList<WageEntry>();
        WageEntry e = new WageEntry("Scott Scala", 2, LocalDateTime.of(2014, 3, 2, 10, 0), LocalDateTime.of(2014, 3, 2, 22, 0));
        entries.add(e);

         Map<Integer, CalculatedWage> totalPerEmployee = calculator.calculate(entries);
         assertTrue(totalPerEmployee.containsKey(2));
        assertEquals(new BigDecimal("55.23"), totalPerEmployee.get(2).getWage());
    }

    @Test
    public void testWageCalculatorOverHoursOnePersonFourteenHours() {
        List<WageEntry> entries = new ArrayList<WageEntry>();
        WageEntry e = new WageEntry("Scott Scala", 2, LocalDateTime.of(2014, 3, 2, 10, 0), LocalDateTime.of(2014, 3, 3, 0, 0));
        entries.add(e);

         Map<Integer, CalculatedWage> totalPerEmployee = calculator.calculate(entries);
         assertTrue(totalPerEmployee.containsKey(2));
        assertEquals(new BigDecimal("72.53"), totalPerEmployee.get(2).getWage());
    }

    @Test
    public void testWageCalculatorOverHoursOnePersonTwentyHours() {
        List<WageEntry> entries = new ArrayList<WageEntry>();
        WageEntry e = new WageEntry("Scott Scala", 2, LocalDateTime.of(2014, 3, 2, 10, 0), LocalDateTime.of(2014, 3, 3, 10, 0));
        entries.add(e);

         Map<Integer, CalculatedWage> totalPerEmployee = calculator.calculate(entries);
         assertTrue(totalPerEmployee.containsKey(2));
        assertEquals(new BigDecimal("187.2"), totalPerEmployee.get(2).getWage());
    }
}

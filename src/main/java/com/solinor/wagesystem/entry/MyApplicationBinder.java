package com.solinor.wagesystem.entry;

import com.solinor.wagesystem.calculation.EveningWorkWageCalculator;
import com.solinor.wagesystem.calculation.OverTimeCompensationCalculator;
import com.solinor.wagesystem.calculation.RegularHourWageCalculator;
import com.solinor.wagesystem.calculation.TotalWageCalculator;
import com.solinor.wagesystem.handler.Handler;
import com.solinor.wagesystem.transformation.CsvToWageEntryTransformer;
import com.solinor.wagesystem.transformation.Transformer;
import com.solinor.wagesystem.transformation.WagesToJsonTransformer;
import com.solinor.wagesystem.validation.CsvInputValidator;
import com.solinor.wagesystem.validation.InputValidator;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class MyApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(Handler.class).to(Handler.class);
        bind(CsvInputValidator.class).to(InputValidator.class);
        bind(CsvToWageEntryTransformer.class).to(Transformer.class);
        bind(WagesToJsonTransformer.class).to(WagesToJsonTransformer.class);
        bind(RegularHourWageCalculator.class).to(RegularHourWageCalculator.class);
        bind(new RegularHourWageCalculator(6,0,18,0,"3.75")).to(RegularHourWageCalculator.class);
        bind(EveningWorkWageCalculator.class).to(EveningWorkWageCalculator.class);
        bind(new EveningWorkWageCalculator(6,0,18,0,"3.75","1.15")).to(EveningWorkWageCalculator.class);
        bind(OverTimeCompensationCalculator.class).to(OverTimeCompensationCalculator.class);
        bind(new OverTimeCompensationCalculator(8,"3.75")).to(OverTimeCompensationCalculator.class);
        bind(TotalWageCalculator.class).to(TotalWageCalculator.class);
    }
}
package com.solinor.wagesystem.entry;

import com.solinor.wagesystem.calculation.WageCalculator;
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
        bind(WageCalculator.class).to(WageCalculator.class);
        bind(WagesToJsonTransformer.class).to(WagesToJsonTransformer.class);
    }
}
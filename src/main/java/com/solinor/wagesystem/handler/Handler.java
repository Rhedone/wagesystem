package com.solinor.wagesystem.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.solinor.wagesystem.calculation.TotalWageCalculator;
import com.solinor.wagesystem.model.CalculatedWage;
import com.solinor.wagesystem.model.InputWrapper;
import com.solinor.wagesystem.model.WageEntry;
import com.solinor.wagesystem.transformation.Transformer;
import com.solinor.wagesystem.transformation.WagesToJsonTransformer;
import com.solinor.wagesystem.validation.InputValidationException;
import com.solinor.wagesystem.validation.InputValidator;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import java.util.List;
import java.util.Map;

/**
 * Created by yolan
 */
@Component
public class Handler {
    final static Logger logger = Logger.getLogger(Handler.class);

    @Inject
    private InputValidator validator;

    @Inject
    private Transformer transformer;

    @Inject
    private TotalWageCalculator calulator;

    @Inject
    private WagesToJsonTransformer toJsonTransformer;

    public String handle(InputWrapper input) throws InputValidationException, JsonProcessingException {
        validator.validate(input);
        List<WageEntry> entries = transformer.transform(input);
        Map<Integer, CalculatedWage> wages = calulator.calculate(entries);
        String returnVal = toJsonTransformer.transform(wages);
        return returnVal;
    }

}

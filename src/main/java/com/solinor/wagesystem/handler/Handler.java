package com.solinor.wagesystem.handler;

import com.solinor.wagesystem.calculator.WageCalculator;
import com.solinor.wagesystem.model.InputWrapper;
import com.solinor.wagesystem.model.WageEntry;
import com.solinor.wagesystem.transformation.Transformer;
import com.solinor.wagesystem.validation.InputValidationException;
import com.solinor.wagesystem.validation.InputValidator;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import java.util.List;

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
    private WageCalculator calulator;

    public String handle(InputWrapper input) throws InputValidationException {
        validator.validate(input);
        List<WageEntry> entries = transformer.transform(input);
        //todo sanity check differs from validate cause it contains business logic not just check whether its a valid csv
        //like are all vals from same month and are is start before end date, etc
        calulator.calculate(entries);

        return "derp";
    }

}

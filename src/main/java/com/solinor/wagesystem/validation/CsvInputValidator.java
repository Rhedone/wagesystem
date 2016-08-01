package com.solinor.wagesystem.validation;

import com.solinor.wagesystem.model.InputWrapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by yolan
 */

@Component
public class CsvInputValidator implements InputValidator {
    //todo make spring properties
    private String CVS_REGEX_TOTAL = "[A-Za-z0-9.,:\\s]*";
    private String CVS_REGEX_FIRST_LINE = "[A-Za-z,\\s]*";
    //detailed layout of how the body should look, each seperate value
    //private String CVS_REGEX_TOTAL_BODY = "";

    final static Logger logger = Logger.getLogger(CsvInputValidator.class);

    public void validate(InputWrapper input) throws InputValidationException {
        validateNotEmpty(input);
        validateWhole(input);
        validateFirstLine(input);
        validateBody(input);
    }

    private void validateNotEmpty(InputWrapper input) throws InputValidationException {

        if (input.isEmpty()) {
            throw new InputValidationException("The input data was empty");
        }
    }

    private void validateBody(InputWrapper input) throws InputValidationException {
        //TODO check for too many commas and equal size
//        String[] split = splitInput(input);
//        for (int i = 1; i < split.length; i++) {
//            if (!split[i].matches(CVS_REGEX_TOTAL_BODY)) {
//                throw new InputValidationException("The input data contain illegal characters");
//            }
//        }
    }

    private void validateFirstLine(InputWrapper input) throws InputValidationException {
        String[] split = input.splitInput();
        String firstLine = split[0];

        if (!firstLine.matches(CVS_REGEX_FIRST_LINE)) {
            throw new InputValidationException("The FirstLine contains illegal characters");

        }
    }

    private void validateWhole(InputWrapper input) throws InputValidationException {

        if (!input.matches(CVS_REGEX_TOTAL)) {
            throw new InputValidationException("The input data contain illegal characters");
        }
    }

}

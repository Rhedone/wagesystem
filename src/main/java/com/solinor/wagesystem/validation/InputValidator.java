package com.solinor.wagesystem.validation;

import com.solinor.wagesystem.model.InputWrapper;

/**
 * Created by yolan
 */
public interface InputValidator {
    public void validate(InputWrapper input) throws InputValidationException;

}

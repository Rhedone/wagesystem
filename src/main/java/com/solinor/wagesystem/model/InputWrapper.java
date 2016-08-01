package com.solinor.wagesystem.model;

import com.solinor.wagesystem.validation.InputValidationException;

/**
 * Created by yolan
 */
public class InputWrapper {
    public static final int MIN_LENGTH = 2;
    public static final String EOL_CHARACTER = "\\n";
    private String input;

    public InputWrapper(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public String[] splitInput() throws InputValidationException {
        String[] split = input.split(EOL_CHARACTER);
        if (split.length < MIN_LENGTH) {
            throw new InputValidationException("The input data was too short");
        }
        return split;
    }

    public boolean isEmpty() {
        return (input == null || input.isEmpty());
    }

    public boolean matches(String regex) {
        return input.matches(regex);
    }
}

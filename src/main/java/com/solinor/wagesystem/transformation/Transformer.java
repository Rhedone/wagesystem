package com.solinor.wagesystem.transformation;

import com.solinor.wagesystem.model.InputWrapper;
import com.solinor.wagesystem.model.WageEntry;
import com.solinor.wagesystem.validation.InputValidationException;

import java.util.List;

/**
 * Created by yolan
 */
public interface Transformer {
    public List<WageEntry> transform(InputWrapper input) throws InputValidationException;
}

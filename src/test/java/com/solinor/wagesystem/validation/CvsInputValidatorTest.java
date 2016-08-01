package com.solinor.wagesystem.validation;

import com.solinor.wagesystem.model.InputWrapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.junit.Assert.fail;

/**
 * Created by yolan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context.xml")
public class CvsInputValidatorTest {

    @Inject
    private CsvInputValidator validator;

    @Test
    public void testHandlerSimple() {
        InputWrapper input = new InputWrapper("Person Name,Person ID,Date,Start,End\n" +
                "Scott Scala,2,2.3.2014,6:00,7:00\n");
        try {
            validator.validate(input);
        } catch (InputValidationException e) {
            fail(e.getMessage());
        }
    }

    //todo check the exceptions to see if it right one was thrown(empty,body,header etc)
    @Test(expected = InputValidationException.class)
    public void testHandlerIllegalCharacter() throws InputValidationException {
        InputWrapper input = new InputWrapper("Person Name,Person ID,Date,Start,End\n" +
                "\\\n");
        validator.validate(input);
    }

    @Test(expected = InputValidationException.class)
    public void testHandlerEmpty() throws InputValidationException {
        InputWrapper input = new InputWrapper("");
        validator.validate(input);
    }

    @Test(expected = InputValidationException.class)
    public void testHandlerNull() throws InputValidationException {
        InputWrapper input = new InputWrapper(null);
        validator.validate(input);
    }
}

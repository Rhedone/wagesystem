package com.solinor.wagesystem.handler;

import com.solinor.wagesystem.model.InputWrapper;
import com.solinor.wagesystem.validation.InputValidationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by yolan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context.xml")
public class HandlerTest {

    private InputWrapper input;

    @Inject
    private Handler handler;

    @Before
    public void init() throws IOException {
        input = new InputWrapper("Person Name,Person ID,Date,Start,End\n" +
                "Scott Scala,2,2.3.2014,6:00,7:00\n");
    }

    @Test
    public void testHandlerSimple() throws InputValidationException {
        String expected = "Person Name,Person ID, Wage\n" +
                "Scott Scala,2,3.75";

        //String actual = handler.handle(input);
//        assertEquals(expected, actual);
    }

}

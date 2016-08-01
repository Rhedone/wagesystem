package com.solinor.wagesystem.entry;

import com.solinor.wagesystem.handler.Handler;
import com.solinor.wagesystem.model.InputWrapper;
import com.solinor.wagesystem.validation.InputValidationException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;

/**
 * Created by yolan
 */
@Path("/introduce")
@Component
public class EntryPoint {
    final static Logger logger = Logger.getLogger(EntryPoint.class);

    @Inject
    private Handler handler;

    @PUT
    public void introduceWages(String input) {
        try {
            InputWrapper wrappedInput = new InputWrapper(input);

            handler.handle(wrappedInput);
        } catch (InputValidationException e) {
            e.printStackTrace();
        }
    }

}

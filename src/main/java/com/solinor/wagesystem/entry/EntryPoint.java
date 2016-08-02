package com.solinor.wagesystem.entry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solinor.wagesystem.handler.Handler;
import com.solinor.wagesystem.model.InputWrapper;
import com.solinor.wagesystem.validation.InputValidationException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by yolan
 */
@Path("/introduce")
@Component
public class EntryPoint {
    final static Logger logger = Logger.getLogger(EntryPoint.class);

    @Inject
    private Handler handler;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response introduceWages(String input) {
        try {
            InputWrapper wrappedInput = new InputWrapper(input);

            String s = handler.handle(wrappedInput);

            return Response.ok().type(MediaType.APPLICATION_JSON).entity(s).build();

        } catch (InputValidationException | JsonProcessingException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}

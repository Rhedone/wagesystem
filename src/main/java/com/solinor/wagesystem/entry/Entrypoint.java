package com.solinor.wagesystem.entry;

import com.solinor.wagesystem.model.WageEntry;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by yolan
 */
@Path("/introduce")
public class Entrypoint {
    final static Logger logger = Logger.getLogger(Entrypoint.class);
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public WageEntry test() {
        WageEntry we = new WageEntry("Jos", 1, "today", "way too early", "rediculously late");
        return we;
    }

    @PUT
    @Path("/{input}")
    public void introduceWages(@PathParam("input") String hourList) {
        logger.info(hourList);
    }
}

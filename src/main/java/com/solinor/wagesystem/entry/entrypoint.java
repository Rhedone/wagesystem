package com.solinor.wagesystem.entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by yolan
 */
@Path("/hi")
public class entrypoint {
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String introduceWages() {
        return "werkt";
    }

    @GET
    @Path("/tost")
    @Produces(MediaType.TEXT_HTML)
    public String introduceWages2() {
        String werkt = "<h1>Het werkt werkt </h1>";
        return werkt;
    }

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public String introduceWages3() {
        String werkt = "<h1>Het werkt werkt </h1>";
        return werkt;
    }

}

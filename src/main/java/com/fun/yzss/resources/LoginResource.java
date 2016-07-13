package com.fun.yzss.resources;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by fanqq on 2016/7/13.
 */
@Component
@Path("/activate")
public class LoginResource {
    @GET
    @Path("/slb")
    public Response activateSlb(@Context HttpServletRequest request,
                                @Context HttpHeaders hh,
                                @QueryParam("slbId") List<Long> slbIds)throws Exception{
        return Response.status(200).entity("suc").build();
    }
}

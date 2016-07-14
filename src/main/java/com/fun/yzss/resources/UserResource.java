package com.fun.yzss.resources;

import com.fun.yzss.util.DesEncrypt;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("/user")
public class UserResource {


    @POST
    @Path("/register")
    public Response activateSlb(@Context HttpServletRequest request,
                                @Context HttpHeaders hh,
                                String registeInfoDes) throws Exception {

        return Response.status(200).entity("suc:" + registeInfoDes).build();
    }
}

package com.fun.yzss.resources;

import com.alibaba.fastjson.JSON;
import com.fun.yzss.exception.ValidateException;
import com.fun.yzss.model.protocol.RegisterModel;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

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
        RegisterModel register = JSON.parseObject(registeInfoDes, RegisterModel.class);
        if (register == null){
            throw new ValidateException("Data Illegal.");
        }


        return Response.status(200).entity("suc:" + registeInfoDes).build();
    }
}

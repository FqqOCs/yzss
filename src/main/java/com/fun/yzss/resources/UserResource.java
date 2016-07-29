package com.fun.yzss.resources;

import com.alibaba.fastjson.JSON;
import com.fun.yzss.exception.ValidateException;
import com.fun.yzss.model.protocol.AuthModel;
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
    @Path("/auth")
    public Response auth(@Context HttpServletRequest request,
                                @Context HttpHeaders hh,
                                String auth) throws Exception {
        AuthModel authModel = JSON.parseObject(auth, AuthModel.class);
        if (authModel == null){
            throw new ValidateException("Data Illegal.");
        }


        return Response.status(200).entity("suc:" + auth).build();
    }
}

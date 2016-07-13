package com.fun.yzss.util;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.HandlerWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fanqq on 2016/7/13.
 */

public class RequestLogHandler extends HandlerWrapper {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public RequestLogHandler() {
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        logger.info("[RequestLog] uri: " + uri);
    }
}

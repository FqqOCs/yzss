package com.fun.yzss.aop;

import com.fun.yzss.exception.NotFoundException;
import com.fun.yzss.exception.ValidateException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

import javax.ws.rs.core.Response;

/**
 * Created by fanqq on 2016/7/15.
 */
public class ExceptionAop implements Ordered {
    @Override
    public int getOrder() {
        return AopOrder.Exception;
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* com.fun.yzss.resources.*Resource.*(..))")
    public Object exception(ProceedingJoinPoint point) throws Throwable {
        String objectName = point.getSignature().getDeclaringTypeName();
        String methodName = point.getSignature().getName();
        try {
            return point.proceed();
        }catch (Throwable throwable){
            logger.error(objectName + " throws an error when calling " + methodName + ".", throwable);
            if (throwable instanceof ValidateException){
                return Response.status(400).entity("Bad Request! Data is illegal.").build();
            }
            if (throwable instanceof NotFoundException){
                return Response.status(404).entity("Not Found Error.").build();
            }
            if (throwable instanceof NullPointerException){
                return Response.status(404).entity("Not Found Error.").build();
            }
            return Response.status(500).entity("Server Error.").build();
        }
    }
}

package com.softserveinc.reviewer.interceptor;

import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingInterceptor implements MethodInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object returnValue = null;
        try {
            LOG.info("Entered into the method -> " + invocation.getMethod().getName()
                    + " and input arguments are -> " + Arrays.asList(invocation.getArguments()));
            returnValue = invocation.proceed();
        } catch (Throwable e) {
            LOG.error("Method has an exception " + e.getMessage());
        }
        return returnValue;
    }
}

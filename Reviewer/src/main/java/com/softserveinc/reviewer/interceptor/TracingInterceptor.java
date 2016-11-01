package com.softserveinc.reviewer.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TracingInterceptor implements MethodInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(TracingInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.nanoTime();
        try {
            return invocation.proceed();
        } finally {
            LOG.info(String.format("Invocation of method %s took %.1f ms.",
                    invocation.getMethod().getName(), (System.nanoTime() - start) / 1000000.0));
        }
    }

}

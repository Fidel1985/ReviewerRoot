package com.softserveinc.reviewer.interceptor;

import com.google.inject.Inject;
import com.softserveinc.reviewer.service.StatisticService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TracingInterceptor implements MethodInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(TracingInterceptor.class);

    @Inject
    private StatisticService statisticService;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            long duration = System.currentTimeMillis() - start;
            //statisticService.saveStats(duration, invocation.getMethod().getName());
            LOG.info(String.format("Invocation of method %s took %d ms.",
                    invocation.getMethod().getName(), duration));
        }
    }

}

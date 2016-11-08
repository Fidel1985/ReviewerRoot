package com.softserveinc.reviewer.health;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.codahale.metrics.health.HealthCheck;

public class TimeLimitedHealthCheck extends HealthCheck {
    private final long timeout;
    private final TimeUnit timeUnit;
    private final List<Callable<Boolean>> asyncHealthChecks;
    private final ExecutorService executorService;

    public TimeLimitedHealthCheck(long timeout, TimeUnit timeUnit, Callable<Boolean>... asyncHealthChecks) {
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.asyncHealthChecks = Arrays.asList(asyncHealthChecks);
        this.executorService = Executors.newFixedThreadPool(asyncHealthChecks.length);
    }

    @Override
    protected Result check() throws Exception {
        for (Future<Boolean> future : executorService.invokeAll(asyncHealthChecks, timeout, timeUnit)) {
            if (future.isCancelled() || !future.get()) {
                return Result.unhealthy("At least one of the services is unhealthy");
            }
        }
        return Result.healthy();
    }

}

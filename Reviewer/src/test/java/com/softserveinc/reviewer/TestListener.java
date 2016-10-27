package com.softserveinc.reviewer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Uses from custom test-suites runners to beautify output messages
 */
public class TestListener extends TestListenerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        LOG.info("<<SUCCESS>> " + tr.getName());
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        LOG.info("<<FAILED>> " + tr.getName());
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        super.onTestSkipped(tr);
        LOG.info("<<SKIPPED>> " + tr.getName());
    }
}

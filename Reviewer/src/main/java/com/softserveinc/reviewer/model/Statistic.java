package com.softserveinc.reviewer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Statistic {
    private String methodName;
    private int invocationCount;
    private double average;

    @JsonProperty("name")
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @JsonProperty("count")
    public int getInvocationCount() {
        return invocationCount;
    }

    public void setInvocationCount(int invocationCount) {
        this.invocationCount = invocationCount;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}

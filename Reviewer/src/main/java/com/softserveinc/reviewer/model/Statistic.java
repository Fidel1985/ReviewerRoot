package com.softserveinc.reviewer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.ObjectId;

public class Statistic {
    @ObjectId
    private String id;
    private String methodName;
    private int invocationCount;
    private double average;

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

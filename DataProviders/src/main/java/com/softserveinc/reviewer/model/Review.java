package com.softserveinc.reviewer.model;

public class Review {
    private String client;
    private transient String externalId;
    private ReviewProduct subjectProduct;
    private String submissionTime;
    private String text;
    private String title;
    private String type;

    public Review() {
        // Jackson deserialization
    }

    public Review(String client, String externalId, String submissionTime, String text, String title, String type) {
        this.client = client;
        this.externalId = externalId;
        this.subjectProduct = new ReviewProduct(externalId);
        this.submissionTime = submissionTime;
        this.text = text;
        this.title = title;
        this.type = type;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public ReviewProduct getSubjectProduct() {
        return subjectProduct;
    }

    public void setSubjectProduct(ReviewProduct subjectProduct) {
        this.subjectProduct = subjectProduct;
    }

    public String getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(String submissionTime) {
        this.submissionTime = submissionTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

class ReviewProduct {
    private String externalId;

    ReviewProduct(String externalId) {
        this.externalId = externalId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}

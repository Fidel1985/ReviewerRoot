package com.softserveinc.reviewer.api;

public class Review {
    private String client;
    private SubjectProduct subjectProduct;
    private String submissionTime;
    private String text;
    private String title;
    private String type;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public SubjectProduct getSubjectProduct() {
        return subjectProduct;
    }

    public void setSubjectProduct(SubjectProduct subjectProduct) {
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

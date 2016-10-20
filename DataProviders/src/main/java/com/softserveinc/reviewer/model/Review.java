package com.softserveinc.reviewer.model;

public class Review {
    private String client;
    private Product subjectProduct;
    private String submissionTime;
    private String text;
    private String title;
    private String type;

    public Review() {
        // Jackson deserialization
    }

    public Review(String client, String externalId, String submissionTime, String text, String title, String type) {
        this.client = client;
        this.subjectProduct = new Product(externalId);
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
        return subjectProduct == null ? null : subjectProduct.externalId;
    }

    public Product getSubjectProduct() {
        return subjectProduct;
    }

    public void setSubjectProduct(Product subjectProduct) {
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

    private class Product {
        private String externalId;

        Product(String externalId) {
            this.externalId = externalId;
        }

        public String getExternalId() {
            return externalId;
        }

        public void setExternalId(String externalId) {
            this.externalId = externalId;
        }
    }
}

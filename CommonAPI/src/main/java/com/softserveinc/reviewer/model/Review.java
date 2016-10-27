package com.softserveinc.reviewer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Review {
    private String client;
    private Product subjectProduct;
    private String submissionTime;
    private String text;
    private String title;
    private String type;

    public Review(String client, String externalId, String submissionTime, String text, String title, String type) {
        this.client = client;
        this.subjectProduct = new Product(externalId);
        this.submissionTime = submissionTime;
        this.text = text;
        this.title = title;
        this.type = type;
    }

    @JsonIgnore
    public String getExternalId() {
        return subjectProduct == null ? null : subjectProduct.externalId;
    }

    @Getter
    @NoArgsConstructor
    private class Product {
        private String externalId;

        public Product(String externalId) {
            this.externalId = externalId;
        }
    }

}

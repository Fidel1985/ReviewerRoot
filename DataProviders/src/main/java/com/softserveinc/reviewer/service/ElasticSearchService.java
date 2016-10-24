package com.softserveinc.reviewer.service;

import com.softesrveinc.reviewer.model.Review;
import com.softesrveinc.reviewer.response.ElasticSearchResponse;

import java.util.Arrays;
import java.util.List;

public class ElasticSearchService {

    private static final List<Review[]> REVIEWS_LIST = Arrays.asList(
            new Review[5], new Review[12], new Review[3],
            new Review[76], new Review[321], new Review[175], new Review[221],
            new Review[51], new Review[23], new Review[17], new Review[93],
            new Review[341], new Review[321],
            new Review[56], new Review[23], new Review[72], new Review[35]
    );

    private static final List<Review> REVIEWS = Arrays.asList(
            new Review("oak-style", "oak-sc-2ft", "2016-07-18T17:00:43.000Z",
                    "Quisque varius tortor vitae augue laoreet euismod aliquet metus vulputate. Duis tempor tristique dolor, ut venenatis enim " +
                            "fringilla feugiat. Praesent porttitor laoreet augue sed iaculis. Sed adipiscing convallis imperdiet. " +
                            "Fusce commodo dictum malesuada. Phasellus non nisl et elit euismod fermentum vel vitae lorem. " +
                            "Vestibulum nunc nisi, molestie vitae aliquet vel, pulvinar non nulla. Curabitur sit amet arcu turpis.",
                    "Sed nibh metus", "review"),
            new Review("table-next", "wooden-table", "2016-08-29T17:00:43.000Z",
                    "Proin eget malesuada ipsum. Maecenas purus arcu, imperdiet nec venenatis in, aliquet quis mauris. Donec in nulla erat, " +
                            "eget vehicula purus. Nulla varius, lacus id eleifend placerat, magna tortor accumsan quam, sit amet viverra " +
                            "quam tortor et urna. Integer nisl libero, euismod at commodo ut, sodales nec magna. Maecenas facilisis turpis " +
                            "id enim sollicitudin at dictum mauris commodo. Phasellus ut lectus eget turpis molestie vulputate. " +
                            "Praesent vel fringilla est. Ut tristique mollis magna, et pretium lacus aliquam quis.",
                    "Pellentesque habitant morbi tristique", "review"),
            new Review("besttable", "plNewWood", "2016-09-29T17:00:43.000Z",
                    "Proin eget malesuada ipsum. Maecenas purus arcu, imperdiet nec venenatis in, aliquet quis mauris. Donec in nulla erat, " +
                            "eget vehicula purus. Nulla varius, lacus id eleifend placerat, magna tortor accumsan quam, sit amet viverra " +
                            "quam tortor et urna. Integer nisl libero, euismod at commodo ut, sodales nec magna. Maecenas facilisis turpis " +
                            "id enim sollicitudin at dictum mauris commodo. Phasellus ut lectus eget turpis molestie vulputate. " +
                            "Praesent vel fringilla est. Ut tristique mollis magna, et pretium lacus aliquam quis.",
                    "Pellentesque habitant morbi tristique", "review")
    );

    static {
        for (Review[] reviews : REVIEWS_LIST) {
            for(int i = 0; i < reviews.length; i++) {
                reviews[i] = new Review();
            }
        }
        REVIEWS_LIST.get(0)[0] = REVIEWS.get(0);
        REVIEWS_LIST.get(3)[0] = REVIEWS.get(1);
        REVIEWS_LIST.get(11)[0] = REVIEWS.get(2);
    }

    public ElasticSearchResponse getReviews(String type, String client, String externalId) {

        for (Review[] reviews : REVIEWS_LIST) {
            if(reviews != null && validateReviewFieldsNotNull(reviews[0]) && reviews[0].getExternalId().equals(externalId) &&
                    reviews[0].getClient().equals(client) && reviews[0].getType().equals(type)) {
                return new ElasticSearchResponse(Arrays.asList(reviews));
            }
        }
        return null;
    }

    private boolean validateReviewFieldsNotNull(Review review) {
        return review.getExternalId() != null && review.getClient() != null && review.getType() != null;
    }
}

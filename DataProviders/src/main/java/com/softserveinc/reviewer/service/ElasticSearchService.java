package com.softserveinc.reviewer.service;

import com.softserveinc.reviewer.model.Review;
import com.softserveinc.reviewer.model.ReviewProduct;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ElasticSearchService {

    private static final List<Review> REVIEWS = Arrays.asList(
            new Review("oak-style", new ReviewProduct("oak-sc-2ft"), "2016-07-18T17:00:43.000Z",
                    "Quisque varius tortor vitae augue laoreet euismod aliquet metus vulputate. Duis tempor tristique dolor, ut venenatis enim " +
                            "fringilla feugiat. Praesent porttitor laoreet augue sed iaculis. Sed adipiscing convallis imperdiet. " +
                            "Fusce commodo dictum malesuada. Phasellus non nisl et elit euismod fermentum vel vitae lorem. " +
                            "Vestibulum nunc nisi, molestie vitae aliquet vel, pulvinar non nulla. Curabitur sit amet arcu turpis.",
                    "Sed nibh metus", "review"),
            new Review("table-next", new ReviewProduct("wooden-table"), "2016-08-29T17:00:43.000Z",
                    "Proin eget malesuada ipsum. Maecenas purus arcu, imperdiet nec venenatis in, aliquet quis mauris. Donec in nulla erat, " +
                            "eget vehicula purus. Nulla varius, lacus id eleifend placerat, magna tortor accumsan quam, sit amet viverra " +
                            "quam tortor et urna. Integer nisl libero, euismod at commodo ut, sodales nec magna. Maecenas facilisis turpis " +
                            "id enim sollicitudin at dictum mauris commodo. Phasellus ut lectus eget turpis molestie vulputate. " +
                            "Praesent vel fringilla est. Ut tristique mollis magna, et pretium lacus aliquam quis.",
                    "Pellentesque habitant morbi tristique", "review")
    );

    public List<Review> getReviews(String type, String client, String externalId) {

        return REVIEWS.stream().filter(x -> x.getType().equals(type) && x.getClient().equals(client) &&
                x.getSubjectProduct().getExternalId().equals(externalId)).collect(Collectors.toList());
    }
}

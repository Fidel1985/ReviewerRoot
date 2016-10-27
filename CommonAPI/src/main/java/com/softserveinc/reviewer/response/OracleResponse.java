package com.softserveinc.reviewer.response;

import com.softserveinc.reviewer.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OracleResponse {
    private List<Product> products;

}

package com.codecool.shop.ajax.filtering;

import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductFieldExtractor;

import java.util.Arrays;
import java.util.HashMap;

public class ProductFilterFieldMap extends HashMap<String, BaseModel> {

    public ProductFilterFieldMap(Product product) {
        Arrays.stream(product.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(FilterProductBy.class))
                .forEach(field -> {
                    String requestParameterName = field.getAnnotation(FilterProductBy.class).requestParameterName();
                    ProductFieldExtractor productFieldExtractor = new ProductFieldExtractor(product, field);
                    BaseModel baseModel = productFieldExtractor.extract();
                    this.put(requestParameterName, baseModel);
                });
    }
}

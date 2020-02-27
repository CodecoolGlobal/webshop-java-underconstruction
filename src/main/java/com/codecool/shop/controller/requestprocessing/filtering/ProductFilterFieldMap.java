package com.codecool.shop.controller.requestprocessing.filtering;

import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductFieldExtractor;

import java.util.Arrays;
import java.util.HashMap;

@Deprecated
public class ProductFilterFieldMap extends HashMap<String, BaseModel> {

    private ProductFieldExtractor extractor = new ProductFieldExtractor();

    public ProductFilterFieldMap(Product product) {
        Arrays.stream(product.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(FilterProductBy.class))
                .forEach(field -> {
                    String requestParameterName = field.getAnnotation(FilterProductBy.class).requestParameterName();
                    BaseModel baseModel = extractor.extract(product, field);
                    this.put(requestParameterName, baseModel);
                });
    }
}

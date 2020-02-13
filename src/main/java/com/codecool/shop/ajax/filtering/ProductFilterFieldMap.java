package com.codecool.shop.ajax.filtering;

import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductField;

import java.util.Arrays;
import java.util.HashMap;

public class ProductFilterFieldMap extends HashMap<String, BaseModel> {

    public ProductFilterFieldMap(Product product) {
        Arrays.stream(product.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(FilterProductBy.class))
                .forEach(field -> {
                    String requestParameterName = field.getAnnotation(FilterProductBy.class).requestParameterName();
                    ProductField productField = new ProductField(product, field);
                    BaseModel baseModel = productField.getBaseModel();
                    this.put(requestParameterName, baseModel);
                });
    }
}

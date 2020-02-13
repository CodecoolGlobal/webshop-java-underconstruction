package com.codecool.shop.model;

import java.lang.reflect.Field;

public class ProductFieldExtractor {

    BaseModel baseModel;

    public ProductFieldExtractor(Product product, Field field) {
        field.setAccessible(true);
        try {
            baseModel = (BaseModel) field.get(product);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public BaseModel extract() {
        return baseModel;
    }
}

package com.codecool.shop.model;

import java.lang.reflect.Field;

public class ProductFieldExtractor {

    public BaseModel extract(Product product, Field field) {
        field.setAccessible(true);
        try {
            return (BaseModel) field.get(product);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

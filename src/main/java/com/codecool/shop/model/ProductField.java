package com.codecool.shop.model;

import java.lang.reflect.Field;

public class ProductField {

    BaseModel baseModel;

    public ProductField(Product product, Field field) {
        field.setAccessible(true);
        try {
            baseModel = (BaseModel) field.get(product);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public BaseModel getBaseModel() {
        return baseModel;
    }
}

package com.codecool.shop.ajax;

import com.codecool.shop.model.ModelType;
import com.codecool.shop.model.Product;

public class ProductFilteringOption {

    private Integer id;
    private ModelType modelType;

    public ProductFilteringOption(String id, ModelType modelType) {
        this.id = id != null ? Integer.parseInt(id) : null;
        this.modelType = modelType;
    }

    public boolean shouldRetain(Product product) {
        return ((Integer) product.getMember(modelType).getId()).equals(id);
    }
}

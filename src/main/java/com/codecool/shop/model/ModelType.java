package com.codecool.shop.model;

public enum ModelType {
    PRODUCT("product"), PRODUCT_CATEGORY("product_category"), SUPPLIER("supplier");

    private final String name;

    ModelType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ModelType getByName(String name) {
        for (ModelType modelType : ModelType.values())
            if (modelType.getName().equals(name))
                return modelType;
        return null;
    }
}

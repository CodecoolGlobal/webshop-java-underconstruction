package com.codecool.shop.model;


import com.codecool.shop.controller.requestprocessing.filtering.ProductFilterFieldMap;

public interface Filterable {

    ProductFilterFieldMap getFilterFieldMap();
}

package com.codecool.shop.dao.implementation;


import com.codecool.shop.controller.requestprocessing.filtering.ProductFilteringStrategy;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.util.Util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoMem implements ProductDao {

    private List<Product> data = new ArrayList<>();
    private static ProductDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoMem() {
    }

    public static ProductDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        data.add(product);
    }

    @Override
    public Product find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        return data;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return data.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return data.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(Supplier supplier, ProductCategory productCategory) {
        return data.stream()
                .filter(t -> t.getSupplier().equals(supplier) && t.getProductCategory().equals(productCategory))
                .collect(Collectors.toList());
    }

    @Override
    public Product getBy(int productId) {
        List<Product> result = data.stream().filter(product -> product.getId() == productId).collect(Collectors.toList());
        if (result.size() != 0)
            return result.get(0);

        return null;
    }

    @Override
    public List<Product> getBy(ProductFilteringStrategy productFilteringStrategy) {
        return data.stream().filter(
                product -> productFilteringStrategy.getFilterMap().entrySet().stream().allMatch(entry -> {
                    boolean retained = false;
                    String fieldName = Util.toCamelCaseFromLowerCaseWithUnderScores(entry.getKey());
                    try {
                        assert fieldName != null;
                        Field field = product.getClass().getDeclaredField(fieldName);
                        field.setAccessible(true);
                        BaseModel fieldData = (BaseModel) field.get(product);
                        retained = Arrays.stream(entry.getValue()).anyMatch(i -> fieldData.getId() == i);
                    } catch (AssertionError | NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return retained;
                })).collect(Collectors.toList());
    }
}

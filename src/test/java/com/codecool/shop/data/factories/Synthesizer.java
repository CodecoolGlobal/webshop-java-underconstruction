package com.codecool.shop.data.factories;

import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class Synthesizer {

    private Factory productCategoryFactory = ProductCategoryFactory.getInstance();
    private Factory supplierFactory = SupplierFactory.getInstance();
    private Factory productFactory = ProductFactory.getInstance();

    private int defaultProductId = 0;
    private String defaultProductName = "product";
    private float defaultPrice = 1.0f;
    private String defaultProductDescription = "productDescription";
    private String defaultCurrencyString = "USD";

    private String defaultCategoryName = "category";
    private String defaultCategoryDepartment = "department";
    private String defaultCategoryDescription = "categoryDescription";

    private String defaultSupplierName = "supplier";
    private String defaultSupplierDescription = "supplierDescription";

    public void synthesize() {
        repeat(2, i -> productCategoryFactory.synthesize(new String[] {
                Integer.toString(i),
                defaultCategoryName + i,
                defaultCategoryDepartment + i,
                defaultCategoryDescription + i
        }));
        repeat(5, i -> supplierFactory.synthesize(new String[] {
                Integer.toString(i),
                defaultSupplierName + i,
                defaultSupplierDescription + i
        }));
        repeat(10, i -> productFactory.synthesize(new String[] {
                Integer.toString(defaultProductId + i),
                defaultProductName + i,
                Float.toString(defaultPrice),
                defaultCurrencyString,
                defaultProductDescription + i,
                Integer.toString((i + 4) / 5),
                Integer.toString((i + 1) / 2)
        }));
    }

    private void repeat(int repetitions, IntConsumer synthesis) {
        IntStream.rangeClosed(1, repetitions).forEach(synthesis);
    }

}

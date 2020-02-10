package com.codecool.shop.data;

import com.codecool.shop.data.factories.*;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.IOException;

public class DaoBuilder {

    private DaoBuilder() {
    }

    public static void extractData(ServletContext sc) throws IOException {

        BufferedReader supplierReader = CSVLoader.loadData("suppliers.csv", sc);
        BufferedReader productCategoryReader = CSVLoader.loadData("product_categories.csv", sc);
        BufferedReader productReader = CSVLoader.loadData("products.csv", sc);

        Factory supplierFactory = SupplierFactory.getInstance();
        Factory productCategoryFactory = ProductCategoryFactory.getInstance();
        Factory productFactory = ProductFactory.getInstance();

        supplierFactory.buildDao(supplierReader);
        productCategoryFactory.buildDao(productCategoryReader);
        productFactory.buildDao(productReader);
    }
}

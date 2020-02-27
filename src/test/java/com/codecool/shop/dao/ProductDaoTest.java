package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.data.factories.Synthesizer;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.util.Util;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.api.Assumptions.*;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {

    private static ProductDao productDao = ProductDaoMem.getInstance();
    private static ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getInstance();
    private static SupplierDao supplierDao = SupplierDaoMem.getInstance();

    @BeforeAll
    @Test
    static void init() {

    }

    @Test
    void testAdd() {

//        ProductCategory category = productCategoryDao.find(catId);
//        Supplier supplier = supplierDao.find(supId);
//        Assumptions.assumeTrue(category != null && supplier != null);
//        Product product = new Product("p", 1.0f, "USD", "d", category, supplier);
//        product.setId(999999);
//        productDao.add(product);
//        assertNotNull(productDao.find(product.getId()));
    }

    @Test
    void testFind() {

//        assertDoesNotThrow(() -> productDao.find(productId));
    }

    @Test
    void testRemove() {
//        productDao.add(product);
//        assertDoesNotThrow(() -> productDao.remove(product.getId()));
//        assertNull(productDao.getBy(product.getId()));
    }

    @Test
    void testGetAllReturnsNotNull() {
        List<Product> products = productDao.getAll();
        assertNotNull(products, "Was null");
    }

    @Test
    void testGetByProductCategory() {

//        ProductCategory productCategory = productCategoryDao.find(categoryId);
//        List<Product> products = productDao.getBy(productCategory);
//        assertTrue(products.stream().allMatch(product -> product.getProductCategory().getId() == categoryId));
    }

    @Test
    void testGetBySupplier() {

        //        Supplier supplier = supplierDao.find(supplierId);
//        List<Product> products = productDao.getBy(supplier);
//        assertTrue(products.stream().allMatch(product -> product.getSupplier().getId() == supplierId));
    }

}
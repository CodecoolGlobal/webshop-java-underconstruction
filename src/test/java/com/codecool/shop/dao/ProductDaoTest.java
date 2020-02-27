package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {

    private static ProductDao productDaoMem;

    @BeforeAll
    static void init() {
        productDaoMem = ProductDaoMem.getInstance();
    }

    @ParameterizedTest
    @MethodSource("productProvider")
    void testAdd(Product product) {
        assertDoesNotThrow(() -> productDaoMem.add(product));
        assertNotNull(productDaoMem.find(product.getId()));
        productDaoMem.remove(product.getId());
    }

    @ParameterizedTest
    @MethodSource("productProvider")
    void testFind(Product product) {
        productDaoMem.add(product);
        assertNotNull(productDaoMem.find(product.getId()));
        assertEquals(product, productDaoMem.find(product.getId()));
        productDaoMem.remove(product.getId());
    }

    @ParameterizedTest
    @MethodSource("productProvider")
    void testRemove(Product product) {
        productDaoMem.add(product);
        assertDoesNotThrow(() -> productDaoMem.remove(product.getId()));
        assertNull(productDaoMem.find(product.getId()));
    }

    @Test
    void testGetAllReturnsNotNull() {
        List<Product> products = productDaoMem.getAll();
        assertNotNull(products, "Was null");
    }

    @ParameterizedTest
    @MethodSource("productAndCategoryProvider")
    void testGetByProductCategory(Product product, ProductCategory category) {
        productDaoMem.add(product);
        assertTrue(productDaoMem.getBy(category).contains(product));
        productDaoMem.remove(product.getId());
    }

    @ParameterizedTest
    @MethodSource("productAndSupplierProvider")
    void testGetBySupplier(Product product, Supplier supplier) {
        productDaoMem.add(product);
        assertTrue(productDaoMem.getBy(supplier).contains(product));
        productDaoMem.remove(product.getId());
    }

    @AfterAll
    @Test
    static void finalCheck() {
        assertTrue(productDaoMem.getAll().isEmpty());
    }

    static Stream<Arguments> productProvider() {
        ProductCategory productCategory = createProductCategory(1);
        Supplier supplier = createSupplier(1);
        Product product = createProduct(1, productCategory, supplier);
        return Stream.of(Arguments.arguments(product));
    }

    static Stream<Arguments> productAndCategoryProvider() {
        Supplier supplier = createSupplier(2);
        ProductCategory productCategory = createProductCategory(2);
        Product product = createProduct(2, productCategory, supplier);
        return Stream.of(Arguments.arguments(product, productCategory));
    }

    static Stream<Arguments> productAndSupplierProvider() {
        Supplier supplier = createSupplier(2);
        ProductCategory productCategory = createProductCategory(2);
        Product product = createProduct(2, productCategory, supplier);
        return Stream.of(Arguments.arguments(product, supplier));
    }

    static ProductCategory createProductCategory(int id) {
        return new ProductCategory(id, "cat", "dep", "desc");
    }

    static Supplier createSupplier(int id) {
        return new Supplier(id, "sup", "desc");
    }

    static Product createProduct(int id, ProductCategory productCategory, Supplier supplier) {
        return new Product(id, "p" + id, 1.0f, "USD", "desc" + id, productCategory, supplier);
    }

}
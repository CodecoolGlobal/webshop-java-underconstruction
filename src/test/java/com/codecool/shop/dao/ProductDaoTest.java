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

    private static Synthesizer synthesizer = new Synthesizer();
    private static ProductDao productDao = ProductDaoMem.getInstance();
    private static ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getInstance();
    private static SupplierDao supplierDao = SupplierDaoMem.getInstance();

    @BeforeAll
    @Test
    static void buildMemDaos() {
        synthesizer.synthesize();
    }

    @ParameterizedTest
    @MethodSource("categoryAndSupplierIdProvider")
    void testAdd(int catId, int supId) {
        ProductCategory category = productCategoryDao.find(catId);
        Supplier supplier = supplierDao.find(supId);
        Assumptions.assumeTrue(category != null && supplier != null);
        Product product = new Product("p", 1.0f, "USD", "d", category, supplier);
        product.setId(999999);
        productDao.add(product);
        assertNotNull(productDao.find(product.getId()));
    }

    static Stream<Arguments> categoryAndSupplierIdProvider() {
        return Stream.of(
                Arguments.arguments(1, 1),
                Arguments.arguments(1, 2),
                Arguments.arguments(2, 3),
                Arguments.arguments(2, 4),
                Arguments.arguments(1, 5)
        );
    }

    @ParameterizedTest(name = "product_id = {arguments}")
    @ValueSource(ints = {1, 2, 3, 4, 5, 7, 1, 2})
    void testRemove(int productId) {

    }

    @Test
    void testGetAllReturnsNotNull() {
        List<Product> products = productDao.getAll();
        assertNotNull(products, "Was null");
    }

    @ParameterizedTest(name = "id = {arguments}")
    @ValueSource(ints = {1, 2, 3, 5, 7, 8, 1, 2, 1, 1})
    void testGetByProductCategory(int categoryId) {
        ProductCategory productCategory = productCategoryDao.find(categoryId);
        List<Product> products = productDao.getBy(productCategory);
        assertTrue(products.stream().allMatch(product -> product.getProductCategory().getId() == categoryId));
    }

    @ParameterizedTest(name = "id = {arguments}")
    @MethodSource({"idSupplier"})
    void testGetBySupplier(int supplierId) {
        Supplier supplier = supplierDao.find(supplierId);
        List<Product> products = productDao.getBy(supplier);
        assertTrue(products.stream().allMatch(product -> product.getSupplier().getId() == supplierId));
    }

    static IntStream idSupplier() {
        return IntStream.generate(() -> Util.randRange(0, 10)).limit(10);
    }

}
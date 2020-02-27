package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.data.factories.Synthesizer;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.util.Util;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoTest {

    private static Synthesizer synthesizer = new Synthesizer();
    private static ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getInstance();

    @BeforeAll
    @Test
    static void buildMemDaos() {
        synthesizer.synthesize();
    }

    @ParameterizedTest(name = "{displayName}")
    @MethodSource("categorySupplier")
    void testAdd(ProductCategory category) {
        assertDoesNotThrow(() -> productCategoryDao.add(category));
        assertNotNull(productCategoryDao.find(category.getId()));
    }

    @Test
    void testFind() {
    }

    @Test
    void testRemove() {
    }

    @Test
    void testGetAll() {
    }

    static Stream<Arguments> categorySupplier() {
        ProductCategory category = new ProductCategory(Util.randRange(11, 100), "c", "d", "d");
        return Stream.of(Arguments.of(category));
    }
}
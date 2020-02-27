package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.data.factories.Synthesizer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoTest {

    private static Synthesizer synthesizer = new Synthesizer();
    private static ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getInstance();

    @BeforeAll
    @Test
    static void buildMemDaos() {
        synthesizer.synthesize();
    }

    @Test
    void testAdd() {
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
}
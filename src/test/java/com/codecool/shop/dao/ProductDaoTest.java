package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.data.factories.Synthesizer;
import com.codecool.shop.model.Product;
import com.codecool.shop.util.Util;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {

    private static Synthesizer synthesizer = new Synthesizer();
    private static ProductDao productDao = ProductDaoMem.getInstance();

    @BeforeAll
    @Test
    static void buildMemDaos() {
        synthesizer.synthesize();
    }

    @BeforeEach
    void logTestNameBeforeEach(TestInfo testInfo) {
        System.out.println(Util.multiplyString("-", 50));
        System.out.println(String.format("Initiating %s", testInfo.getDisplayName()));
        System.out.println(Util.multiplyString("-", 50) + "\n");
    }

    @AfterEach
    void logTestNameAfterEach(TestInfo testInfo) {
        System.out.println("\n" + Util.multiplyString("-", 50));
        System.out.println(String.format("Finishing %s", testInfo.getDisplayName()));
        System.out.println(Util.multiplyString("-", 50));
    }

    @Test
    void testGetAllReturnsNotNull() {
        List<Product> products = productDao.getAll();
        assertNotNull(products, "Was null");
    }

}
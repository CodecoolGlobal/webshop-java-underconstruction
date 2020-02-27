package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.data.factories.Synthesizer;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.util.Util;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoTest {

    private static Synthesizer synthesizer = new Synthesizer();
    private static SupplierDao supplierDao = SupplierDaoMem.getInstance();

    @BeforeAll
    static void buildMemDaos() {
        synthesizer.synthesize();
    }

    @ParameterizedTest(name = "{index}")
    @MethodSource("supplierSupplier")
    void testAdd(Supplier supplier) {
        assertDoesNotThrow(() -> supplierDao.add(supplier));
        assertNotNull(supplierDao.find(supplier.getId()));
    }

    @Test
    void testFind() {
    }

    @ParameterizedTest(name = "{index}")
    @MethodSource("supplierSupplier")
    void testRemove() {
    }

    @Test
    void testGetAll() {
    }

    static Stream<Arguments> supplierSupplier() {
        return Stream.of(Arguments.of(new Supplier(Util.randRange(1, 5), "s", "d")));
    }
}
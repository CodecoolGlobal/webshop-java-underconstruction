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
import org.junit.jupiter.params.provider.ValueSource;

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
        supplierDao.remove(supplier.getId());
    }

    @ParameterizedTest(name = "{index}")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7})
    void testFind(int id) {
        assertDoesNotThrow(() -> supplierDao.find(id));
        Supplier supplier = supplierDao.find(id);
        if (id <= 5)
            assertNotNull(supplier);
        else
            assertNull(supplier);
    }

    @ParameterizedTest(name = "{index}")
    @MethodSource("supplierSupplier")
    void testRemove(Supplier supplier) {
        supplierDao.add(supplier);
        assertDoesNotThrow(() -> supplierDao.remove(supplier.getId()));
        assertNull(supplierDao.find(supplier.getId()));
    }

    @Test
    void testGetAll() {
        assertDoesNotThrow(() -> supplierDao.getAll());
        assertNotNull(supplierDao.getAll());
    }

    static Stream<Arguments> supplierSupplier() {
        return Stream.of(Arguments.of(new Supplier(Util.randRange(6, 10), "s", "d")));
    }
}
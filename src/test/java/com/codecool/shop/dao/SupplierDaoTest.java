package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.sqlImplementation.SupplierDaoJDBC;
import com.codecool.shop.data.sql.ConnectionProperties;
import com.codecool.shop.data.sql.Executor;
import com.codecool.shop.data.sql.StatementProvider;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoTest {

    private static SupplierDao supplierDao;
    private static Executor executor;

    @BeforeAll
    static void init() {
        initDao(SupplierDaoMem.class);
    }

    private static void initDao(Class<? extends SupplierDao> daoClass) {
        if (daoClass == SupplierDaoJDBC.class) {
            ConnectionProperties.readFrom("./src/test/resources/test_connection.properties");
            supplierDao = new SupplierDaoJDBC();
            executor = new Executor();
        }
        else supplierDao = SupplierDaoMem.getInstance();
    }

    private void cleanse() {
        if (supplierDao instanceof SupplierDaoJDBC) {
            String query = "TRUNCATE product, product_category, supplier, line_item RESTART IDENTITY;";
            StatementProvider statementProvider = connection -> connection.prepareStatement(query);
            executor.execute(statementProvider);
        } else {
            supplierDao.remove(1);
            supplierDao.remove(2);
        }
    }

    @BeforeEach
    void cleanseBeforeEach() {
        cleanse();
    }

    @Test
    void testGetAllIfNoRecord() {
        assertEquals(new ArrayList<Supplier>(),supplierDao.getAll());
    }

    @Test
    void testGetAllIfRecordsExist() {
        Supplier supplier2 = new Supplier(
                1, "Amazon", "Recreation"
        );
        Supplier supplier3 = new Supplier(
                2, "Test stuff", "Test Description"
        );
        Supplier[] suppliers = {supplier2, supplier3};

        supplierDao.add(supplier2);
        supplierDao.add(supplier3);

        assertArrayEquals(suppliers, supplierDao.getAll().toArray());
    }

    @Test
    void testFindIfExists() {
        Supplier supplier = new Supplier(
                1, "Natural psychedelic", "Recreation"
        );
        supplierDao.add(supplier);
        assertEquals(supplier, supplierDao.find(1));
    }

    @Test
    void testFindIfNotExists() {
        assertNull(supplierDao.find(3));
    }

    @Test
    void testRemoveIfExists() {
        Supplier supplier = new Supplier(
                1, "Natural psychedelic", "Recreation"
        );
        supplierDao.add(supplier);
        assertEquals(supplier, supplierDao.find(1));
        supplierDao.remove(1);
        assertNull(supplierDao.find(1));
    }
}
package com.codecool.shop.sql;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.sqlImplementation.SupplierDaoJDBC;
import com.codecool.shop.data.sql.ConnectionProperties;
import com.codecool.shop.data.sql.Executor;
import com.codecool.shop.data.sql.StatementProvider;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.util.SQLDumpReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SupplierDaoJdbcTest {
    private SupplierDao supplierDao = new SupplierDaoJDBC();
    private static Executor executor = new Executor();

    @BeforeAll
    @Test
    static void setConnection() {
        ConnectionProperties.readFrom("./src/test/resources/test_connection.properties");
    }

    @BeforeEach
    @Test
    void setDatabase() {
        String query = SQLDumpReader.getQueryString();
        StatementProvider statementProvider = connection -> connection.prepareStatement(query);
        executor.execute(statementProvider);
    }

    @Test
    void testGetAllIfNoRecord() {
        assertEquals(new ArrayList<Supplier>(),supplierDao.getAll());
    }

    @Test
    void testGetAllIfRecordsExist() {
        Supplier supplier2 = new Supplier(
                2, "Amazon", "Recreation"
        );
        Supplier supplier3 = new Supplier(
                3, "Test stuff", "Test Description"
        );
        Supplier[] suppliers = {supplier2, supplier3};

        supplierDao.add(supplier2);
        supplierDao.add(supplier3);

        assertArrayEquals(suppliers, supplierDao.getAll().toArray());
    }

    @Test
    void testFindIfExists() {
        Supplier supplier = new Supplier(
                2, "Natural psychedelic", "Recreation"
        );
        supplierDao.add(supplier);
        assertEquals(supplier, supplierDao.find(2));
    }

    @Test
    void testFindIfNotExists() {
        assertNull(supplierDao.find(3));
    }

    @Test
    void testRemoveIfExists() {
        Supplier supplier = new Supplier(
                2, "Natural psychedelic", "Recreation"
        );
        supplierDao.add(supplier);
        assertEquals(supplier, supplierDao.find(2));
        supplierDao.remove(2);
        assertNull(supplierDao.find(2));
    }
}

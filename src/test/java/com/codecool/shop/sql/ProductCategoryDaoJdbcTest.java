package com.codecool.shop.sql;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.sqlImplementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.sqlImplementation.ProductDaoJDBC;
import com.codecool.shop.data.sql.ConnectionProperties;
import com.codecool.shop.data.sql.DatabaseConnection;
import com.codecool.shop.data.sql.Executor;
import com.codecool.shop.data.sql.StatementProvider;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.util.SQLDumpReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCategoryDaoJdbcTest {

    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoJDBC();
    private ProductDao productDao = new ProductDaoJDBC();
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
        System.out.println(query);
        StatementProvider statementProvider = connection -> connection.prepareStatement(query);
        executor.execute(statementProvider);
    }

    @Test
    void testGetAll() {
        assertDoesNotThrow(() -> productCategoryDao.getAll());
        assertNotNull(productCategoryDao.getAll());
    }
    @Test
    void testFindIfExists() {
        ProductCategory category = new ProductCategory(
                1, "Natural psychedelic", "Recreation", null
        );
        assertEquals(category, productCategoryDao.find(1));
    }

    @Test
    void testFindIfNotExists() {
        assertNull(productCategoryDao.find(3));
    }

    @Test
    void testRemoveIfExists() {
        ProductCategory category = new ProductCategory(
                1, "Natural psychedelic", "Recreation", null
        );
        assertEquals(category, productCategoryDao.find(1));
        productDao.remove(1);
        productCategoryDao.remove(1);
        assertNull(productCategoryDao.find(1));
    }

    @Test
    void testRemoveIfNotExists() {
        assertNull(productCategoryDao.find(1));
    }

}

package com.codecool.shop.sql;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.sqlImplementation.ProductCategoryDaoJDBC;
import com.codecool.shop.data.sql.ConnectionProperties;
import com.codecool.shop.data.sql.Executor;
import com.codecool.shop.data.sql.StatementProvider;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.util.SQLDumpReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCategoryDaoJdbcTest {

    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoJDBC();
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
        assertEquals(new ArrayList<ProductCategory>(),productCategoryDao.getAll());
    }

    @Test
    void testGetAllIfRecordsExist() {
        ProductCategory category2 = new ProductCategory(
                2, "Natural psychedelic", "Recreation", null
        );
        ProductCategory category3 = new ProductCategory(
                3, "Test stuff", "Recreation", "This is a test instance"
        );
        ProductCategory[] categories = {category2, category3};

        productCategoryDao.add(category2);
        productCategoryDao.add(category3);

        assertArrayEquals(categories, productCategoryDao.getAll().toArray());
    }

    @Test
    void testFindIfExists() {
        ProductCategory category = new ProductCategory(
                2, "Natural psychedelic", "Recreation", null
        );
        productCategoryDao.add(category);
        assertEquals(category, productCategoryDao.find(2));
    }

    @Test
    void testFindIfNotExists() {
        assertNull(productCategoryDao.find(3));
    }

    @Test
    void testRemoveIfExists() {
        ProductCategory category = new ProductCategory(
                2, "Natural psychedelic", "Recreation", null
        );
        productCategoryDao.add(category);
        assertEquals(category, productCategoryDao.find(2));
        productCategoryDao.remove(2);
        assertNull(productCategoryDao.find(2));
    }
}
